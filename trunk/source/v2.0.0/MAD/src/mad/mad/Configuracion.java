/* =============================================================================
 * Archivo:             Configuracion.java
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
public class Configuracion {
    private int puerto;
    private String host;
    
    public Configuracion() {
    }
    
    public Configuracion(int puerto, String host) {
        this.puerto = puerto;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
