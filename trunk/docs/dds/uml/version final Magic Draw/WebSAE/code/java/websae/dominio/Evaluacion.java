/**
 * @(#) Evaluacion.java
 */
package websae.dominio;

public class Evaluacion {
    /**
     * Referencia al tipo de objeto dependiente Evaluacion_Tema_Articulo.
     */
    public java.util.List<Evaluacion_Tema_Articulo> ref_evaluacion_tema_articulo;
    /**
     * Referencia al tipo de objeto maestro Convocatoria.
     */
    public Convocatoria ref_convocatoria;
    /**
     * Referencia al tipo de objeto dependiente Seccion.
     */
    public Seccion ref_seccion;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_evaluacion;
    private String mensaje;
    private String descripcion;

    public Evaluacion() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(Integer id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Evaluacion() {
    }

    public void fin_Evaluacion() {
    }
    // </editor-fold>
}
