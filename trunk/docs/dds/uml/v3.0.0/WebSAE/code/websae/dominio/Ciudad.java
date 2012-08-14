/**
 * @(#) Ciudad.java
 */

package websae.dominio;

public class Ciudad{
	private Integer id_ciudad;
	
	private String nombre;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Ciudad( ){
		
	}
	
	public void cr_Ciudad( ){
		
	}
	
	public void fin_ciudad( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Pais.
	 */
	public Pais ref_pais;
	
	/**
	 * Referencia al tipo de objeto dependiente Empresa.
	 */
	public java.util.List<Empresa> ref_empresa;
	
	/**
	 * Referencia al tipo de objeto dependiente Usuario.
	 */
	public java.util.List<Usuario> ref_usuario;
	
	
}
