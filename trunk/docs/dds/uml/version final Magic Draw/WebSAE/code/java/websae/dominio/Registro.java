/**
 * @(#) Registro.java
 */
package websae.dominio;

public class Registro {

    /**
     * Referencia al tipo de objeto maestro Usuario.
     */
    public Usuario ref_usuario;
    /**
     * Referencia al tipo de objeto maestro Evento.
     */
    public Evento ref_evento;
    /**
     * Referencia al tipo de objeto maestro Rol_Usuario.
     */
    public Rol_Usuario ref_rol_usuario;
    /**
     * Referencia al tipo de objeto maestro Subcategoria_Evento.
     */
    public Subcategoria_Evento ref_subcategoria_evento;
    /**
     * Referencia al tipo de objeto maestro Categoria_Evento.
     */
    public Categoria_Evento ref_categoria_evento;
    /**
     * Referencia al tipo de objeto dependiente Material_Registro.
     */
    public java.util.List<Material_Registro> ref_material_registro;
    /**
     * indicador de estado
     */
    private int estado;
    private Integer id_registro;
    private String fecha_registro;
    private float valor_total_registro;
    private int aprobar_descuento;
    private int asistencia;
    private int pagado;

    public Registro() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public int getAprobar_descuento() {
        return aprobar_descuento;
    }

    public void setAprobar_descuento(int aprobar_descuento) {
        this.aprobar_descuento = aprobar_descuento;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public float getValor_total_registro() {
        return valor_total_registro;
    }

    public void setValor_total_registro(float valor_total_registro) {
        this.valor_total_registro = valor_total_registro;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_Registro() {
    }

    public void fin_Registro() {
    }
    // </editor-fold>
}
