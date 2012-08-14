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
import websae.informacion.Lenguaje;
import websae.mae.dominio.AE_Categoria_Evento;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Categoria_Evento {

    private AE_Categoria_Evento categoria_evento;
    private BigDecimal id_evento;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Categoria_Evento(HttpServletRequest request, String id_evento, String tipo) {
        this.categoria_evento = new AE_Categoria_Evento();

        this.categoria_evento.setCe_id_categoria_evento( new BigDecimal( request.getParameter("txt_id_precio") ) );
        this.categoria_evento.setCe_precio( Float.parseFloat( request.getParameter("txt_precio") ) );
        this.categoria_evento.setCe_fecha_inicio( request.getParameter("txt_fecha_inicio") );
        this.categoria_evento.setCe_fecha_fin( request.getParameter("txt_fecha_fin") );
        this.categoria_evento.ref_categoria.setCa_id_categoria( new BigDecimal( request.getParameter("cmb_categoria") ) );
        this.id_evento = new BigDecimal( id_evento );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.categoria_evento.getCe_id_categoria_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.categoria_evento.getCe_precio(), new Float("0")));
        parametros.add(new Dato(Tipo.IN, this.categoria_evento.getCe_fecha_inicio(), ""));
        parametros.add(new Dato(Tipo.IN, this.categoria_evento.getCe_fecha_fin(), ""));
        parametros.add(new Dato(Tipo.IN, this.categoria_evento.ref_categoria.getCa_id_categoria(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, null, new String()));

        String sql = "{call ae_administrar_categoria_evento(?,?,?,?,?,?,?,?)}";
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
                    json.put("mensaje", Lenguaje.OK_PRECIO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_PRECIO_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_PRECIO_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_PRECIO_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("fuera-rango")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_PRECIO_TRASLAPADO[ Lenguaje.parse(lang) ]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Categoria_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
