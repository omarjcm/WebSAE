/**
 * @(#) Autor_Articulo.java
 */
package websae.dominio;

public class Autor_Articulo {
    /**
     * Referencia al tipo de objeto maestro Rol_Usuario.
     */
    public Rol_Usuario ref_rol_usuario;
    /**
     * Referencia al tipo de objeto maestro Articulo.
     */
    public Articulo ref_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_autor_articulo;
    private Integer autor_principal;

    public Autor_Articulo() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public Integer getAutor_principal() {
        return autor_principal;
    }

    public void setAutor_principal(Integer autor_principal) {
        this.autor_principal = autor_principal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_autor_articulo() {
        return id_autor_articulo;
    }

    public void setId_autor_articulo(Integer id_autor_articulo) {
        this.id_autor_articulo = id_autor_articulo;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Autor_Articulo() {
    }

    public void fin_Autor_Articulo() {
    }
    // </editor-fold>
}
