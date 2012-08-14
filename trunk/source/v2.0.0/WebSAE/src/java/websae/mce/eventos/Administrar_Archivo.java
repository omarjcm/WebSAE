
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
import websae.informacion.Funciones;

import websae.mce.dominio.CE_Archivo;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;

import java.util.Calendar;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Archivo {
    private CE_Archivo  archivo;
    private String      respuesta;
    private String      tipo_accion;
    private Transaccion transaccion;

    public Administrar_Archivo(Transaccion transaccion, CE_Archivo archivo, String tipo) {
        this.tipo_accion = tipo;
        this.transaccion = transaccion;
        this.archivo     = archivo;
    }

    public String procesar_peticion() {
        if (this.tipo_accion.compareTo(Constante.AGREGAR) == 0) {
            String sql = "SELECT MAX(ar_id_archivo) AS id_archivo FROM CE_Archivo WHERE ar_nombre = '"
                         + this.archivo.getAr_nombre() + "' AND ref_articulo = "
                         + this.archivo.ref_articulo.getAr_id_articulo() + ";";

            this.archivo.setAr_id_archivo(Funciones.obtener_id(this.transaccion, sql, "id_archivo"));

            if (this.archivo.getAr_id_archivo() == null) {
                Parametros parametros = new Parametros();

                parametros.add(new Dato(Tipo.IN, this.archivo.getAr_nombre(), new String()));
                parametros.add(new Dato(Tipo.IN,
                                        String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS", Calendar.getInstance())));
                parametros.add(new Dato(Tipo.IN, this.archivo.ref_articulo.getAr_id_articulo(), new BigDecimal(0)));
                sql            = "INSERT INTO CE_Archivo (ar_nombre, ar_fecha, ref_articulo) VALUES (?,?,?)";
                this.respuesta = this.transaccion.actualizar(sql, parametros);
            }
        }

        return this.respuesta;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
