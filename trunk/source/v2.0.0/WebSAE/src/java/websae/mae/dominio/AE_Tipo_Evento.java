//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : WebSAE
//  @ File Name : AE_Tipo_Evento.java
//  @ Date : 23/04/2009
//  @ Author : Guillermo Pizarro
//
//

package websae.mae.dominio;

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

public class AE_Tipo_Evento {

    // <editor-fold defaultstate="collapsed" desc="Objetos Dependientes">
	public List< AE_Evento > ref_evento;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
	private BigDecimal te_id_tipo_evento;
	private String te_nombre;
	private String te_estado;
    // </editor-fold>

    public AE_Tipo_Evento() {
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public List<AE_Evento> getRef_evento() {
        return ref_evento;
    }

    public void setRef_evento(List<AE_Evento> ref_evento) {
        this.ref_evento = ref_evento;
    }

    public String getTe_estado() {
        return te_estado;
    }

    public void setTe_estado(String te_estado) {
        this.te_estado = te_estado;
    }

    public BigDecimal getTe_id_tipo_evento() {
        return te_id_tipo_evento;
    }

    public void setTe_id_tipo_evento(BigDecimal te_id_tipo_evento) {
        this.te_id_tipo_evento = te_id_tipo_evento;
    }

    public String getTe_nombre() {
        return te_nombre;
    }

    public void setTe_nombre(String te_nombre) {
        this.te_nombre = te_nombre;
    }
    // </editor-fold>
    
	public void cr_AE_Tipo_Evento(String id_tipo_evento, String nombre, String estado) {
        this.te_id_tipo_evento = new BigDecimal( id_tipo_evento );
        this.te_nombre = nombre;
        this.te_estado = estado;
	}
	
	public void fin_AE_Tipo_Evento() {
	}

    public static AE_Tipo_Evento buscar_tipo_evento(BigDecimal id_tipo_evento) {
        AE_Tipo_Evento tipo_evento = new AE_Tipo_Evento();
        
        Datos datos = new Datos("WebSAE");
        Registro registros = datos.consulta("SELECT * FROM ae_tipo_evento WHERE te_id_tipo_evento = " + id_tipo_evento + ";");
        for (int i=0; i<registros.size(); i++) {
            Table objeto = (Table) registros.get(i);
            tipo_evento.cr_AE_Tipo_Evento( objeto.get("te_id_tipo_evento").toString(), (String)objeto.get("te_nombre"), (String)objeto.get("te_estado"));
        }
        return tipo_evento;
    }
    
    public static List< AE_Tipo_Evento > tipos_eventos() {
        List< AE_Tipo_Evento > tipos_eventos = new LinkedList< AE_Tipo_Evento >();

        Datos datos = new Datos("WebSAE");
        Registro registros = datos.consulta("SELECT * FROM ae_tipo_evento ORDER BY te_nombre;");
        for (int i=0; i<registros.size(); i++) {
            Table objeto = (Table) registros.get(i);
            AE_Tipo_Evento tipo_evento = new AE_Tipo_Evento();
            tipo_evento.cr_AE_Tipo_Evento( objeto.get("te_id_tipo_evento").toString(), (String)objeto.get("te_nombre"), (String)objeto.get("te_estado"));
            tipos_eventos.add( tipo_evento );
        }
        return tipos_eventos;
    }

    public static JSONObject toJSON(List< AE_Tipo_Evento > empresas) {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonItems = new JSONArray();
            for (Iterator< AE_Tipo_Evento > it = empresas.iterator(); it.hasNext();) {
                jsonItems.put( toJSONObject( it.next() ) );
            }
            json.put("identifier", "id_tipo_evento");
            json.put("label", "nombre");
            json.put("items", jsonItems);
        } catch (JSONException ex) {
            Logger.getLogger(AE_Tipo_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static JSONObject toJSONObject(AE_Tipo_Evento tipo_evento) {
        JSONObject json = new JSONObject();
        try {
            json.put("id_tipo_evento", tipo_evento.getTe_id_tipo_evento() );
            json.put("nombre", tipo_evento.getTe_nombre() );
            json.put("estado", tipo_evento.getTe_estado() );
        } catch (Exception ex) {
            Logger.getLogger(AE_Tipo_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
