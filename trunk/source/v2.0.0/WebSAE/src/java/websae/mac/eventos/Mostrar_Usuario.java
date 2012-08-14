/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mac.eventos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Datos;
import mad.objetos.Registro;
import mad.objetos.Table;
import org.json.JSONArray;
import org.json.JSONObject;
import websae.informacion.Lenguaje;
import websae.mac.dominio.AC_Perfil;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class Mostrar_Usuario {

    public Mostrar_Usuario() {
    }

    private static List< AC_Usuario > buscar_usuario_x_perfil_por_nombre_apellido(String sql) {
        List< AC_Usuario > usuarios = new LinkedList<AC_Usuario>();
        Datos dato = new Datos("WebSAE");
        Registro registros = dato.consulta( sql );
        for (int i=0; i<registros.size(); i++) {
            Table objeto = registros.get(i);
            AC_Usuario usuario_nuevo = new AC_Usuario();
            usuario_nuevo.cr_AC_Usuario(objeto, true);
            usuarios.add(usuario_nuevo);
        }
        return usuarios;        
    }

    private static String parser_nombres(String parametro) {
        String[] nombres = parametro.split(" ");
        String nombre_apellido = "%";
        for (int idx = 0; idx < nombres.length; idx++) {
            nombre_apellido += nombres[idx] + "%";
        }
        return nombre_apellido;
    }

    public static List< AC_Usuario > buscar_evaluador_por_nombre_apellido(String parametro, String id_evento) {
        String nombre_apellido = parser_nombres(parametro);
        String sql = "SELECT * FROM ac_usuario us, ac_usuario_perfil up WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = "+AC_Perfil.REVISOR+" AND CONCAT(us.us_nombre, ' ', us.us_apellido) LIKE '"+nombre_apellido+"' AND up.up_id_usuario_perfil NOT IN (SELECT ref_usuario_perfil FROM CE_Evaluador_Evento WHERE ref_evento=" + id_evento + ");";
        return buscar_usuario_x_perfil_por_nombre_apellido( sql );
    }
    
    public static List< AC_Usuario > buscar_conferencista_por_nombre_apellido(String parametro, String id_evento) {
        String nombre_apellido = parser_nombres(parametro);
        String sql = "SELECT * FROM ac_usuario us, ac_usuario_perfil up WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = "+AC_Perfil.CONFERENCISTA+" AND CONCAT(us.us_nombre, ' ', us.us_apellido) LIKE '"+nombre_apellido+"' AND up.up_id_usuario_perfil NOT IN (SELECT ref_usuario_perfil FROM AE_Conferencista_Evento WHERE ref_evento=" + id_evento + ");";
        return buscar_usuario_x_perfil_por_nombre_apellido( sql );
    }

    public static List< AC_Usuario > buscar_usuario_por_nombre_apellido(String parametro, String modulo) {
        String nombre_apellido = parser_nombres(parametro);
        String sql = "SELECT * FROM ac_usuario WHERE CONCAT(us_nombre, ' ', us_apellido) LIKE '"+nombre_apellido+"';";
        return buscar_usuario_x_perfil_por_nombre_apellido( sql );
    }

    private static AC_Usuario buscar_usuario_x_perfil_por_email(String sql) {
        AC_Usuario usuario_nuevo = new AC_Usuario();
        Datos dato = new Datos("WebSAE");
        
        Registro registros = dato.consulta( sql );
        for (int i=0; i<registros.size(); i++) {
            Table objeto = registros.get(i);
            usuario_nuevo.cr_AC_Usuario(objeto, true);
        }
        return usuario_nuevo;
    }

    public static AC_Usuario buscar_evaluador_por_email(String email, String id_evento) {
        String sql = "SELECT * FROM AC_Usuario us, AC_Usuario_Perfil up WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = "+AC_Perfil.REVISOR+" AND us.us_email = '" + email + "' AND up.up_id_usuario_perfil NOT IN (SELECT ref_usuario_perfil FROM CE_Evaluador_Evento WHERE ref_evento=" + id_evento + ");";
        return buscar_usuario_x_perfil_por_email( sql );
    }
    
    public static AC_Usuario buscar_conferencista_por_email(String email, String id_evento) {
        String sql = "SELECT * FROM AC_Usuario us, AC_Usuario_Perfil up WHERE us.us_id_usuario = up.ref_usuario AND up.ref_perfil = "+AC_Perfil.CONFERENCISTA+" AND us.us_email = '" + email + "' AND up.up_id_usuario_perfil NOT IN (SELECT ref_usuario_perfil FROM AE_Conferencista_Evento WHERE ref_evento=" + id_evento + ");";
        return buscar_usuario_x_perfil_por_email( sql );
    }

    public static AC_Usuario buscar_usuario_por_email(String email, String modulo) {
        AC_Usuario usuario_nuevo = new AC_Usuario();
        usuario_nuevo.obtener_clave(modulo, email);

        if (usuario_nuevo.getUs_clave() != null)
            usuario_nuevo.ad_obtener_usuario(modulo, false);
        else
            usuario_nuevo = null;

        return usuario_nuevo;
    }

    public static AC_Usuario buscar_usuario_por_email(String email) {
        AC_Usuario usuario_nuevo = null;
        
        Datos dato = new Datos("WebSAE");
        Registro registros = dato.consulta("call su_obtener_usuario('"+email+"');");
        for (int i=0; i<registros.size(); i++) {
            Table objeto = registros.get(i);
            
            usuario_nuevo = new AC_Usuario();
            usuario_nuevo.setUs_email( (String) objeto.get("us_email") );
            usuario_nuevo.setUs_clave( (String) objeto.get("us_clave") );
        }

        /** Obtengo el usuario con todos los datos necasarios hasta el nivel de los perfiles que tiene */
        if (usuario_nuevo != null) {
            usuario_nuevo.obtener_usuario();
        }
        
        return usuario_nuevo;
    }    

    public static JSONObject obtener_mensaje(AC_Usuario usuario, String lang) {
        JSONObject json = new JSONObject();
        try {
            if (usuario != null) {
                json.put("tipo", "OK");
                json.put("usuario", AC_Usuario.toJSONObject(usuario));
            } else {
                json.put("tipo", "ERROR");
                json.put("mensaje", Lenguaje.ERROR_EMAIL_NOREGISTRADO[ Lenguaje.parse(lang) ]);
            }
        } catch(Exception ex) {
            Logger.getLogger(Mostrar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;        
    }

    public static JSONObject toJSON(List< AC_Usuario > usuarios, int recordsReturned) {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonItems = new JSONArray();
            for (Iterator< AC_Usuario > iter = usuarios.iterator() ; iter.hasNext() ; )
                jsonItems.put( AC_Usuario.toJSONObject( iter.next() ) );
                
            json.put("recordsReturned", recordsReturned);
            json.put("totalRecords", usuarios.size());
            json.put("startIndex", 0);
            json.put("sort", "");
            json.put("dir", "asc");
            json.put("items", jsonItems);
        } catch(Exception ex) {
            Logger.getLogger(Mostrar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

}
