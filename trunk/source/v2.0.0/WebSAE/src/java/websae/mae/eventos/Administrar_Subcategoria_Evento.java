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
import websae.mae.dominio.AE_Subcategoria_Evento;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Subcategoria_Evento {
    
    private AE_Subcategoria_Evento subcategoria_evento;
    private BigDecimal id_evento;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Subcategoria_Evento(HttpServletRequest request, String id_evento, String tipo) {
        this.subcategoria_evento = new AE_Subcategoria_Evento();
        
        this.subcategoria_evento.setSe_id_subcategoria_evento( new BigDecimal( request.getParameter("txt_id_descuento") ) );
        this.subcategoria_evento.setSe_porcentaje_descuento( Float.parseFloat( request.getParameter("txt_descuento") ) );
        this.subcategoria_evento.ref_subcategoria.setSu_id_subcategoria( new BigDecimal( request.getParameter("cmb_subcategoria") ) );
        this.id_evento = new BigDecimal( id_evento );
        this.tipo_accion = tipo;

        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.subcategoria_evento.getSe_id_subcategoria_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.subcategoria_evento.getSe_porcentaje_descuento(), new Float("0")));
        parametros.add(new Dato(Tipo.IN, this.subcategoria_evento.ref_subcategoria.getSu_id_subcategoria(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));

        String sql = "{call ae_administrar_subcategoria_evento(?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_DESCUENTO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_DESCUENTO_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_DESCUENTO_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_DESCUENTO_REPETIDO[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(AE_Subcategoria_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }  
}
