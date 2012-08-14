/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.eventos;

import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Registro;
import mad.objetos.Respuesta;
import mad.objetos.Table;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Lenguaje;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class Modificar_Clave {
    
    private String clave_antigua;
    private String clave_actual;
    private Respuesta respuesta;
    
    public Modificar_Clave(String clave_antigua, String clave_actual) {
        this.clave_antigua = clave_antigua;
        this.clave_actual = clave_actual;
        this.respuesta = new Respuesta();
    }

    public String getMensaje() {
        return this.respuesta.getMensaje();
    }

    public void modificar_clave(String email) {
        /** PASO 1: Realizar la comparacion de la clave del Usuario con la antigua */
        AC_Usuario usuario = null;
        /** Realizar la busqueda de la clave del Usuario */
        Datos datos = new Datos("WebSAE");
        Registro registros = datos.consulta("{call su_obtener_usuario('"+email+"')}");
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
        if (usuario == null || (usuario != null && usuario.getUs_clave().compareTo(this.clave_antigua) != 0)) {
            this.respuesta.setMensaje("ERROR:busqueda");
            return;
        }
        
        /** PASO 2: Modificación de la clave del Usuario */
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.clave_antigua));
        parametros.add(new Dato(Tipo.IN, this.clave_actual));
        parametros.add(new Dato(Tipo.IN, usuario.getUs_email()));
        
        String sql = "{call su_modificar_clave(?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
    }
    
    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.OK_CLAVE_MODIFICAR[ Lenguaje.parse(lang) ]);
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("busqueda")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_CLAVE_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Modificar_Clave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
