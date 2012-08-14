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
import websae.informacion.Lenguaje;
import websae.msu.informacion.SU_Tipo_Empresa_Cargo;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Tipo_Empresa_Cargo {

    private SU_Tipo_Empresa_Cargo tipo_empresa_cargo;
    private Datos datos;
    private Respuesta respuesta;
    
    public Administrar_Tipo_Empresa_Cargo(HttpServletRequest request) {
        this.tipo_empresa_cargo = new SU_Tipo_Empresa_Cargo();
        
        this.tipo_empresa_cargo.setTe_estado( request.getParameter("estado") );
        this.tipo_empresa_cargo.ref_tipo_empresa.setTe_id_tipo_empresa( new BigDecimal( request.getParameter("id_tipo_empresa") ) );
        this.tipo_empresa_cargo.ref_cargo.setCa_id_cargo( new BigDecimal( request.getParameter("id_cargo") ) );
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa_cargo.getTe_estado()));
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa_cargo.ref_tipo_empresa.getTe_id_tipo_empresa()));
        parametros.add(new Dato(Tipo.IN, this.tipo_empresa_cargo.ref_cargo.getCa_id_cargo()));
        
        String sql = "{call su_asignar_tipo_empresa_cargo(?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.OK_CARGO_REGISTRAR[ Lenguaje.parse(lang) ]);
            } else if (valores[0].equals("ERROR")) {
                json.put("tipo", "ERROR");
                json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Tipo_Empresa_Cargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
