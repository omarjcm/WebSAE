/**
 * @(#) Seccion.java
 */

package websae.dominio;

public class Seccion{
	private Integer id_seccion;
	
	private String titulo;
	
	private String descripcion;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Seccion( ){
		
	}
	
	public void cr_Seccion( ){
		
	}
	
	public void fin_Seccion( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evaluacion.
	 */
	public Evaluacion ref_evaluacion;
	
	/**
	 * Referencia al tipo de objeto dependiente Pregunta.
	 */
	public java.util.List<Pregunta> ref_pregunta;
	
	
}
