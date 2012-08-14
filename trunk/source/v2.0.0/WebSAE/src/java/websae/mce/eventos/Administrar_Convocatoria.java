
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

import websae.informacion.Estado;
import websae.informacion.Lenguaje;

import websae.mce.dominio.CE_Convocatoria;

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
public class Administrar_Convocatoria {
    private CE_Convocatoria convocatoria;
    private Datos           datos;
    private BigDecimal      id_evento;
    private Respuesta       respuesta;
    private String          tipo_accion;

    public Administrar_Convocatoria(HttpServletRequest request, String id_evento, String tipo) {
        this.convocatoria = new CE_Convocatoria();
        this.convocatoria.setCo_descripcion(request.getParameter("txt_descripcion_es"), Lenguaje.LANG_ES);
        this.convocatoria.setCo_descripcion(request.getParameter("txt_descripcion_en"), Lenguaje.LANG_EN);
        this.convocatoria.setCo_descripcion(request.getParameter("txt_descripcion_pt"), Lenguaje.LANG_PT);
        this.convocatoria.setCo_ruta_formato(request.getParameter("txt_ruta_formato2"));

        String fecha_presentacion_resumen = request.getParameter("txt_fecha_resumen");

        if ((fecha_presentacion_resumen != null) && (fecha_presentacion_resumen.compareTo("") != 0)) {
            this.convocatoria.setCo_fecha_max_presentacion_resumen(request.getParameter("txt_fecha_resumen"));
        } else {
            this.convocatoria.setCo_fecha_max_presentacion_resumen(null);
        }

        this.convocatoria.setCo_fecha_max_presentacion(request.getParameter("txt_fecha_presentacion"));
        this.convocatoria.setCo_fecha_max_evaluacion(request.getParameter("txt_fecha_evaluacion"));
        this.convocatoria.setCo_fecha_max_aceptacion(request.getParameter("txt_fecha_notificacion"));
        this.convocatoria.setCo_fecha_max_correccion(request.getParameter("txt_fecha_correccion"));

        // this.convocatoria.setCo_estado( request.getParameter("rb_status") );
        this.convocatoria.setCo_estado(Estado.VIGENTE);
        this.id_evento   = new BigDecimal(id_evento);
        this.tipo_accion = tipo;
        this.datos       = new Datos("WebSAE");
        this.respuesta   = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_descripcion(Lenguaje.LANG_ES), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_descripcion(Lenguaje.LANG_EN), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_descripcion(Lenguaje.LANG_PT), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_ruta_formato(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_fecha_max_presentacion_resumen(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_fecha_max_presentacion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_fecha_max_evaluacion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_fecha_max_aceptacion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_fecha_max_correccion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.convocatoria.getCo_estado(), new String()));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));
        parametros.add(new Dato(Tipo.OUT, null, new BigDecimal("0")));
        this.respuesta = datos.procedimiento("{call ce_administrar_convocatoria(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                parametros);

        Dato mensaje = this.respuesta.getObjetos().get(0);

        this.respuesta.setMensaje((String) mensaje.getDato());
    }

    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public String getId_Convocatoria() {
        if ((this.respuesta.getMensaje().compareTo("OK:registrar") == 0)
                || (this.respuesta.getMensaje().compareTo("OK:modificar") == 0)) {
            Dato mensaje = null;

            mensaje = this.respuesta.getObjetos().get(1);

            return mensaje.getDato().toString();
        }

        return null;
    }

    public static JSONObject obtener_mensaje(String mensaje, String id_convocatoria, String lang) {
        String valores[] = new String[2];

        valores = mensaje.split(":", 2);

        JSONObject json = new JSONObject();

        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("id_convocatoria", id_convocatoria);
                    json.put("mensaje", Lenguaje.OK_CONVOCATORIA_ACTUALIZAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("id_convocatoria", id_convocatoria);
                    json.put("mensaje", Lenguaje.OK_CONVOCATORIA_ACTUALIZAR[Lenguaje.parse(lang)]);
                }
            } else if (valores[0].equals("ERROR")) {
                json.put("tipo", "ERROR");
                json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[Lenguaje.parse(lang)]);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Convocatoria.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
