
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

//~--- non-JDK imports --------------------------------------------------------

import mad.eventos.Transaccion;

import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Tipo;

import websae.informacion.Constante;
import websae.informacion.Estado;
import websae.informacion.Funciones;

import websae.mac.dominio.AC_Perfil;

import websae.mce.dominio.CE_Autor_Articulo;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;
import java.math.BigInteger;
import websae.mac.eventos.Administrar_Usuario_Perfil;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Autor_Articulo {
    private CE_Autor_Articulo autor_articulo;
    private BigDecimal        id_articulo;
    private String            respuesta;
    private String            tipo_accion;
    private Transaccion       transaccion;

    public Administrar_Autor_Articulo(Transaccion transaccion, BigDecimal id_articulo, String tipo) {
        this.tipo_accion = tipo;
        this.transaccion = transaccion;
        this.id_articulo = id_articulo;
    }

    public Administrar_Autor_Articulo(Transaccion transaccion, CE_Autor_Articulo autor_articulo,
                                      BigDecimal id_articulo, String tipo) {
        this.tipo_accion    = tipo;
        this.transaccion    = transaccion;
        this.autor_articulo = autor_articulo;
        this.id_articulo    = id_articulo;
    }

    public String procesar_peticion() {
        Parametros parametros;

        if (this.tipo_accion.compareTo(Constante.AGREGAR) == 0) {
            Administrar_Usuario_Perfil registro = new Administrar_Usuario_Perfil(this.transaccion, new BigDecimal(AC_Perfil.AUTOR), this.autor_articulo.ref_usuario_perfil.ref_usuario.getUs_id_usuario());
            this.respuesta = registro.procesar_peticion();
            BigDecimal id_usuario_perfil = registro.getId_usuario_perfil();
            
            /** Registrar el correspondiente dato. */
            parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.autor_articulo.getAa_autor_principal(), new Boolean(true)));
            parametros.add(new Dato(Tipo.IN, id_usuario_perfil, new BigDecimal(0)));
            parametros.add(new Dato(Tipo.IN, this.id_articulo, new BigDecimal(0)));
            String sql = "INSERT INTO CE_Autor_Articulo (aa_autor_principal, ref_usuario_perfil, ref_articulo) VALUES (?,?,?);";
            this.respuesta = this.transaccion.actualizar(sql, parametros);
        } else if (this.tipo_accion.compareTo(Constante.ELIMINAR) == 0) {
            parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.id_articulo, new BigDecimal(0)));
            
            String sql = "DELETE FROM CE_Autor_Articulo WHERE ref_articulo = ?;";
            
            this.respuesta = this.transaccion.actualizar(sql, parametros);
        }
        return this.respuesta;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
