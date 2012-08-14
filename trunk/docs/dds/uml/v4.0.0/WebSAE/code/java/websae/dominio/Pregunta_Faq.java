/**
 * @(#) Pregunta_Faq.java
 */
package websae.dominio;

public class Pregunta_Faq {

    /**
     * Referencia al tipo de objeto maestro Faq.
     */
    public Faq ref_faq;
    /**
     * Referencia al tipo de objeto dependiente Respuesta_Faq.
     */
    public java.util.List<Respuesta_Faq> ref_respuesta_faq;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_pregunta_faq;
    private String pregunta;

    public Pregunta_Faq() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_pregunta_faq() {
        return id_pregunta_faq;
    }

    public void setId_pregunta_faq(Integer id_pregunta_faq) {
        this.id_pregunta_faq = id_pregunta_faq;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Pregunta_Faq() {
    }

    public void fin_Pregunta_Faq() {
    }
    // </editor-fold>
}
