/**
 * @(#) Material.java
 */
package websae.dominio;

public class Material {
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto maestro Tipo_Material.
     */
    public Tipo_Material ref_tipo_material;
    /**
     * Referencia al tipo de objeto dependiente Material_Registro.
     */
    public java.util.List<Material_Registro> ref_material_registro;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_material;
    private float precio;
    private int cantidad_entregar;
    private String descripcion;

    public Material() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getCantidad_entregar() {
        return cantidad_entregar;
    }

    public void setCantidad_entregar(int cantidad_entregar) {
        this.cantidad_entregar = cantidad_entregar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_material() {
        return id_material;
    }

    public void setId_material(Integer id_material) {
        this.id_material = id_material;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Material() {
    }

    public void fin_Material() {
    }
    // </editor-fold>
}
