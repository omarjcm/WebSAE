/**
 * @(#) Respuesta_Faq.java
 */
package websae.dominio;

public class Respuesta_Faq {

    /**
     * Referencia al tipo de objeto maestro Pregunta_Faq.
     */
    public Pregunta_Faq ref_pregunta_faq;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_respuesta_faq;
    private String respuesta;

    public Respuesta_Faq() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_respuesta_faq() {
        return id_respuesta_faq;
    }

    public void setId_respuesta_faq(Integer id_respuesta_faq) {
        this.id_respuesta_faq = id_respuesta_faq;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Respuesta_Faq() {
    }

    public void fin_Respuesta_Faq() {
    }
    // </editor-fold>
}
