/**
 * @(#) Alternativa.java
 */
package websae.dominio;

public class Alternativa {
    /**
     * Referencia al tipo de objeto maestro Pregunta.
     */
    public Pregunta ref_pregunta;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_alternativa;
    private String titulo;

    public Alternativa() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_alternativa() {
        return id_alternativa;
    }

    public void setId_alternativa(Integer id_alternativa) {
        this.id_alternativa = id_alternativa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Alternativa() {
    }

    public void fin_Alternativa() {
    }
    // </editor-fold>
}