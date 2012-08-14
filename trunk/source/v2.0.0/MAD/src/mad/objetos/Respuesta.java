/* =============================================================================
 * Archivo:             Respuesta.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */

package mad.objetos;

import java.util.ArrayList;

/** Clase base que contiene la respuesta de la ejeución de un procedure.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Respuesta {

    /** Mensaje que se recibe cuando se ejecuta un procedimiento. */
    private String mensaje;
    /** Lista de objetos que se retorna en la ejecución de un procedimiento. */
    private ArrayList<Dato> objetos;

    public Respuesta () {
        this.objetos = new ArrayList<Dato>();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Dato> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Dato> objetos) {
        this.objetos = objetos;
    }
}