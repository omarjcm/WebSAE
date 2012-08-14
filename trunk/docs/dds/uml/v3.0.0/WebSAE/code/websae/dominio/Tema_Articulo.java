/**
 * @(#) Tema_Articulo.java
 */

package websae.dominio;

public class Tema_Articulo{
	private Integer id_tema_articulo;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Tema_Articulo( ){
		
	}
	
	public void cr_Tema_Articulo( ){
		
	}
	
	public void fin_Tema_Articulo( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Tema.
	 */
	public Tema ref_tema;
	
	/**
	 * Referencia al tipo de objeto maestro Articulo.
	 */
	public Articulo ref_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Evaluacion_Tema_Articulo.
	 */
	public java.util.List<Evaluacion_Tema_Articulo> ref_evaluacion_tema_articulo;
	
	
}
