/**
 * @(#) Auspicio.java
 */

package websae.dominio;

public class Auspicio{
	private Integer id_auspicio;
	
	private float monto;
	
	private String descripcion;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Auspicio( ){
		
	}
	
	public void cr_Auspicio( ){
		
	}
	
	public void fin_Auspicio( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto maestro Empresa.
	 */
	public Empresa ref_empresa;
	
	
}
