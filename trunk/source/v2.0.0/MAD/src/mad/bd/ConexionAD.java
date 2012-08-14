/* =============================================================================
 * Archivo:             ConexionAD.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */

package mad.bd;

/** Clase base que permite las disversas conexiones a la Base de Datos principal.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class ConexionAD extends AD {

    public ConexionAD(String modulo) {
        ConexionHSQLDB con = new ConexionHSQLDB();

        /** Configurar los valores para la conexión a la Base de Datos */
        con.configurar_conexion(modulo);
        this.setAD("com.mysql.jdbc.Driver",
                con.getConexion().getUsuario(),
                con.getConexion().getClave(),
                con.getConexion().getBase_datos(),
                con.getConfiguracion().getPuerto(),
                con.getConfiguracion().getHost());
    }
}