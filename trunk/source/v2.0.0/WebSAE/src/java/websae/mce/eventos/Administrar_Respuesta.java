
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

import java.math.BigDecimal;
import websae.mce.dominio.CE_Respuesta;
import mad.eventos.Transaccion;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Tipo;
import websae.mce.dominio.CE_Tipo_Pregunta;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Respuesta {
    
    private CE_Respuesta objeto;
    private BigDecimal id_evaluador_articulo;
    private BigDecimal id_objeto;
    
    private Transaccion  transaccion;
    private String       mensaje;
    private BigDecimal   tipo;
    
    public Administrar_Respuesta(Transaccion transaccion, CE_Respuesta respuesta, BigDecimal id_evaluador_articulo, BigDecimal id_objeto, BigDecimal tipo) {
        this.objeto = respuesta;
        this.id_evaluador_articulo = id_evaluador_articulo;
        this.id_objeto = id_objeto;
        
        this.transaccion = transaccion;
        this.tipo = tipo;
    }
    
    public void procesar_peticion() {
        if ( this.objeto != null ) {
            try {
                String sql = "";
                Parametros parametros = new Parametros();

                if (this.tipo.compareTo( CE_Tipo_Pregunta.ABIERTA ) == 0) {
                    parametros.add(new Dato(Tipo.IN, this.objeto.getRe_texto(), new String()));
                    parametros.add(new Dato(Tipo.IN, this.id_objeto, BigDecimal.ZERO));
                    parametros.add(new Dato(Tipo.IN, this.id_evaluador_articulo, BigDecimal.ZERO));

                    sql = "INSERT CE_Respuesta (re_texto, ref_pregunta, ref_evaluacion_articulo) VALUES (?,?,?);";
                } else {
                    parametros.add(new Dato(Tipo.IN, this.id_objeto, BigDecimal.ZERO));
                    parametros.add(new Dato(Tipo.IN, this.objeto.getRe_seleccionada(), Boolean.TRUE));
                    parametros.add(new Dato(Tipo.IN, this.id_evaluador_articulo, BigDecimal.ZERO));

                    sql = "INSERT CE_Respuesta (ref_alternativa, re_seleccionada, ref_evaluacion_articulo) VALUES (?,?,?);";
                }
                this.mensaje = this.transaccion.actualizar(sql, parametros);
            } catch (Exception ex) {
                this.transaccion.rollback();
                this.mensaje = "ERROR:registrar";
            }
        }
    }
}
