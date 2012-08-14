
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

import websae.informacion.Funciones;
import websae.informacion.Lenguaje;

import websae.mce.dominio.CE_Pregunta;

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
public class Administrar_Pregunta {
    private Datos       datos;
    private BigDecimal  id_seccion;
    private BigDecimal  id_tipo_pregunta;
    private CE_Pregunta pregunta;
    private Respuesta   respuesta;
    private String      tipo_accion;

    public Administrar_Pregunta(HttpServletRequest request, String tipo) {
        this.pregunta = new CE_Pregunta();
        this.pregunta.setPr_id_pregunta(new BigDecimal(request.getParameter("txt_id_pregunta")));
        this.pregunta.setPr_nombre(request.getParameter("txt_pregunta"));
        this.pregunta.setPr_orden(Funciones.getInteger(request.getParameter("txt_orden")));
        this.id_seccion       = new BigDecimal(request.getParameter("id_seccion"));
        this.id_tipo_pregunta = new BigDecimal(request.getParameter("rb_tipo_pregunta"));
        this.tipo_accion      = tipo;
        this.datos            = new Datos("WebSAE");
        this.respuesta        = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.pregunta.getPr_id_pregunta(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.pregunta.getPr_nombre(), new String("")));
        parametros.add(new Dato(Tipo.IN, this.pregunta.getPr_orden(), new Integer(0)));
        parametros.add(new Dato(Tipo.IN, this.id_seccion, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.id_tipo_pregunta, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));
        this.respuesta = datos.procedimiento("{call ce_administrar_pregunta(?,?,?,?,?,?,?)}", parametros);

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
                    json.put("mensaje", Lenguaje.OK_PREGUNTA_REGISTRAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_PREGUNTA_MODIFICAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_PREGUNTA_ELIMINAR[Lenguaje.parse(lang)]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.ERROR_PREGUNTA_REPETIDO[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.ERROR_PREGUNTA_ASIGNADO[Lenguaje.parse(lang)]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[Lenguaje.parse(lang)]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Pregunta.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
