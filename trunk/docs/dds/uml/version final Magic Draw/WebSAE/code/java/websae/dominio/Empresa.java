/**
 * @(#) Empresa.java
 */
package websae.dominio;

public class Empresa {
    /**
     * Referencia al tipo de objeto maestro Tipo_Empresa.
     */
    public Tipo_Empresa ref_tipo_empresa;
    /**
     * Referencia al tipo de objeto maestro Ciudad.
     */
    public Ciudad ref_ciudad;
    /**
     * Referencia al tipo de objeto dependiente Empresa_Usuario.
     */
    public java.util.List<Empresa_Usuario> ref_empresa_usuario;
    /**
     * Referencia al tipo de objeto dependiente Auspicio.
     */
    public java.util.List<Auspicio> ref_auspicio;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_empresa;
    private String nombre;
    private String direccion;
    private String telefono;
    private String codigo_postal;
    private String fax;
    private String logo_direccion;
    private String pagina_web;
    private String siglas;

    public Empresa() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getLogo_direccion() {
        return logo_direccion;
    }

    public void setLogo_direccion(String logo_direccion) {
        this.logo_direccion = logo_direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Empresa() {
    }

    public void fin_Empresa() {
    }
    // </editor-fold>
}
