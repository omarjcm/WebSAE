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
import websae.mae.dominio.AE_Faq;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Faq {

    private AE_Faq faq;
    private String id_evento;
    private String tipo_accion;
    private Datos datos;
    private Respuesta respuesta;
    
    public Administrar_Faq(HttpServletRequest request, String id_evento, String tipo) {
        this.faq = new AE_Faq();
        
        this.id_evento = id_evento;
        this.faq.setFa_texto( (String) request.getParameter("txt_texto") );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public void procesar_peticion() {
        Parametros parametros = new Parametros();

        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, new BigDecimal( this.id_evento ), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.faq.getFa_texto()));
        
        String sql = "{call ae_administrar_faq(?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
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
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.OK_FAQ_MODIFICAR[ Lenguaje.parse(lang) ]);
            } else if (valores[0].equals("ERROR")) {
                json.put("tipo", "ERROR");
                json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Faq.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }    
}
