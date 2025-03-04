//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : WebSAE - Sistema de Administración de Eventos
//  @ File Name : AC_Asignar_Opcion.java
//  @ Date : 28/03/2009
//  @ Author : Guillermo Pizarro
//  @ Version : v1.0.0
//
//
package websae.mac.dominio;

import java.math.BigDecimal;

public class AC_Asignar_Opcion {

    public AC_Opcion ref_subopcion;
    public AC_Opcion ref_opcion;
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private BigDecimal ao_id_asignar_opcion;
    private String ao_estado;
    // </editor-fold>

    public AC_Asignar_Opcion() {
        this.ref_opcion = new AC_Opcion();
        this.ref_subopcion = new AC_Opcion();
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getAo_estado() {
        return ao_estado;
    }

    public void setAo_estado(String ao_estado) {
        this.ao_estado = ao_estado;
    }

    public BigDecimal getAo_id_asignar_opcion() {
        return ao_id_asignar_opcion;
    }

    public void setAo_id_asignar_opcion(BigDecimal ao_id_asignar_opcion) {
        this.ao_id_asignar_opcion = ao_id_asignar_opcion;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventos">
    public void cr_AC_Asignar_Opcion() {
        this.ref_opcion = new AC_Opcion();
        this.ref_subopcion = new AC_Opcion();
    }

    public void cr_AC_Asignar_Opcion(AC_Opcion ref_opcion, AC_Opcion ref_subopcion) {
        this.ref_opcion = ref_opcion;
        this.ref_subopcion = ref_subopcion;
    }

    public void fin_AC_Asignar_Opcion() {
    }
    // </editor-fold>
}
