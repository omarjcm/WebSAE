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
import websae.mae.dominio.AE_Categoria;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Categoria {

    private AE_Categoria categoria;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Categoria(HttpServletRequest request, String tipo) {
        this.categoria = new AE_Categoria();
        
        this.categoria.setCa_id_categoria(new BigDecimal( (String) request.getParameter("txt_id_categoria") ));
        this.categoria.setCa_nombre( (String) request.getParameter("txt_categoria") );
        this.categoria.setCa_estado( Estado.VIGENTE );
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
        parametros.add(new Dato(Tipo.IN, this.categoria.getCa_id_categoria(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.categoria.getCa_nombre()));
        parametros.add(new Dato(Tipo.IN, this.categoria.getCa_estado()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        String sql = "{call ae_administrar_categoria(?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_CATEGORIA_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CATEGORIA_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CATEGORIA_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_CATEGORIA_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ASIGNADO_EVENTO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
