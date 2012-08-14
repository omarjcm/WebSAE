/**
 * @(#) Cargo.java
 */
package websae.dominio;

import websae.informacion.*;

public class Cargo {
    /**
     * Referencia al tipo de objeto dependiente Empresa_Usuario.
     */
    public java.util.List<Empresa_Usuario> ref_empresa_usuario;
    /**
     * Referencia al tipo de objeto dependiente Tipo_Empresa_cargo.
     */
    public java.util.List<Tipo_Empresa_Cargo> ref_tipo_empresa_cargo;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_cargo;
    private String nombre;
    private String cargo;

    public Cargo() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Integer id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Cargo() {
    }

    public void fin_Cargo() {
    }
    // </editor-fold>
}
