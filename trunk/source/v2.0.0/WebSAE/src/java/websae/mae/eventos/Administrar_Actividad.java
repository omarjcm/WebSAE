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
import websae.mae.dominio.AE_Actividad;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Actividad {

    private AE_Actividad actividad;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Actividad(HttpServletRequest request, String tipo) {
        this.actividad = new AE_Actividad();
        
        this.actividad.setAc_id_actividad( new BigDecimal( request.getParameter("txt_id_actividad") ) );
        this.actividad.setAc_hora_inicio( request.getParameter("txt_hora_inicio") );
        this.actividad.setAc_hora_fin( request.getParameter("txt_hora_fin") );
        this.actividad.setAc_actividad( request.getParameter("txt_actividad") );
        this.actividad.ref_fecha_evento.setFe_id_fecha_evento( new BigDecimal( request.getParameter("txt_id_fecha_evento") ) );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.actividad.getAc_id_actividad(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.actividad.getAc_hora_inicio(), new String()));
        parametros.add(new Dato(Tipo.IN, this.actividad.getAc_hora_fin(), new String()));
        parametros.add(new Dato(Tipo.IN, this.actividad.getAc_actividad(), new String()));
        parametros.add(new Dato(Tipo.IN, this.actividad.ref_fecha_evento.getFe_id_fecha_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));
        
        String sql = "{call ae_administrar_actividad(?,?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_ACTIVIDAD_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ACTIVIDAD_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ACTIVIDAD_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ACTIVIDAD_REPETIDA[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("fuera-rango")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ACTIVIDAD_TRASLAPADA[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
