/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mac.eventos;

import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Estado;
import websae.informacion.Lenguaje;
import websae.informacion.Constante;

/**
 *
 * @author Guillermo Pizarro
 */
public class Asignar_Usuario_Perfil {
    private String id_perfil;
    private String email;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo;
    
    public Asignar_Usuario_Perfil(String email, String id_perfil, String tipo) {
        this.email = email;
        this.id_perfil = id_perfil;
        this.tipo = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void actualizar_usuario_perfil() {
        Parametros parametros = new Parametros();
        parametros.add(new Dato(Tipo.IN, this.email ));
        parametros.add(new Dato(Tipo.IN, this.id_perfil ));
        if (this.tipo.compareTo( Constante.HABILITAR ) == 0) {
            parametros.add(new Dato(Tipo.IN, Estado.VIGENTE ));
        } else if (this.tipo.compareTo( Constante.DESHABILITAR ) == 0) {
            parametros.add(new Dato(Tipo.IN, Estado.ELIMINADO ));
        }
        
        String sql = "{call ac_actualizar_asignacion_usuario_perfil(?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
    }

    public String getMensaje() {
        return respuesta.getMensaje();
    }

    public String getTipo() {
        return tipo;
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);

        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.OK_ASIGNACIONES_REGISTRAR[ Lenguaje.parse(lang) ]);
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Asignar_Usuario_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
