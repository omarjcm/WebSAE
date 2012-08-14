
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

import websae.mce.dominio.CE_Tema_Articulo;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Tema_Articulo {
    private BigDecimal       id_articulo;
    private String           respuesta;
    private CE_Tema_Articulo tema_articulo;
    private String           tipo_accion;
    private Transaccion      transaccion;

    public Administrar_Tema_Articulo(Transaccion transaccion, BigDecimal id_articulo, String tipo) {
        this.tipo_accion = tipo;
        this.transaccion = transaccion;
        this.id_articulo = id_articulo;
    }

    public Administrar_Tema_Articulo(Transaccion transaccion, CE_Tema_Articulo tema_articulo, BigDecimal id_articulo,
                                     String tipo) {
        this.tipo_accion   = tipo;
        this.transaccion   = transaccion;
        this.tema_articulo = tema_articulo;
        this.id_articulo   = id_articulo;
    }

    public String procesar_peticion() {
        Parametros parametros = new Parametros();

        if (this.tipo_accion.compareTo(Constante.AGREGAR) == 0) {
            parametros.add(new Dato(Tipo.IN, this.tema_articulo.ref_tema.getTe_id_tema(), new BigDecimal(0)));
            parametros.add(new Dato(Tipo.IN, this.id_articulo, new BigDecimal(0)));

            String sql = "INSERT INTO CE_Tema_Articulo (ref_tema, ref_articulo) VALUES (?,?)";

            this.respuesta = this.transaccion.actualizar(sql, parametros);
        } else if (this.tipo_accion.compareTo(Constante.ELIMINAR) == 0) {
            parametros.add(new Dato(Tipo.IN, this.id_articulo, new BigDecimal(0)));

            String sql = "DELETE FROM CE_Tema_Articulo WHERE ref_articulo = ?;";

            this.respuesta = this.transaccion.actualizar(sql, parametros);
        }

        return this.respuesta;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
