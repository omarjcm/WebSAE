/**
 * @(#) Categoria.java
 */
package websae.dominio;

public class Categoria {
    /**
     * Referencia al tipo de objeto dependiente Categoria_Evento.
     */
    public java.util.List<Categoria_Evento> ref_categoria_evento;
    /**
     * Referencia al tipo de objeto dependiente Subcategoria.
     */
    public java.util.List<Subcategoria> ref_subcategoria;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_categoria;
    private String nombre;

    public Categoria() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Categoria() {
    }

    public void fin_Categoria() {
    }
    // </editor-fold>
}
