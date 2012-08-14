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
import websae.informacion.Constante;
import websae.mae.dominio.AE_Organizador;
import websae.mgi.dominio.GI_Grupo_Investigacion;
import websae.msu.dominio.SU_Empresa;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Organizador {
    
    private AE_Organizador organizador;
    private BigDecimal id_evento;
    
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Organizador(HttpServletRequest request, String id_evento, String tipo) {
        this.organizador = new AE_Organizador();

        this.organizador.setOr_id_organizador( new BigDecimal( request.getParameter("id_organizador") ) );
        this.organizador.setOr_tipo( request.getParameter("txt_tipo") );

        if (this.organizador.getOr_tipo().compareTo( Constante.GRUPO_INVESTIGACION ) == 0) {
            this.organizador.ref_grupo_investigacion = new GI_Grupo_Investigacion();
            this.organizador.getRef_grupo_investigacion().setGi_id_grupo_investigacion( new BigDecimal( request.getParameter("txt_id_grupo_investigacion") ) );
        } else if (this.organizador.getOr_tipo().compareTo( Constante.EMPRESA_UNIVERSIDAD ) == 0) {
            this.organizador.ref_empresa = new SU_Empresa();
            this.organizador.getRef_empresa().setEm_id_empresa( new BigDecimal( request.getParameter("id_empresa") ) );
        }
        this.id_evento = new BigDecimal( id_evento );
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public String getMensaje() {
        return this.respuesta.getMensaje();
    }
    
    public void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.organizador.getOr_id_organizador(), new BigDecimal("0")));
        if ( this.organizador.getOr_tipo().compareTo( Constante.EMPRESA_UNIVERSIDAD ) == 0 ) {
            parametros.add(new Dato(Tipo.IN, null, new BigDecimal("0")));
            parametros.add(new Dato(Tipo.IN, this.organizador.getRef_empresa().getEm_id_empresa(), new BigDecimal("0")));
        } else if ( this.organizador.getOr_tipo().compareTo( Constante.GRUPO_INVESTIGACION ) == 0 ) {
            parametros.add(new Dato(Tipo.IN, this.organizador.getRef_grupo_investigacion().getGi_id_grupo_investigacion(), new BigDecimal("0")));
            parametros.add(new Dato(Tipo.IN, null, new BigDecimal("0")));
        }
        parametros.add(new Dato(Tipo.IN, this.organizador.getOr_tipo(), new String()));
        parametros.add(new Dato(Tipo.IN, this.id_evento, new BigDecimal("0")));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        String sql = "{call ae_administrar_organizador(?,?,?,?,?,?,?)}";
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
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ORGANIZADOR_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ORGANIZADOR_ELIMINAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ORGANIZADOR_REPETIDO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Organizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
