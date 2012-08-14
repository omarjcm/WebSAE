/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mae.eventos;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Estado;
import websae.informacion.Lenguaje;
import websae.mae.dominio.AE_Material;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Material {

    private AE_Material material;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Material(HttpServletRequest request, String tipo, String id_evento) {
        this.material = new AE_Material();
        
        this.material.setMa_id_material(new BigDecimal( request.getParameter("txt_id_material") ));
        this.material.setMa_precio( Float.parseFloat( request.getParameter("txt_precio") ) );
        this.material.setMa_descripcion( request.getParameter("txt_descripcion") );
        this.material.setMa_cantidad_entregar( Integer.parseInt( request.getParameter("txt_cantidad") ) );
        this.material.setMa_estado( Estado.VIGENTE );
        this.material.ref_tipo_material.setTm_id_tipo_material(new BigDecimal( request.getParameter("cmb_tipo_material") ));
        this.material.ref_evento.setEv_id_evento(new BigDecimal( id_evento ));
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
        parametros.add(new Dato(Tipo.IN, this.material.getMa_id_material(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.material.getMa_precio(), new Float(0)));
        parametros.add(new Dato(Tipo.IN, this.material.getMa_descripcion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.material.getMa_cantidad_entregar(), new Integer(0)));
        parametros.add(new Dato(Tipo.IN, this.material.getMa_estado()));
        parametros.add(new Dato(Tipo.IN, this.material.ref_tipo_material.getTm_id_tipo_material()));
        parametros.add(new Dato(Tipo.IN, this.material.ref_evento.getEv_id_evento()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        String sql = "{call ae_administrar_material(?,?,?,?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_MATERIAL_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_MATERIAL_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_MATERIAL_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_MATERIAL_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ASIGNADO_EVENTO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Material.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
