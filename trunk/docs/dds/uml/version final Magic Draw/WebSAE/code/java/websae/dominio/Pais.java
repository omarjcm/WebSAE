/**
 * @(#) Pais.java
 */
package websae.dominio;

public class Pais {

    /**
     * Referencia al tipo de objeto dependiente Ciudad.
     */
    public java.util.List<Ciudad> ref_ciudad;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_pais;
    private String nombre;

    public Pais() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Pais() {
    }

    public void fin_Pais() {
    }
    // </editor-fold>
}
