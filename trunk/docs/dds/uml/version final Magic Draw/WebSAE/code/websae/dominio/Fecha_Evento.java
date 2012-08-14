/**
 * @(#) Fecha_Evento.java
 */

package websae.dominio;

public class Fecha_Evento{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_fecha_evento;
	
	
	private String fecha;
	
	public Fecha_Evento( ){
		
	}
	
	public void cr_Fecha_Evento( ){
		
	}
	
	public void fin_Fecha_Evento( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Actividad.
	 */
	public java.util.List<Actividad> ref_actividad;
	
	
}
