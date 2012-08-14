/* =============================================================================
 * Archivo:             Parametros.java
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

/** Clase base Parametros en la que se guarda la información que va a ser procesada
 * en la ejecución de un procedimiento.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Parametros {

    /** Lista de los datos a ser ingresados en un procedure. */
    private ArrayList<Dato> datos;
    
    public Parametros () {
        this.datos = new ArrayList<Dato>();
    }

    public void add (Dato dato) {
        this.datos.add(dato);
    }

    public Dato get (int index) {
        return this.datos.get(index);
    }

    public int size () {
        return this.datos.size();
    }

    public ArrayList<Dato> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Dato> datos) {
        this.datos = datos;
    }
}