
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

//~--- non-JDK imports --------------------------------------------------------

import mad.eventos.Transaccion;
import websae.informacion.Constante;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Tipo;
import websae.mac.dominio.AC_Perfil;
import websae.mac.eventos.Administrar_Usuario_Perfil;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Administrador_Evento {
    private BigDecimal  id_evento;
    private BigDecimal  id_usuario;
    private BigDecimal  id_usuario_perfil;
    
    private Transaccion transaccion;
    private String tipo_accion;
    private String respuesta;

    public Administrar_Administrador_Evento(Transaccion transaccion, BigDecimal id_evento, BigDecimal id_usuario, BigDecimal id_usuario_perfil, String tipo_accion) {
        this.id_evento = id_evento;
        this.id_usuario_perfil = id_usuario_perfil;
        this.id_usuario = id_usuario;
        
        this.transaccion = transaccion;
        this.tipo_accion = tipo_accion;
    }
    
    public String procesar_peticion() {
        if (this.tipo_accion.compareTo( Constante.REGISTRAR ) == 0) {
            registrar_administrador_evento();
        } else if (this.tipo_accion.compareTo( Constante.ELIMINAR ) == 0) {
            eliminar_administrador_evento();
        }
        return this.respuesta;
    }
    
    private void registrar_administrador_evento() {
        try {
            if (this.id_usuario_perfil == null) {
                Administrar_Usuario_Perfil registro = new Administrar_Usuario_Perfil(this.transaccion, new BigDecimal(AC_Perfil.ADMINISTRADOR), this.id_usuario);
                this.respuesta = registro.procesar_peticion();
                this.id_usuario_perfil = registro.getId_usuario_perfil();
            }
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, id_usuario_perfil));
            parametros.add(new Dato(Tipo.IN, this.id_evento));
            String sql     = "INSERT INTO CE_Administrador_Evento (ref_usuario_perfil, ref_evento) VALUES (?,?)";
            this.respuesta = this.transaccion.actualizar(sql, parametros);
            
            this.respuesta = "OK:registrar";
        } catch (Exception ex) {
            this.respuesta = "ERROR:" + ex.getMessage();
            this.transaccion.rollback();
        }
    }
    
    private void eliminar_administrador_evento() {
        try {
            
            
            this.respuesta = "OK:registrar";
        } catch (Exception ex) {
            this.respuesta = "ERROR:" + ex.getMessage();
            this.transaccion.rollback();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
