/**
 * @(#) Conferencista_Evento.java
 */
package websae.dominio;

public class Conferencista_Evento {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto maestro Rol_Usuario.
     */
    public Rol_Usuario ref_rol_usuario;
    /**
     * Referencia al tipo de objeto dependiente Conferencista_Evento_Articulo.
     */
    public java.util.List<Conferencista_Evento_Articulo> ref_conferencista_evento_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_conferencista_evento;

    public Conferencista_Evento() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_conferencista_evento() {
        return id_conferencista_evento;
    }

    public void setId_conferencista_evento(Integer id_conferencista_evento) {
        this.id_conferencista_evento = id_conferencista_evento;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Conferencista_Evento() {
    }

    public void fin_Conferencista_Evento() {
    }
    // </editor-fold>
}
