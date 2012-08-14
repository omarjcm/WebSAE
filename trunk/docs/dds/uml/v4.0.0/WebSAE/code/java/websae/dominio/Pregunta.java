/**
 * @(#) Pregunta.java
 */
package websae.dominio;

public class Pregunta {

    /**
     * Referencia al tipo de objeto maestro Seccion.
     */
    public Seccion ref_seccion;
    /**
     * Referencia al tipo de objeto maestro Tipo_Pregunta.
     */
    public Tipo_Pregunta ref_tipo_pregunta;
    /**
     * Referencia al tipo de objeto dependiente Alternativa.
     */
    public java.util.List<Alternativa> ref_alternativa;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_pregunta;
    private String titulo;

    public Pregunta() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(Integer id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Pregunta() {
    }

    public void fin_Pregunta() {
    }
    // </editor-fold>
}
