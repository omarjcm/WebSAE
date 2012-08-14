/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mac.eventos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Lenguaje;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class Autentificar_Usuario {

    private AC_Usuario admin;
    private String mensaje;

    public Autentificar_Usuario(AC_Usuario admin) {
        if (admin != null) {
            this.admin = admin;
            this.mensaje = "OK:verificar";
        } else
            this.mensaje = "ERROR:verificar";
    }
    
    public Autentificar_Usuario(HttpServletRequest request) {
        this.admin = new AC_Usuario();
        this.admin.setUs_email( request.getParameter("txt_usuario") );
        this.admin.setUs_clave( request.getParameter("txt_clave") );
        
        HttpSession sesion = request.getSession();
        if (this.admin.obtener_usuario()) {
            sesion.setAttribute("usuario", this.admin);
            this.mensaje = "OK:registro";
        } else {
            sesion.setAttribute("usuario", null);
            this.mensaje = "ERROR:registro";
        }
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public AC_Usuario getAdmin() {
        return admin;
    }
    
    public static JSONObject obtener_mensaje(String mensaje, AC_Usuario usuario, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registro") || valores[1].equals("verificar")) {
                    json.put("tipo", "OK");
                    json.put("usuario", AC_Usuario.toJSONObject(usuario));
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMAIL_CLAVE[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Autentificar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
