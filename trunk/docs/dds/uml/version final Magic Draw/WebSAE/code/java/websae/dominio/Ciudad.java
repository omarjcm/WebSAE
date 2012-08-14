/**
 * @(#) Ciudad.java
 */
package websae.dominio;

public class Ciudad {
    /**
     * Referencia al tipo de objeto maestro Pais.
     */
    public Pais ref_pais;
    /**
     * Referencia al tipo de objeto dependiente Empresa.
     */
    public java.util.List<Empresa> ref_empresa;
    /**
     * Referencia al tipo de objeto dependiente Usuario.
     */
    public java.util.List<Usuario> ref_usuario;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_ciudad;
    private String nombre;

    public Ciudad() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Ciudad() {
    }

    public void fin_ciudad() {
    }
    // </editor-fold>
}
