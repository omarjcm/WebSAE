/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.eventos;

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
import websae.msu.dominio.SU_Tipo_Empresa;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Tipo_Empresa {

    private SU_Tipo_Empresa tipo_empresa;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Tipo_Empresa(HttpServletRequest request, String tipo) {
        this.tipo_empresa = new SU_Tipo_Empresa();

        this.tipo_empresa.setTe_id_tipo_empresa(new BigDecimal( (String) request.getParameter("txt_id_tipo_empresa") ));
        this.tipo_empresa.setTe_nombre( (String) request.getParameter("txt_tipo_empresa") );
        this.tipo_empresa.setTe_estado( Estado.VIGENTE );
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
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa.getTe_id_tipo_empresa(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa.getTe_nombre()));
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa.getTe_estado()));
        parametros.add(new Dato(Tipo.OUT, new String()));

        String sql = "{call su_administrar_tipo_empresa(?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_TIPO_EMPRESA_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_TIPO_EMPRESA_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_TIPO_EMPRESA_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TIPO_EMPRESA_REPETIDA[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ASIGNADO_EMPRESA[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Tipo_Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
