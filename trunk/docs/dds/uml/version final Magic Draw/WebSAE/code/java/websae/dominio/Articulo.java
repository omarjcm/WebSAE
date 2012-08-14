/**
 * @(#) Articulo.java
 */
package websae.dominio;

public class Articulo {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto dependiente Articulo_Articulo.
     */
    public java.util.List<Autor_Articulo> ref_autor_articulo;
    /**
     * Referencia al tipo de objeto dependiente Conferencista_Evento_Articulo.
     */
    public java.util.List<Conferencista_Evento_Articulo> ref_conferencista_evento_articulo;
    /**
     * Referencia al tipo de objeto dependiente Tema_Articulo.
     */
    public Tema_Articulo ref_tema_articulo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_articulo;
    private String titulo;
    private String resumen;
    private String palabras_claves;
    private String direccion_articulo;
    private String fecha_publicacion;
    private String descarga;
    private String comentario_autor;

    public Articulo() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getComentario_autor() {
        return comentario_autor;
    }

    public void setComentario_autor(String comentario_autor) {
        this.comentario_autor = comentario_autor;
    }

    public String getDescarga() {
        return descarga;
    }

    public void setDescarga(String descarga) {
        this.descarga = descarga;
    }

    public String getDireccion_articulo() {
        return direccion_articulo;
    }

    public void setDireccion_articulo(String direccion_articulo) {
        this.direccion_articulo = direccion_articulo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getPalabras_claves() {
        return palabras_claves;
    }

    public void setPalabras_claves(String palabras_claves) {
        this.palabras_claves = palabras_claves;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Articulo() {
    }

    public void fin_Articulo() {
    }
    // </editor-fold>
    
}