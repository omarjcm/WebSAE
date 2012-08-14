/**
 * @(#) Convocatoria.java
 */

package websae.dominio;

public class Convocatoria{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_convocatoria;
	
	
	private String descripcion;
	
	
	private String ruta_formato;
	
	
	private String fecha_max_presentacion_art;
	
	
	private String fecha_max_evaluacion_art;
	
	
	private String fecha_max_aceptacion_art;
	
	
	private String fecha_max_correccion_art;
	
	public Convocatoria( ){
		
	}
	
	public void cr_Convocatoria( ){
		
	}
	
	public void fin_Convocatoria( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto dependiente Evaluacion.
	 */
	public Evaluacion ref_evaluacion;
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Tema.
	 */
	public java.util.List<Tema> ref_tema;
	
	
}
