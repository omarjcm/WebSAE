/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mce.eventos;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Funciones;
import websae.informacion.Lenguaje;
import websae.mce.dominio.CE_Evaluacion;
import websae.mce.dominio.CE_Tipo_Pregunta;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_CE_Evaluacion {

    private BigDecimal id_pregunta;
    private BigDecimal tipo_pregunta;
    private BigDecimal id_respuesta;
    private BigDecimal id_seccion;
    private String respuesta;
    private CE_Evaluacion evaluacion;
    private HttpSession sesion;
    
    private String mensaje;

    public Administrar_CE_Evaluacion(HttpServletRequest request) {
        this.sesion = request.getSession();
        this.evaluacion = (CE_Evaluacion) this.sesion.getAttribute("evaluacion");
        
        this.id_seccion = Funciones.getBigDecimal( request.getParameter("id_seccion") );
        this.id_pregunta = Funciones.getBigDecimal( request.getParameter("id_pregunta") );
        this.tipo_pregunta = Funciones.getBigDecimal( request.getParameter("tipo_pregunta") );
        
        this.id_respuesta = Funciones.getBigDecimal( request.getParameter("id_respuesta") );
        this.respuesta = request.getParameter("txt_respuesta");
    }
    
    public void procesar_peticion() {
        this.evaluacion.registrar_pregunta(this.id_seccion, this.id_pregunta, this.tipo_pregunta, this.id_respuesta, this.respuesta);
        this.sesion.setAttribute("evaluacion", this.evaluacion);
    }
    
    public String getMensaje() {
        return this.mensaje;
    }
    
    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_AUTOR_ARTICULO_REPETIDO[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("no existe")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_AUTOR_ARTICULO_NO_EXISTE[Lenguaje.parse(lang)]);
                } else {
                    json.put("tipo", "ERROR");
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_CE_Evaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}
