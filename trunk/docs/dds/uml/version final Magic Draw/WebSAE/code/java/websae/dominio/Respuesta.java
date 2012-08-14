/**
 * @(#) Respuesta.java
 */
package websae.dominio;

public class Respuesta {

    /**
     * Referencia al tipo de objeto maestro Evaluacion_Tema_Articulo.
     */
    public Evaluacion_Tema_Articulo ref_evaluacion_tema_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_respuesta;

    public Respuesta() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_respuesta() {
        return id_respuesta;
    }

    public void setId_respuesta(Integer id_respuesta) {
        this.id_respuesta = id_respuesta;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Respuesta() {
    }

    public void fin_Respuesta() {
    }
    // </editor-fold>
}
