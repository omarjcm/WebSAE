/**
 * @(#) Conferencista_Evento_Articulo.java
 */
package websae.dominio;

public class Conferencista_Evento_Articulo {
    /**
     * Referencia al tipo de objeto maestro Conferencista_Evento.
     */
    public Conferencista_Evento ref_conferencista_evento;
    /**
     * Referencia al tipo de objeto maestro Articulo.
     */
    public Articulo ref_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_conferencista_evento_articulo;

    public Conferencista_Evento_Articulo() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_conferencista_evento_articulo() {
        return id_conferencista_evento_articulo;
    }

    public void setId_conferencista_evento_articulo(Integer id_conferencista_evento_articulo) {
        this.id_conferencista_evento_articulo = id_conferencista_evento_articulo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Conferencista_Evento_Articulo() {
    }

    public void fin_Conferencista_Evento_Articulo() {
    }
    // </editor-fold>
}
