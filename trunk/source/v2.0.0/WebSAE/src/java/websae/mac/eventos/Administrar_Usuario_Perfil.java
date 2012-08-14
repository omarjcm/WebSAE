/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websae.mac.eventos;

import java.math.BigDecimal;
import mad.eventos.Transaccion;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Tipo;
import websae.informacion.Estado;
import websae.informacion.Funciones;
import websae.mac.dominio.AC_Usuario_Perfil;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Usuario_Perfil {

    private BigDecimal id_perfil;
    private BigDecimal id_usuario;
    private BigDecimal id_usuario_perfil;
    private Transaccion transaccion;
    private String respuesta;

    public Administrar_Usuario_Perfil(Transaccion transaccion, BigDecimal id_perfil, BigDecimal id_usuario) {
        this.id_perfil = id_perfil;
        this.id_usuario = id_usuario;
        this.transaccion = transaccion;
    }
    
    public Administrar_Usuario_Perfil(Transaccion transaccion, BigDecimal id_usuario_perfil) {
        this.id_usuario_perfil = id_usuario_perfil;
        this.transaccion = transaccion;
    }

    public String procesar_peticion() {
        registrar_usuario_perfil();
        return this.respuesta;
    }

    private void actualizar_usuario_perfil() {
        try {
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.VIGENTE));
            parametros.add(new Dato(Tipo.IN, this.id_usuario));
            parametros.add(new Dato(Tipo.IN, this.id_perfil));
            
            String sql = "UPDATE AC_Usuario_Perfil SET up_estado = ? WHERE ref_usuario = ? AND ref_perfil = ?;";
            this.respuesta = this.transaccion.actualizar(sql, parametros);
        } catch (Exception ex) {
            this.respuesta = "ERROR:"+ex.getMessage();
            this.transaccion.rollback();
        }
    }
    
    private void insertar_usuario_perfil() {
        try {
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.VIGENTE));
            parametros.add(new Dato(Tipo.IN, this.id_usuario));
            parametros.add(new Dato(Tipo.IN, this.id_perfil));

            String sql = "INSERT INTO AC_Usuario_Perfil (up_estado, ref_usuario, ref_perfil) VALUES (?,?,?);";
            this.respuesta = this.transaccion.actualizar(sql, parametros);
        } catch (Exception ex) {
            this.respuesta = "ERROR:"+ex.getMessage();
            this.transaccion.rollback();
        }
    }

    private BigDecimal obtener_id_usuario_perfil() {
        String sql = "SELECT up_id_usuario_perfil AS id FROM AC_Usuario_Perfil WHERE ref_usuario = " + this.id_usuario + " AND ref_perfil = " + this.id_perfil + ";";
        this.id_usuario_perfil = Funciones.obtener_id(this.transaccion, sql, "id");
        return this.id_usuario_perfil;
    }

    private void registrar_usuario_perfil() {
        try {
            if (this.id_usuario_perfil == null) this.id_usuario_perfil = AC_Usuario_Perfil.id_usuario_perfil(this.transaccion, this.id_usuario, this.id_perfil.toString());
            
            if (this.id_usuario_perfil == null) {
                /** PASO 1a: Preguntar si existe. */
                this.id_usuario_perfil = obtener_id_usuario_perfil();
                /** PASO 1b: Si existe, ya se tiene el id deseado y se actualiza el estado a VIGENTE. */
                if (this.id_usuario_perfil != null) {
                    actualizar_usuario_perfil();
                } else {
                    /** PASO 1c: Si no existe, registrar el correspondiente dato. */
                    insertar_usuario_perfil();
                    /** PASO 1ca: Obtener el correspondiente ID. */
                    this.id_usuario_perfil = obtener_id_usuario_perfil();
                }
            }
        } catch (Exception ex) {
            this.respuesta = "ERROR:"+ex.getMessage();
            this.transaccion.rollback();
        }
    }
    
    public BigDecimal getId_usuario_perfil() {
        return this.id_usuario_perfil;
    }
}
