/* =============================================================================
 * Archivo:             Dato.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */

package mad.objetos;

/** Clase base que contiene la información de cada Dato que va a ser procesada en
 * la ejecución de un procedure.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Dato {

    /** Si va a ser tipo de entrada o de salida en un procedure */
    private Tipo tipo;
    /** Tipo de dato genérico */
    private Object dato;
    /** Dato para poder especificar el tipo de dato */
    private String tipo_dato;

    public Dato() {
        this.dato = null;
    }

    /**
     * Se inicializan las variables para la ejecución de un procedure.
     * @param tipo se menciona si es un dato tipo IN o OUT.
     * @param dato el dato a ser ingresado, NO SE ACEPTAN NULOS.
     */
    public Dato (Tipo tipo, Object dato) {
        if (dato != null) {
            this.tipo = tipo;
            this.dato = dato;
            this.tipo_dato = dato.getClass().toString();
        }
    }
    
    /**
     * Se inicializan las variables para la ejecución de un procedure.
     * @param tipo se menciona si es un dato tipo IN o OUT.
     * @param dato el dato a ser ingresado, SE ACEPTAN NULOS.
     * @param tipo_dato este dato es inicializado si "dato" es null, se debe ingresar sólo la inicialización, por ejemplo, new String(). Este Dato NO PUEDE SER NULO.
     */
    public Dato (Tipo tipo, Object dato, Object tipo_dato) {
        if (tipo_dato != null) {
            this.tipo = tipo;
            this.dato = dato;
            this.tipo_dato = tipo_dato.getClass().toString();
        }
    }

    public void setTipo_dato(String tipo_dato) {
        this.tipo_dato = tipo_dato;
    }
    
    public String getTipo_dato() {
        return tipo_dato;
    }
    
    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
