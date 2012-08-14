/**
 * @(#) Material_Registro.java
 */
package websae.dominio;

public class Material_Registro {
    /**
     * Referencia al tipo de objeto maestro Registro.
     */
    public Registro ref_registro;
    /**
     * Referencia al tipo de objeto maestro Material.
     */
    public Material ref_material;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_material_registro;
    private int cantidad_adicional;

    public Material_Registro() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getCantidad_adicional() {
        return cantidad_adicional;
    }

    public void setCantidad_adicional(int cantidad_adicional) {
        this.cantidad_adicional = cantidad_adicional;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_material_registro() {
        return id_material_registro;
    }

    public void setId_material_registro(Integer id_material_registro) {
        this.id_material_registro = id_material_registro;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Material_Registro() {
    }

    public void fin_Material_Registro() {
    }
    // </editor-fold>
}
