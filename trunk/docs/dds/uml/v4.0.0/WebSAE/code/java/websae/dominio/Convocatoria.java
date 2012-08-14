/**
 * @(#) Convocatoria.java
 */
package websae.dominio;

public class Convocatoria {
    /**
     * Referencia al tipo de objeto dependiente Evaluacion.
     */
    public Evaluacion ref_evaluacion;
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto dependiente Tema.
     */
    public java.util.List<Tema> ref_tema;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_convocatoria;
    private String descripcion;
    private String ruta_formato;
    private String fecha_max_presentacion_art;
    private String fecha_max_evaluacion_art;
    private String fecha_max_aceptacion_art;
    private String fecha_max_correccion_art;

    public Convocatoria() {
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

    public String getFecha_max_aceptacion_art() {
        return fecha_max_aceptacion_art;
    }

    public void setFecha_max_aceptacion_art(String fecha_max_aceptacion_art) {
        this.fecha_max_aceptacion_art = fecha_max_aceptacion_art;
    }

    public String getFecha_max_correccion_art() {
        return fecha_max_correccion_art;
    }

    public void setFecha_max_correccion_art(String fecha_max_correccion_art) {
        this.fecha_max_correccion_art = fecha_max_correccion_art;
    }

    public String getFecha_max_evaluacion_art() {
        return fecha_max_evaluacion_art;
    }

    public void setFecha_max_evaluacion_art(String fecha_max_evaluacion_art) {
        this.fecha_max_evaluacion_art = fecha_max_evaluacion_art;
    }

    public String getFecha_max_presentacion_art() {
        return fecha_max_presentacion_art;
    }

    public void setFecha_max_presentacion_art(String fecha_max_presentacion_art) {
        this.fecha_max_presentacion_art = fecha_max_presentacion_art;
    }

    public Integer getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(Integer id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public String getRuta_formato() {
        return ruta_formato;
    }

    public void setRuta_formato(String ruta_formato) {
        this.ruta_formato = ruta_formato;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Convocatoria() {
    }

    public void fin_Convocatoria() {
    }
    // </editor-fold>
}
