/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.eventos;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import mad.eventos.Datos;
import mad.objetos.Registro;
import mad.objetos.Table;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Email;
import websae.informacion.Lenguaje;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class Recordar_Clave {
    
    private String email;
    private String host;
    private String email_admin;
    private String email_remitente;
    private String nombre_admin;
    private String ruta;
    private String language;
    private String mensaje;
    
    public Recordar_Clave(String email, String host, String email_admin, String email_remitente, String nombre_admin, String ruta, String language) {
        this.email = email;
        this.host = host;
        this.email_admin = email_admin;
        this.email_remitente = email_remitente;
        this.nombre_admin = nombre_admin;
        this.ruta = ruta;
        this.language = language;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void recordar_clave() {
        AC_Usuario usuario = null;
        
        /** PASO 1: Realizar la busqueda de la clave del Usuario */
        Datos datos = new Datos("WebSAE");
        Registro registros = datos.consulta("{call su_obtener_usuario('"+this.email+"')}");
        for (int i=0; i<registros.size(); i++) {
            Table objeto = (Table) registros.get(i);
            usuario = new AC_Usuario();
            usuario.setUs_genero( (String) objeto.get("us_genero") );
            usuario.setUs_nombre( (String) objeto.get("us_nombre") );
            usuario.setUs_apellido( (String) objeto.get("us_apellido") );
            usuario.setUs_clave( (String) objeto.get("us_clave") );
            usuario.setUs_email( (String) objeto.get("us_email") );
        }
        /** Verificar la existencia del usuario. */
        if (usuario == null) {
            this.mensaje = "ERROR:busqueda";
            return;
        }
            
        /** PASO 2: Envio de email con la Clave extraida de la Base de Datos. */
        try {
            /** Seteo de los parametros para la plantilla de envio de email */
            HashMap<String, String> parametros = new HashMap<String, String>();
            if (usuario.getUs_genero().compareTo("m") == 0)
                parametros.put("genero", "o");
            else
                parametros.put("genero", "a");
            parametros.put("nombre", usuario.getUs_nombre());
            parametros.put("apellido", usuario.getUs_apellido());
            parametros.put("clave", usuario.getUs_clave());
            parametros.put("remitente", this.email_remitente);
            
            /** Inicializacion de los emails que se van a enviar. */
            Address[] to = new Address[1];
            to[0] = new InternetAddress(usuario.getUs_email(), usuario.getUs_nombre() + " " + usuario.getUs_apellido() );
            Address[] bcc = new Address[1];
            bcc[0] = new InternetAddress(this.email_admin, this.nombre_admin);
            
            /** Creacion del email que se va a enviar. */
            Email correo = new Email("Web S.A.E. - Recordatorio de Clave", this.host, "recordar_clave_"+this.language+".vm",
                                    this.ruta, parametros, new InternetAddress(this.email_admin, this.nombre_admin),
                                    to, null, bcc);
            correo.enviar();
            this.mensaje = "OK:recordar";
        } catch (UnsupportedEncodingException ex) {
            this.mensaje = "ERROR:email";
            Logger.getLogger(Recordar_Clave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("recordar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EMAIL_RECUPERACION[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("busqueda")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMAIL_NOREGISTRADO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("email")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMAIL_NOENVIADO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Recordar_Clave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
