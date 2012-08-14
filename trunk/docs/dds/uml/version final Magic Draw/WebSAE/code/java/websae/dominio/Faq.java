/**
 * @(#) Faq.java
 */
package websae.dominio;

public class Faq {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto dependiente Pregunta_Faq.
     */
    public Pregunta_Faq ref_pregunta_faq;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_faq;

    public Faq() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_faq() {
        return id_faq;
    }

    public void setId_faq(Integer id_faq) {
        this.id_faq = id_faq;
    }

    public Pregunta_Faq getRef_pregunta_faq() {
        return ref_pregunta_faq;
    }

    public void setRef_pregunta_faq(Pregunta_Faq ref_pregunta_faq) {
        this.ref_pregunta_faq = ref_pregunta_faq;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Faq() {
    }

    public void fin_Faq() {
    }
    // </editor-fold>
}
