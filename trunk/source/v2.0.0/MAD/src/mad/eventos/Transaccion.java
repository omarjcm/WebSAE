/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mad.eventos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.bd.ConexionAD;
import mad.objetos.Parametros;
import mad.objetos.Registro;

/** Clase proxy que permite las operaciones de transacció en una Base de Datos.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Transaccion {

    private String modulo;
    private ConexionAD BD;
    
    public Transaccion(String modulo) {
        this.modulo = modulo;
        this.BD = new ConexionAD(this.modulo);
    }

    public void iniciar() {
        try {
            this.BD.conectar();
            this.BD.getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String actualizar(String sql, Parametros objetos) {
        return this.BD.ejecutar_actualizacion(this.BD.getConnection(), sql, objetos);
    }

    public Registro consultar(String sql) {
        return this.BD.ejecutar_consulta(this.BD.getConnection(), sql);
    }

    public void rollback() {
        try {
            this.BD.getConnection().rollback();
            this.BD.getConnection().setAutoCommit( true );
        } catch (SQLException ex) {
            if (this.BD.getConnection() != null) {
                try {
                    this.BD.getConnection().rollback();
                    this.BD.getConnection().setAutoCommit( true );
                } catch (SQLException ex1) {
                    Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.BD.cerrar();
    }

    public void commit() {
        try {
            this.BD.getConnection().commit();
            this.BD.getConnection().setAutoCommit( true );
        } catch (SQLException ex) {
            if (this.BD.getConnection() != null) {
                try {
                    this.BD.getConnection().rollback();
                    this.BD.getConnection().setAutoCommit( true );
                } catch (SQLException ex1) {
                    Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.BD.cerrar();
    }
}
