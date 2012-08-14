/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mae.eventos;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Lenguaje;
import websae.mae.dominio.AE_Auspicio;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Auspicio {
    
    private AE_Auspicio auspicio;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Auspicio(HttpServletRequest request, String id_evento, String tipo) {
        this.auspicio = new AE_Auspicio();
        
        this.auspicio.setAu_id_auspicio( new BigDecimal( request.getParameter("txt_id_auspiciante") ) );
        this.auspicio.setAu_monto( Float.parseFloat( request.getParameter("txt_monto") ) );
        this.auspicio.setAu_descripcion( request.getParameter("txt_descripcion") );
        this.auspicio.ref_evento.setEv_id_evento( new BigDecimal( id_evento ) );
        this.auspicio.ref_empresa.setEm_id_empresa( new BigDecimal( request.getParameter("cmb_empresa") ) );
        this.tipo_accion = tipo;

        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }
    
    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.auspicio.getAu_id_auspicio(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.auspicio.getAu_monto(), new Float("0")));
        parametros.add(new Dato(Tipo.IN, this.auspicio.getAu_descripcion(), new String("")));
        parametros.add(new Dato(Tipo.IN, this.auspicio.getRef_evento().getEv_id_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.auspicio.getRef_empresa().getEm_id_empresa(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));
        
        String sql = "{call ae_administrar_auspicio(?,?,?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
    }
    
    public String getMensaje() {
        return this.respuesta.getMensaje();
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_AUSPICIANTE_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_AUSPICIANTE_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_AUSPICIANTE_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_AUSPICIANTE_REPETIDO[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Auspicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
