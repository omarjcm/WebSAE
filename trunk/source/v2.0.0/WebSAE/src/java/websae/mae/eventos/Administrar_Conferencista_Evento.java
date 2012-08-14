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
import websae.informacion.Funciones;
import websae.informacion.Lenguaje;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Conferencista_Evento {

    private BigDecimal id_usuario;
    private BigDecimal id_evento;
    private String imagen;
    private String biografia;
    private String linkedin;
    private Boolean es_invitado;
    
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Conferencista_Evento(HttpServletRequest request, String id_evento, String tipo) {
        this.id_usuario = new BigDecimal( request.getParameter("txt_id_usuario") );
        this.id_evento = new BigDecimal( id_evento );
        this.imagen = Funciones.getString( request.getParameter("txt_imagen2") );
        this.biografia = request.getParameter("txt_biografia");
        this.linkedin = request.getParameter("txt_linkedin");
        this.es_invitado = Funciones.getBoolean( request.getParameter("rb_invitado") );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.id_usuario, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.imagen, new String()));
        parametros.add(new Dato(Tipo.IN, this.biografia, new String()));
        parametros.add(new Dato(Tipo.IN, this.linkedin, new String()));
        parametros.add(new Dato(Tipo.IN, this.es_invitado, new Boolean(true)));
        parametros.add(new Dato(Tipo.OUT, null, new String()));
        
        String sql = "{call ae_administrar_conferencista_evento(?,?,?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_CONFERENCISTA_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CONFERENCISTA_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CONFERENCISTA_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_CONFERENCISTA_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("registrar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", "***Error en la eliminación.");
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje",Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Conferencista_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
