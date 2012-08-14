/**
 * @(#) Empresa_Usuario.java
 */
package websae.dominio;

public class Empresa_Usuario {
    /**
     * Referencia al tipo de objeto maestro Empresa.
     */
    public Empresa ref_empresa;
    /**
     * Referencia al tipo de objeto maestro Cargo.
     */
    public Cargo ref_cargo;
    /**
     * Referencia al tipo de objeto maestro Usuario.
     */
    public Usuario ref_usuario;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_empresa_usuario;
    private String telefono_oficina;
    private String fax_oficina;

    public Empresa_Usuario() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFax_oficina() {
        return fax_oficina;
    }

    public void setFax_oficina(String fax_oficina) {
        this.fax_oficina = fax_oficina;
    }

    public Integer getId_empresa_usuario() {
        return id_empresa_usuario;
    }

    public void setId_empresa_usuario(Integer id_empresa_usuario) {
        this.id_empresa_usuario = id_empresa_usuario;
    }

    public String getTelefono_oficina() {
        return telefono_oficina;
    }

    public void setTelefono_oficina(String telefono_oficina) {
        this.telefono_oficina = telefono_oficina;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Empresa_Usuario() {
    }

    public void fin_Empresa_Usuario() {
    }
    // </editor-fold>
}
