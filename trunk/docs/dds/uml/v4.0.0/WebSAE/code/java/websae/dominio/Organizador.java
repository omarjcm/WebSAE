/**
 * @(#) Organizador.java
 */
package websae.dominio;

public class Organizador {

    /**
     * Referencia al tipo de objeto maestro Grupo_Investigacion.
     */
    public Grupo_Investigacion ref_grupo_investigacion;
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_organizador;

    public Organizador() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_organizador() {
        return id_organizador;
    }

    public void setId_organizador(Integer id_organizador) {
        this.id_organizador = id_organizador;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Organizador() {
    }

    public void fin_Organizador() {
    }
    // </editor-fold>
}
