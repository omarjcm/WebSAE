
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

//~--- non-JDK imports --------------------------------------------------------

import mad.eventos.Datos;

import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;

import org.json.JSONException;
import org.json.JSONObject;

import websae.informacion.Lenguaje;

import websae.mce.dominio.CE_Evaluacion;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Evaluacion {
    private Datos         datos;
    private CE_Evaluacion evaluacion;
    private BigDecimal    id_evento;
    private Respuesta     respuesta;
    private String        tipo_accion;

    public Administrar_Evaluacion(HttpServletRequest request, String id_evento, String tipo) {
        this.evaluacion = new CE_Evaluacion();
        this.evaluacion.setEv_mensaje(request.getParameter("txt_mensaje"));
        this.evaluacion.setEv_descripcion(request.getParameter("txt_instruccion"));
        this.evaluacion.setEv_estado(request.getParameter("rb_status"));
        this.id_evento   = new BigDecimal(id_evento);
        this.tipo_accion = tipo;
        this.datos       = new Datos("WebSAE");
        this.respuesta   = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.evaluacion.getEv_mensaje(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evaluacion.getEv_descripcion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evaluacion.getEv_estado(), new String()));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));

        String sql = "{call ce_administrar_evaluacion(?,?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
    }

    public String getRespuesta() {
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
                    json.put("mensaje", Lenguaje.OK_EVALUACION_ACTUALIZAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EVALUACION_ACTUALIZAR[Lenguaje.parse(lang)]);
                }
            } else if (valores[0].equals("ERROR")) {
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.ERROR_TEMA_REPETIDO[Lenguaje.parse(lang)]);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Evaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
