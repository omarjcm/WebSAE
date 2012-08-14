/**
 * @(#) Articulo.java
 */

package websae.dominio;

public class Articulo{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_articulo;
	
	
	private String titulo;
	
	
	private String resumen;
	
	
	private String palabras_claves;
	
	
	private String direccion_articulo;
	
	
	private String fecha_publicacion;
	
	
	private String descarga;
	
	
	private String comentario_autor;
	
	public Articulo( ){
		
	}
	
	public void cr_Articulo( ){
		
	}
	
	public void fin_Articulo( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Articulo_Articulo.
	 */
	public java.util.List<Autor_Articulo> ref_autor_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Conferencista_Evento_Articulo.
	 */
	public java.util.List<Conferencista_Evento_Articulo> ref_conferencista_evento_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Tema_Articulo.
	 */
	public Tema_Articulo ref_tema_articulo;
	
	
}
