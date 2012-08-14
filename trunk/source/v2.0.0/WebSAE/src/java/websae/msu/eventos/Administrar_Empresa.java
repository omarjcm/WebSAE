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
import websae.msu.dominio.SU_Empresa;

/**
 * 
 * @author Guillermo Pizarro
 */
public class Administrar_Empresa {
    private SU_Empresa empresa;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Empresa(HttpServletRequest request, String tipo) {
        this.empresa = new SU_Empresa();
        
        this.empresa.cr_SU_Empresa();
        this.empresa.setEm_id_empresa( new BigDecimal( request.getParameter("txt_id_empresa") ) );
        this.empresa.setEm_nombre( request.getParameter("txt_empresa") );
        this.empresa.setEm_direccion( request.getParameter("txt_direccion") );
        this.empresa.setEm_telefono( request.getParameter("txt_telefono") );
        this.empresa.setEm_codigo_postal( request.getParameter("txt_codigo_postal") );
        this.empresa.setEm_fax( request.getParameter("txt_fax") );
        this.empresa.setEm_logo( request.getParameter("txt_logo2") );
        this.empresa.setEm_web( request.getParameter("txt_url") );
        this.empresa.setEm_siglas( request.getParameter("txt_siglas") );
        this.empresa.ref_ciudad.setCi_id_ciudad( new BigDecimal( request.getParameter("cmb_ciudad_empresa") ) );
        this.empresa.ref_tipo_empresa.setTe_id_tipo_empresa( new BigDecimal( request.getParameter("cmb_tipo_empresa") ) );
        this.empresa.setEm_estado( Estado.VIGENTE );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }
    
    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public BigDecimal getId_Empresa() {
        try {
            return (BigDecimal) this.respuesta.getObjetos().get(1).getDato();
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * <p>Método:</p> registrar_empresa()
     * <p>Precondiciones:</p> nombre != null && estado != null
     */
    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_id_empresa(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_nombre()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_direccion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_telefono(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_codigo_postal(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_fax(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_logo(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_web(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_siglas(), new String()));
        parametros.add(new Dato(Tipo.IN, this.empresa.getEm_estado()));
        parametros.add(new Dato(Tipo.IN, this.empresa.ref_ciudad.getCi_id_ciudad(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.empresa.ref_tipo_empresa.getTe_id_tipo_empresa(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, new String()));
        parametros.add(new Dato(Tipo.OUT, new BigDecimal("0")));
        
        String sql = "{call su_administrar_empresa(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
    }
    
    public static JSONObject obtener_mensaje(String mensaje, BigDecimal id_empresa, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "OK");
                    json.put("id_empresa", id_empresa.toString());
                    json.put("mensaje", Lenguaje.OK_EMPRESA_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EMPRESA_MODIFICAR[ Lenguaje.parse(lang) ]);
                }else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EMPRESA_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMPRESA_REPETIDA[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ASIGNADO_USUARIO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
