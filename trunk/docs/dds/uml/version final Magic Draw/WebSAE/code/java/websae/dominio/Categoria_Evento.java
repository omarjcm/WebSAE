/**
 * @(#) Categoria_Evento.java
 */
package websae.dominio;

import java.util.List;

public class Categoria_Evento {
    /**
     * Referencia al tipo de objeto maestro Categoria.
     */
    public Categoria ref_categoria;
    /**
     * Referencia al tipo de objeto dependiente Registro.
     */
    public java.util.List<Registro> ref_registro;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_categoria_evento;
    private float precio;
    private String fecha_inicio;
    private String fecha_fin;

    public Categoria_Evento() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
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

    public Integer getId_categoria_evento() {
        return id_categoria_evento;
    }

    public void setId_categoria_evento(Integer id_categoria_evento) {
        this.id_categoria_evento = id_categoria_evento;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Registro> getRef_registro() {
        return ref_registro;
    }

    public void setRef_registro(List<Registro> ref_registro) {
        this.ref_registro = ref_registro;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Categoria_Evento() {
    }

    public void fin_Categoria_Evento() {
    }
    // </editor-fold>
}
