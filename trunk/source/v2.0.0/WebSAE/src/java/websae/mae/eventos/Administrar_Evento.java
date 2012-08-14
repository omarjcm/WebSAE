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
import websae.informacion.Funciones;
import websae.mae.dominio.AE_Evento;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Evento {
    
    private AE_Evento evento;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Evento(HttpServletRequest request, String tipo) {
        this.evento = new AE_Evento();
        
        this.evento.setEv_id_evento( Funciones.getBigDecimal( request.getParameter("txt_id_evento") ) );
        this.evento.setEv_nombre( request.getParameter("txt_evento_es"), Lenguaje.LANG_ES );
        this.evento.setEv_nombre( request.getParameter("txt_evento_en"), Lenguaje.LANG_EN );
        this.evento.setEv_nombre( request.getParameter("txt_evento_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_lugar( request.getParameter("txt_lugar") );
        this.evento.setEv_objetivo( request.getParameter("txt_objetivo_es"), Lenguaje.LANG_ES );
        this.evento.setEv_objetivo( request.getParameter("txt_objetivo_en"), Lenguaje.LANG_EN );
        this.evento.setEv_objetivo( request.getParameter("txt_objetivo_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_descripcion( request.getParameter("txt_descripcion_es"), Lenguaje.LANG_ES );
        this.evento.setEv_descripcion( request.getParameter("txt_descripcion_en"), Lenguaje.LANG_EN );
        this.evento.setEv_descripcion( request.getParameter("txt_descripcion_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_dirigido( request.getParameter("txt_dirigido_es"), Lenguaje.LANG_ES );
        this.evento.setEv_dirigido( request.getParameter("txt_dirigido_en"), Lenguaje.LANG_EN );
        this.evento.setEv_dirigido( request.getParameter("txt_dirigido_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_fecha_inicio( request.getParameter("txt_fecha_inicio") );
        this.evento.setEv_fecha_fin( request.getParameter("txt_fecha_fin") );
        this.evento.setEv_slogan( request.getParameter("txt_slogan_es"), Lenguaje.LANG_ES );
        this.evento.setEv_slogan( request.getParameter("txt_slogan_en"), Lenguaje.LANG_EN );
        this.evento.setEv_slogan( request.getParameter("txt_slogan_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_imagen( request.getParameter("txt_imagen2") );
        this.evento.setEv_email( request.getParameter("txt_email") );
        this.evento.setEv_agenda_general( request.getParameter("txt_agenda_general") );
        this.evento.setEv_estado( request.getParameter("rb_status") );
        this.evento.setEv_descripcion_registro( request.getParameter("txt_descripcion_registro_es"), Lenguaje.LANG_ES );
        this.evento.setEv_descripcion_registro( request.getParameter("txt_descripcion_registro_en"), Lenguaje.LANG_EN );
        this.evento.setEv_descripcion_registro( request.getParameter("txt_descripcion_registro_pt"), Lenguaje.LANG_PT );
        this.evento.setEv_estado_registro( request.getParameter("rb_estado_registro") );
        this.evento.ref_tipo_evento.setTe_id_tipo_evento( new BigDecimal( request.getParameter("cmb_tipo_evento") ) );
        this.evento.ref_ciudad.setCi_id_ciudad( new BigDecimal( request.getParameter("cmb_ciudad") ) );
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
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_id_evento(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_nombre( Lenguaje.LANG_ES )));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_nombre( Lenguaje.LANG_EN )));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_nombre( Lenguaje.LANG_PT )));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_lugar(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_objetivo( Lenguaje.LANG_ES ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_objetivo( Lenguaje.LANG_EN ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_objetivo( Lenguaje.LANG_PT ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion( Lenguaje.LANG_ES ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion( Lenguaje.LANG_EN ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion( Lenguaje.LANG_PT ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_dirigido( Lenguaje.LANG_ES ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_dirigido( Lenguaje.LANG_EN ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_dirigido( Lenguaje.LANG_PT ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_fecha_inicio(), "2009-05-10"));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_fecha_fin(), "2009-05-10"));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_slogan( Lenguaje.LANG_ES ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_slogan( Lenguaje.LANG_EN ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_slogan( Lenguaje.LANG_PT ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_imagen(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_email(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_agenda_general(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_estado()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion_registro( Lenguaje.LANG_ES ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion_registro( Lenguaje.LANG_EN ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_descripcion_registro( Lenguaje.LANG_PT ), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.getEv_estado_registro(), new String()));
        parametros.add(new Dato(Tipo.IN, this.evento.ref_tipo_evento.getTe_id_tipo_evento()));
        parametros.add(new Dato(Tipo.IN, this.evento.ref_ciudad.getCi_id_ciudad()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        String sql = "{call ae_administrar_evento(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                if (valores[1].equals("registro")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EVENTO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_EVENTO_MODIFICAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EVENTO_REPETIDO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}