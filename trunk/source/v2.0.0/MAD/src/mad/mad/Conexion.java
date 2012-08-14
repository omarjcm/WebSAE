/* =============================================================================
 * Archivo:             Conexion.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */

package mad.mad;

/** Clase base que permite la correspondiente configuración de una conexión.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Conexion {
    private int id_conexion;
    private String modulo;
    private String base_datos;
    private String usuario;
    private String clave;
    
    public Conexion() {
    }

    public Conexion(int id_conexion, String modulo, String base_datos, String usuario, String clave) {
        this.id_conexion = id_conexion;
        this.modulo = modulo;
        this.base_datos = base_datos;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getBase_datos() {
        return base_datos;
    }

    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getId_conexion() {
        return id_conexion;
    }

    public void setId_conexion(int id_conexion) {
        this.id_conexion = id_conexion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
