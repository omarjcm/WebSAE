//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : WebSAE
//  @ File Name : AE_Auspicio.java
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
import websae.msu.dominio.SU_Empresa;

public class AE_Auspicio {

    // <editor-fold defaultstate="collapsed" desc="Objetos Maestros">
	public AE_Evento ref_evento;
	public SU_Empresa ref_empresa;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Atributos">
	private BigDecimal au_id_auspicio;
	private Float au_monto;
	private String au_descripcion;
    // </editor-fold>

    public AE_Auspicio() {
        this.ref_empresa = new SU_Empresa();
        this.ref_evento = new AE_Evento();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getAu_descripcion() {
        return au_descripcion;
    }

    public void setAu_descripcion(String au_descripcion) {
        this.au_descripcion = au_descripcion;
    }

    public BigDecimal getAu_id_auspicio() {
        return au_id_auspicio;
    }

    public void setAu_id_auspicio(BigDecimal au_id_auspicio) {
        this.au_id_auspicio = au_id_auspicio;
    }

    public Float getAu_monto() {
        return au_monto;
    }

    public void setAu_monto(Float au_monto) {
        this.au_monto = au_monto;
    }

    public SU_Empresa getRef_empresa() {
        return ref_empresa;
    }

    public void setRef_empresa(SU_Empresa ref_empresa) {
        this.ref_empresa = ref_empresa;
    }

    public AE_Evento getRef_evento() {
        return ref_evento;
    }

    public void setRef_evento(AE_Evento ref_evento) {
        this.ref_evento = ref_evento;
    }
    // </editor-fold>
    
	public void cr_AE_Auspicio(Table objeto) {
        this.au_id_auspicio = new BigDecimal( (Long) objeto.get("au_id_auspicio") );
        this.au_monto = (Float) objeto.get("au_monto");
        this.au_descripcion = (String) objeto.get("au_descripcion");
        
        this.ref_empresa.setEm_id_empresa( new BigDecimal( (Long) objeto.get("ref_empresa") ) );
	}
	
	public void fin_AE_Auspicio() {
	}

    public static List< AE_Auspicio > mostrar_auspiciantes(String id_evento) {
        List< AE_Auspicio > auspicios = new LinkedList< AE_Auspicio >();

        Datos datos = new Datos("WebSAE");
        String sql = "SELECT * FROM ae_auspicio WHERE ref_evento = "+id_evento+";";
        Registro registros = datos.consulta( sql );
        for (int i=0; i<registros.size(); i++) {
            Table objeto = (Table) registros.get(i);
            
            AE_Auspicio elemento = new AE_Auspicio();
            elemento.cr_AE_Auspicio( objeto );
            auspicios.add( elemento );
        }
        return auspicios;
    }

    public static JSONObject toJSON(List< AE_Auspicio > auspicios) {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonItems = new JSONArray();
            for (Iterator<AE_Auspicio> it = auspicios.iterator(); it.hasNext();) {
                AE_Auspicio elemento = it.next();
                jsonItems.put( toJSONObject( elemento ) );
            }
            json.put("identifier", "id_auspicio");
            json.put("label", "monto");
            json.put("items", jsonItems);
        } catch (JSONException ex) {
            Logger.getLogger(AE_Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    public static JSONObject toJSONObject(AE_Auspicio elemento) {
        JSONObject json = new JSONObject();
        try {
            json.put("id_auspicio", elemento.getAu_id_auspicio() );
            json.put("monto", elemento.getAu_monto() );
            json.put("descripcion", elemento.getAu_descripcion() );
            
            if (elemento.ref_empresa.getEm_id_empresa() != null)
                json.put("empresa", SU_Empresa.toJSONObject( SU_Empresa.buscar_empresa( elemento.getRef_empresa().getEm_id_empresa().toString() ) ) );
        } catch (Exception ex) {
            Logger.getLogger(AE_Auspicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
