/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mae.eventos;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Estado;
import websae.informacion.Lenguaje;
import websae.informacion.Constante;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Evento_Opcion {
    
    private BigDecimal id_evento;
    private BigDecimal id_opcion;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Evento_Opcion(String id_evento, String id_opcion, String tipo) {
        this.id_evento = new BigDecimal( id_evento );
        this.id_opcion = new BigDecimal( id_opcion );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();        
    }

    public void actualizar_evento_opcion() {
        Parametros parametros = new Parametros();
        parametros.add(new Dato(Tipo.IN, this.id_evento ));
        parametros.add(new Dato(Tipo.IN, this.id_opcion ));
        if (this.tipo_accion.compareTo( Constante.HABILITAR ) == 0)
            parametros.add(new Dato(Tipo.IN, Estado.VIGENTE ));
        else if (this.tipo_accion.compareTo( Constante.DESHABILITAR ) == 0)
            parametros.add(new Dato(Tipo.IN, Estado.ELIMINADO ));
        
        String sql = "{call ae_actualizar_asignacion_evento_opcion(?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
    }

    public String getMensaje() {
        return this.respuesta.getMensaje();
    }
    
    public String getTipo() {
        return this.tipo_accion;
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
                json.put("mensaje", Lenguaje.OK_MENU_OPCIONES_MODIFICAR[ Lenguaje.parse(lang) ]);
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_MENU_OPCIONES_MODIFICAR[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Evento_Opcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
