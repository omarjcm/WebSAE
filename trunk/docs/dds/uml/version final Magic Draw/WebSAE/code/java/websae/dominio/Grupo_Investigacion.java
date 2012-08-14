/**
 * @(#) Grupo_Investigacion.java
 */
package websae.dominio;

public class Grupo_Investigacion {
    /**
     * Referencia al tipo de objeto dependiente Organizador.
     */
    public java.util.List<Organizador> ref_organizador;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_grupo_investigacion;
    private String nombre_grupo;
    private String objetivo_grupo;
    private String logo_direccion;
    private String pagina_web;
    private String telefono;

    public Grupo_Investigacion() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getId_grupo_investigacion() {
        return id_grupo_investigacion;
    }

    public void setId_grupo_investigacion(Integer id_grupo_investigacion) {
        this.id_grupo_investigacion = id_grupo_investigacion;
    }

    public String getLogo_direccion() {
        return logo_direccion;
    }

    public void setLogo_direccion(String logo_direccion) {
        this.logo_direccion = logo_direccion;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public String getObjetivo_grupo() {
        return objetivo_grupo;
    }

    public void setObjetivo_grupo(String objetivo_grupo) {
        this.objetivo_grupo = objetivo_grupo;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Grupo_Investigacion() {
    }

    public void fin_Grupo_Investigacion() {
    }
    // </editor-fold>
}
