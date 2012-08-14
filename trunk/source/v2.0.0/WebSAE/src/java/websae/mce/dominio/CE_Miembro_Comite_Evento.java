/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mce.dominio;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Datos;
import mad.objetos.Registro;
import mad.objetos.Table;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import websae.mac.dominio.AC_Usuario;
import websae.mac.dominio.AC_Usuario_Perfil;
import websae.mae.dominio.AE_Evento;
import websae.informacion.Funciones;

/**
 *
 * @author Guillermo Pizarro
 */
public class CE_Miembro_Comite_Evento {

    public BigDecimal ref_administrador;
    public BigDecimal ref_evaluador;
    public AE_Evento ref_evento;
    
    private BigDecimal mc_id_miembro_comite_evento;
    
    public AC_Usuario ref_usuario;
    private Boolean mc_es_administrador;
    private Boolean mc_es_evaluador;
    
    public CE_Miembro_Comite_Evento() {
        this.ref_evento = new AE_Evento();
        this.ref_usuario = new AC_Usuario();
        
        this.mc_es_administrador = Boolean.FALSE;
        this.mc_es_evaluador = Boolean.FALSE;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public Boolean getMc_es_administrador() {
        return mc_es_administrador;
    }

    public void setMc_es_administrador(Boolean mc_es_administrador) {
        this.mc_es_administrador = mc_es_administrador;
    }

    public Boolean getMc_es_evaluador() {
        return mc_es_evaluador;
    }

    public void setMc_es_evaluador(Boolean mc_es_evaluador) {
        this.mc_es_evaluador = mc_es_evaluador;
    }

    public BigDecimal getMc_id_miembro_comite_evento() {
        return mc_id_miembro_comite_evento;
    }

    public void setMc_id_miembro_comite_evento(BigDecimal mc_id_miembro_comite_evento) {
        this.mc_id_miembro_comite_evento = mc_id_miembro_comite_evento;
    }
    // </editor-fold>
    
    public void cr_CE_Miembro_Comite_Evento(Table objeto) {
        this.mc_id_miembro_comite_evento = Funciones.getBigDecimal( objeto.get("mc_id_miembro_comite_evento") );
        this.ref_evento.setEv_id_evento( Funciones.getBigDecimal( objeto.get("ref_evento") ) );
        BigDecimal id = Funciones.getBigDecimal( objeto.get("ref_administrador") );
        if (id != null) {
            this.ref_administrador = id;
            this.setMc_es_administrador( Boolean.TRUE );
        }
        id = Funciones.getBigDecimal( objeto.get("ref_evaluador") );
        if (id != null) {
            this.ref_evaluador = id;
            this.setMc_es_evaluador( Boolean.TRUE );
        }
        if (this.ref_evaluador != null) this.ref_usuario = AC_Usuario_Perfil.buscar_usuario( this.ref_evaluador );
        if (this.ref_administrador != null && this.ref_usuario.getUs_id_usuario() == null) this.ref_usuario = AC_Usuario_Perfil.buscar_usuario( this.ref_administrador );
    }

    public static List<CE_Miembro_Comite_Evento> miembros_comite_evento( String id_evento ) {
        String sql = "SELECT * FROM ce_miembro_comite_evento WHERE ref_evento = " + id_evento + ";";
        return miembros( sql );
    }
    
    public static List<CE_Miembro_Comite_Evento> administradores_evento( String id_evento ) {
        String sql = "SELECT * FROM ce_miembro_comite_evento WHERE ref_evento = " + id_evento + " AND ref_administrador IS NOT NULL;";
        return miembros_comite( sql );
    }
    
    /**
     * Metodo utilizado cuando se le asigna los evaluadores a un articulo en especifico, esta lista valida que
     * un autor no evalue su propio articulo.
     * @param id_evento
     * @param id_articulo
     * @return
     */
    public static List<CE_Miembro_Comite_Evento> evaluadores_evento( String id_evento, String id_articulo ) {
        String sql = "{call ce_evaluadores_evento(" + id_evento + ", " + id_articulo + ")};";
        return miembros_comite( sql );
    }

    /**
     * @param id_articulo
     * @return Una lista de evaluadores asignados a un articulo.
     */
    public static List<CE_Miembro_Comite_Evento> evaluadores_articulo( String id_articulo ) {
        String sql = "SELECT mc.* FROM ce_miembro_comite_Evento mc, ce_evaluacion_articulo ea WHERE mc.mc_id_miembro_comite_evento = ea.ref_evaluador AND ea.ref_articulo = "+id_articulo+";";
        return miembros_comite( sql );
    }
    
    private static List<CE_Miembro_Comite_Evento> miembros_comite( String sql ) {
        List<CE_Miembro_Comite_Evento> elementos = new LinkedList<CE_Miembro_Comite_Evento>();
        
        Datos dato = new Datos("WebSAE");
        Registro registros = dato.consulta(sql);
        for (int i = 0; i < registros.size(); i++) {
            CE_Miembro_Comite_Evento elemento = new CE_Miembro_Comite_Evento();
            elemento.cr_CE_Miembro_Comite_Evento( (Table)registros.get(i) );
            elementos.add( elemento );
        }
        return elementos;
    }
    
    private static List<CE_Miembro_Comite_Evento> miembros( String sql ) {
        List<CE_Miembro_Comite_Evento> elementos = new LinkedList<CE_Miembro_Comite_Evento>();

        Datos dato = new Datos("WebSAE");
        Registro registros = dato.consulta(sql);
        for (int i = 0; i < registros.size(); i++) {
            CE_Miembro_Comite_Evento elemento = new CE_Miembro_Comite_Evento();
            elemento.cr_CE_Miembro_Comite_Evento( (Table)registros.get(i) );
            elementos = interseccion(elementos, elemento);
        }
        return elementos;
    }
    
    private static List<CE_Miembro_Comite_Evento> interseccion(List<CE_Miembro_Comite_Evento> elementos, CE_Miembro_Comite_Evento miembro) {
        Boolean centinela = Boolean.TRUE;
        for (int i=elementos.size()-1; i >= 0; i--) {
            CE_Miembro_Comite_Evento elemento = elementos.get(i);
            if (elemento.ref_usuario.getUs_id_usuario().compareTo( miembro.ref_usuario.getUs_id_usuario() ) == 0) {
                if (miembro.getMc_es_administrador())
                    elemento.setMc_es_administrador( Boolean.TRUE );
                if (miembro.getMc_es_evaluador())
                    elemento.setMc_es_evaluador( Boolean.TRUE );
                
                centinela = Boolean.FALSE;
                break;
            }
        }
        if (centinela)
            elementos.add(miembro);
        
        return elementos;
    }

    public static JSONObject toJSON(List<CE_Miembro_Comite_Evento> elementos) {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonItems = new JSONArray();
            for (Iterator<CE_Miembro_Comite_Evento> it = elementos.iterator(); it.hasNext();) {
                CE_Miembro_Comite_Evento elemento = it.next();
                jsonItems.put(toJSONObject(elemento));
            }
            json.put("items", jsonItems);
        } catch (JSONException ex) {
            Logger.getLogger(CE_Miembro_Comite_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static JSONObject toJSONObject(CE_Miembro_Comite_Evento elemento) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", elemento.getMc_id_miembro_comite_evento() );
            json.put("usuario", AC_Usuario.toJSONObject( elemento.ref_usuario ));
            json.put("es_evaluador", elemento.getMc_es_evaluador() );
            json.put("es_administrador", elemento.getMc_es_administrador() );
        } catch (Exception ex) {
            Logger.getLogger(CE_Miembro_Comite_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }    
}
