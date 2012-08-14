/**
 * @(#) Evento.java
 */
package websae.dominio;

import websae.informacion.*;

public class Evento {

    /**
     * Referencia al tipo de objeto maestro Tipo_Evento.
     */
    public Tipo_Evento ref_tipo_evento;
    /**
     * Referencia al tipo de objeto dependiente Auspicio.
     */
    public java.util.List<Auspicio> ref_auspicio;
    /**
     * Referencia al tipo de objeto dependiente Articulo.
     */
    public java.util.List<Articulo> ref_articulo;
    /**
     * Referencia al tipo de objeto dependiente Conferencista_Evento.
     */
    public java.util.List<Conferencista_Evento> ref_conferencista_evento;
    /**
     * Referencia al tipo de objeto dependiente Convocatoria.
     */
    public Convocatoria ref_convocatoria;
    /**
     * Referencia al tipo de objeto dependiente Material.
     */
    public java.util.List<Material> ref_material;
    /**
     * Referencia al tipo de objeto dependiente Fecha_Evento.
     */
    public java.util.List<Fecha_Evento> ref_fecha_evento;
    /**
     * Referencia al tipo de objeto dependiente Organizador.
     */
    public java.util.List<Organizador> ref_organizador;
    /**
     * Referencia al tipo de objeto dependiente Faq.
     */
    public java.util.List<Faq> ref_faq;
    /**
     * Referencia al tipo de objeto dependiente Registro.
     */
    public java.util.List<Registro> ref_registro;
    /**
     * Referencia al tipo de objeto dependiente Subevento_Evento.
     */
    public Subevento_Evento ref_es_subevento_evento;
    /**
     * Referencia al tipo de objeto dependiente Subevento_Evento.
     */
    public java.util.List<Subevento_Evento> ref_tiene_subevento_evento;
    /**
     * Referencia al tipo de objeto dependiente Menu_Evento.
     */
    public java.util.List<Menu_Evento> ref_menu_evento;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_evento;
    private String nombre;
    private String lugar;
    private String slogan;
    private String objetivo;
    private String descripcion;
    private String dirigido;
    private String fecha_inicio;
    private String fecha_fin;
    private String direccion_grafico;
    private String descripcion_registro;

    public Evento() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion_registro() {
        return descripcion_registro;
    }

    public void setDescripcion_registro(String descripcion_registro) {
        this.descripcion_registro = descripcion_registro;
    }

    public String getDireccion_grafico() {
        return direccion_grafico;
    }

    public void setDireccion_grafico(String direccion_grafico) {
        this.direccion_grafico = direccion_grafico;
    }

    public String getDirigido() {
        return dirigido;
    }

    public void setDirigido(String dirigido) {
        this.dirigido = dirigido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Evento() {
    }

    public void fin_Evento() {
    }
    // </editor-fold>
}
