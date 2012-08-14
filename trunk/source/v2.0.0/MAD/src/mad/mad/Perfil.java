/* =============================================================================
 * Archivo:             Perfil.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * 1.1.0	Guillermo Pizarro   18/02/2009  Cambio de tipo de dato del id_perfil
 * ========================================================================== */

package mad.mad;

import java.math.BigDecimal;

/** Clase base que permite la correspondiente configuración de una conexión.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class Perfil {
    private BigDecimal id_perfil;
    private String nombre;
    
    public Perfil() {
    }

    public Perfil(BigDecimal id_perfil, String nombre) {
        this.id_perfil = id_perfil;
        this.nombre = nombre;
    }

    public BigDecimal getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(BigDecimal id_perfil) {
        this.id_perfil = id_perfil;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
