/**
 * @(#) Conferencista_Evento.java
 */

package websae.dominio;

public class Conferencista_Evento{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_conferencista_evento;
	
	public Conferencista_Evento( ){
		
	}
	
	public void cr_Conferencista_Evento( ){
		
	}
	
	public void fin_Conferencista_Evento( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto maestro Rol_Usuario.
	 */
	public Rol_Usuario ref_rol_usuario;
	
	/**
	 * Referencia al tipo de objeto dependiente Conferencista_Evento_Articulo.
	 */
	public java.util.List<Conferencista_Evento_Articulo> ref_conferencista_evento_articulo;
	
	
}
