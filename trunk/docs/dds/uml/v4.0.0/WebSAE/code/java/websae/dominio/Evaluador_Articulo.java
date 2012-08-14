/**
 * @(#) Evaluador_Articulo.java
 */
package websae.dominio;

public class Evaluador_Articulo {
    /**
     * Referencia al tipo de objeto maestro Rol_Usuario.
     */
    public Rol_Usuario ref_rol_usuario;
    /**
     * Referencia al tipo de objeto dependiente Evaluacion_Tema_Articulo.
     */
    public java.util.List<Evaluacion_Tema_Articulo> ref_evaluacion_tema_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_evaluador_articulo;

    public Evaluador_Articulo() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_evaluador_articulo() {
        return id_evaluador_articulo;
    }

    public void setId_evaluador_articulo(Integer id_evaluador_articulo) {
        this.id_evaluador_articulo = id_evaluador_articulo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Evaluador_Articulo() {
    }

    public void fin_Evaluador_Articulo() {
    }
    // </editor-fold>
}
