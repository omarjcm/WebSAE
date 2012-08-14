/* =============================================================================
 * Archivo:             Registro.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */

package mad.objetos;

import java.util.LinkedList;
import java.util.List;

/** Clase base que contiene una lista de registros a ser retornados en una consulta.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Registro {

    /** Lista de registros que se retorna en una consulta. */
    public List< Table > registros;
    
    public Registro () {
        this.registros = new LinkedList< Table >();
    }

    public void add (Table objeto) {
        this.registros.add(objeto);
    }
    
    public Table get (int index) {
        return this.registros.get(index);
    }

    public int size () {
        return this.registros.size();
    }
}