/**
 * @(#) Categoria.java
 */

package websae.dominio;

public class Categoria{
	private Integer id_categoria;
	
	private String nombre;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Categoria( ){
		
	}
	
	public void cr_Categoria( ){
		
	}
	
	public void fin_Categoria( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto dependiente Categoria_Evento.
	 */
	public java.util.List<Categoria_Evento> ref_categoria_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Subcategoria.
	 */
	public java.util.List<Subcategoria> ref_subcategoria;
	
	
}
