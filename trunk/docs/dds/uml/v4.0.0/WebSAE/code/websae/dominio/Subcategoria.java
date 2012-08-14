/**
 * @(#) Subcategoria.java
 */

package websae.dominio;

public class Subcategoria{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_subcategoria;
	
	
	private String nombre;
	
	public Subcategoria( ){
		
	}
	
	public void cr_Subcategoria( ){
		
	}
	
	public void fin_Subcategoria( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Categoria.
	 */
	public Categoria ref_categoria;
	
	/**
	 * Referencia al tipo de objeto dependiente Subcategoria_Evento.
	 */
	public java.util.List<Subcategoria_Evento> ref_subcategoria_evento;
	
	
}
