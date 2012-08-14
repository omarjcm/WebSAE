/**
 * @(#) Evento.java
 */

package websae.dominio;

import websae.informacion.*;

public class Evento{
	private Integer id_evento;
	
	private String nombre;
	
	private String lugar;
	
	private String slogan;
	
	private String objetivo;
	
	private String descripcion;
	
	private String dirigido;
	
	private String fecha_inicio;
	
	private String fecha_fin;
	
	private String direccion_grafico;
	
	private String descripcion_registro;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Evento( ){
		
	}
	
	public void cr_Evento( ){
		
	}
	
	public void fin_Evento( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Tipo_Evento.
	 */
	public Tipo_Evento ref_tipo_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Auspicio.
	 */
	public java.util.List<Auspicio> ref_auspicio;
	
	/**
	 * Referencia al tipo de objeto dependiente Articulo.
	 */
	public java.util.List<Articulo> ref_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Conferencista_Evento.
	 */
	public java.util.List<Conferencista_Evento> ref_conferencista_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Convocatoria.
	 */
	public Convocatoria ref_convocatoria;
	
	/**
	 * Referencia al tipo de objeto dependiente Material.
	 */
	public java.util.List<Material> ref_material;
	
	/**
	 * Referencia al tipo de objeto dependiente Fecha_Evento.
	 */
	public java.util.List<Fecha_Evento> ref_fecha_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Organizador.
	 */
	public java.util.List<Organizador> ref_organizador;
	
	/**
	 * Referencia al tipo de objeto dependiente Faq.
	 */
	public Faq ref_faq;
	
	/**
	 * Referencia al tipo de objeto dependiente Registro.
	 */
	public java.util.List<Registro> ref_registro;
	
	/**
	 * Referencia al tipo de objeto dependiente Subevento_Evento.
	 */
	public Subevento_Evento ref_es_subevento_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Subevento_Evento.
	 */
	public java.util.List<Subevento_Evento> ref_tiene_subevento_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Menu_Evento.
	 */
	public java.util.List<Menu_Evento> ref_menu_evento;
	
	
}
