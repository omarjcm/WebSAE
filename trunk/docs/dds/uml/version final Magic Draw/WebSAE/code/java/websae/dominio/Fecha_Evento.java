/**
 * @(#) Fecha_Evento.java
 */
package websae.dominio;

public class Fecha_Evento {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto dependiente Actividad.
     */
    public java.util.List<Actividad> ref_actividad;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_fecha_evento;
    private String fecha;

    public Fecha_Evento() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_fecha_evento() {
        return id_fecha_evento;
    }

    public void setId_fecha_evento(Integer id_fecha_evento) {
        this.id_fecha_evento = id_fecha_evento;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Fecha_Evento() {
    }

    public void fin_Fecha_Evento() {
    }
    // </editor-fold>
}
