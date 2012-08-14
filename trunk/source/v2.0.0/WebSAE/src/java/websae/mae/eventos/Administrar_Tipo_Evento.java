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
import websae.informacion.Estado;
import websae.informacion.Lenguaje;
import websae.mae.dominio.AE_Tipo_Evento;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Tipo_Evento {

    private AE_Tipo_Evento tipo_evento;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Tipo_Evento(HttpServletRequest request, String tipo) {
        this.tipo_evento = new AE_Tipo_Evento();
        
        this.tipo_evento.setTe_id_tipo_evento(new BigDecimal( (String) request.getParameter("txt_id_tipo_evento") ));
        this.tipo_evento.setTe_nombre( (String) request.getParameter("txt_tipo_evento") );
        this.tipo_evento.setTe_estado( Estado.VIGENTE );
        this.tipo_accion = tipo;

        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.tipo_evento.getTe_id_tipo_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.tipo_evento.getTe_nombre()));
        parametros.add(new Dato(Tipo.IN, this.tipo_evento.getTe_estado()));
        parametros.add(new Dato(Tipo.OUT, new String()));

        String sql = "{call ae_administrar_tipo_evento(?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_TIPO_EVENTO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_TIPO_EVENTO_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_TIPO_EVENTO_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TIPO_EVENTO_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ASIGNADO_EVENTO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Tipo_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
