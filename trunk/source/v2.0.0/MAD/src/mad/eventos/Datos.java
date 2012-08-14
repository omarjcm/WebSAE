/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.eventos;

import mad.bd.*;
import mad.objetos.*;

/** Clase proxy que permite la extracción de datos a través de una consulta o la
 * ejecución de un procedimiento.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Datos {
    private String modulo;
    
    public Datos(String modulo) {
        this.modulo = modulo;
    }

    /** Permite consultas de datos a la Base de la aplicación correspondiente.
     * <p><strong>Precondiciones:</strong> sql!=null && sql!=""</p>
     * <p><strong>Poscondiciones:</strong> Registro contiene una lista de los
     * datos solicitados, como también puede ser nulo.</p>
     * @param modulo Modulo desde donde se accede a la Base de Datos.
     * @param sql El correspondiente query a ser ejecutado para extraer información de la misma.
     * @return una lista de los registros consultados.
     */
    public Registro consulta(String sql) {
        if (sql != null && sql.trim().compareTo("") != 0) {
            ConexionAD BD = new ConexionAD(this.modulo);
            return BD.ejecutar_consulta(sql);
        }
        return null;
    }
    
    /** Permite la ejecución de procedures almacenados en la Base de Datos, con la correspondiente autenticación.
     * <p><strong>Precondiciones:</strong> sql!=null && sql!=""</p>
     * <p><strong>Poscondiciones:</strong> Una respuesta que contiene un mensaje en caso de error, y los objetos que se puedan obtener del procedure.</p>
     * @param modulo Modulo desde donde se accede a la Base de Datos.
     * @param sql El correspondiente procedure que va a ser ejecutado.
     * @param objetos Parametros que son ingresados en el procedure.
     * @return una respuesta que contiene un mensaje y una lista de objetos en el caso que se de.
     */
    public Respuesta procedimiento(String sql, Parametros objetos) {
        if (sql != null && objetos != null && sql.trim().compareTo("") != 0) {
            ConexionAD BD = new ConexionAD(this.modulo);
            return BD.ejecutar_procedimiento(sql, objetos);
        }
        return null;
    }
}