/**
 * @(#) Auspicio.java
 */
package websae.dominio;

public class Auspicio {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto maestro Empresa.
     */
    public Empresa ref_empresa;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_auspicio;
    private float monto;
    private String descripcion;

    public Auspicio() {
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

    public Integer getId_auspicio() {
        return id_auspicio;
    }

    public void setId_auspicio(Integer id_auspicio) {
        this.id_auspicio = id_auspicio;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Auspicio() {
    }

    public void fin_Auspicio() {
    }
    // </editor-fold>
}
