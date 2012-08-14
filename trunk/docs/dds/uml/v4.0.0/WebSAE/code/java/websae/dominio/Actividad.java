/**
 * @(#) Actividad.java
 */
package websae.dominio;

public class Actividad {
    /**
     * Referencia al tipo de objeto maestro Fecha_Evento.
     */
    public Fecha_Evento ref_fecha_evento;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_actividad;
    private String hora_inicio;
    private String hora_fin;
    private String actividad;
    private String expositor;
    
    public Actividad() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Integer getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Integer id_actividad) {
        this.id_actividad = id_actividad;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Actividad() {
    }

    public void fin_Actividad() {
    }
    // </editor-fold>
}