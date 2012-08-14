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
import websae.informacion.Constante;
import websae.msu.dominio.SU_Cargo;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Cargo {

    private SU_Cargo cargo;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;

    public Administrar_Cargo(HttpServletRequest request, String tipo) {
        this.cargo = new SU_Cargo();
        
        this.cargo.setCa_id_cargo( new BigDecimal(request.getParameter("txt_id_cargo")) );
        this.cargo.setCa_nombre( request.getParameter("txt_cargo") );
        this.cargo.setCa_estado( Estado.VIGENTE );
        
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
        parametros.add(new Dato(Tipo.IN, this.cargo.getCa_id_cargo(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.cargo.getCa_nombre()));
        parametros.add(new Dato(Tipo.IN, this.cargo.getCa_estado()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        parametros.add(new Dato(Tipo.OUT, new BigDecimal("0")));
        
        String sql = "{call su_administrar_cargo(?,?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
        
        if (this.respuesta.getMensaje().compareTo("OK:registro") == 0) {
            /** Si es un registro, el id del cargo debo buscarlo. */
            if (this.tipo_accion.compareTo( Constante.REGISTRAR ) == 0) {
                Dato id_objeto = this.respuesta.getObjetos().get(1);
                this.cargo.setCa_id_cargo(new BigDecimal( id_objeto.getDato().toString() ));
            }
        }
    }
    
    public BigDecimal getId_Cargo() {
        return this.cargo.getCa_id_cargo();
    }
    
    public static JSONObject obtener_mensaje(String mensaje, BigDecimal id_cargo, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("id_cargo", id_cargo);
                    json.put("mensaje", Lenguaje.OK_CARGO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("id_cargo", id_cargo);
                    json.put("mensaje", Lenguaje.OK_CARGO_MODIFICAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CARGO_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_CARGO_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_CARGO_ASIGNADO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Cargo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
