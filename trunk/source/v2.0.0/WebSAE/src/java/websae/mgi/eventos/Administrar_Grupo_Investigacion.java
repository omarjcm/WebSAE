/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mgi.eventos;

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
import websae.mgi.dominio.GI_Grupo_Investigacion;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Grupo_Investigacion {

    private GI_Grupo_Investigacion grupo_investigacion;
    private BigDecimal id_evento;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Grupo_Investigacion(HttpServletRequest request, String tipo) {
        this.grupo_investigacion = new GI_Grupo_Investigacion();

        this.grupo_investigacion.setGi_id_grupo_investigacion( new BigDecimal( request.getParameter("txt_id_grupo_investigacion") ) );
        this.grupo_investigacion.setGi_nombre( request.getParameter("txt_grupo_investigacion") );
        this.grupo_investigacion.setGi_objetivo( request.getParameter("txt_objetivo") );
        this.grupo_investigacion.setGi_logo( request.getParameter("txt_imagen2") );
        this.grupo_investigacion.setGi_web( request.getParameter("txt_url") );
        this.grupo_investigacion.setGi_telefono( request.getParameter("txt_telefono") );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_id_grupo_investigacion(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_nombre(), new String()));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_objetivo(), new String()));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_logo(), new String()));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_web(), new String()));
        parametros.add(new Dato(Tipo.IN, this.grupo_investigacion.getGi_telefono(), new String()));
        parametros.add(new Dato(Tipo.OUT, null, new String()));

        String sql = "{call gi_administrar_grupo_investigacion(?,?,?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_GRUPO_INVESTIGACION_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_GRUPO_INVESTIGACION_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_GRUPO_INVESTIGACION_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_GRUPO_INVESTIGACION_REPETIDO[ Lenguaje.parse(lang) ]);
                }else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_GRUPO_INVESTIGACION_ASIGNADO[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Grupo_Investigacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
