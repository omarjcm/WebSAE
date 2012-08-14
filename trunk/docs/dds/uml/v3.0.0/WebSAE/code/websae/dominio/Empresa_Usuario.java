/**
 * @(#) Empresa_Usuario.java
 */

package websae.dominio;

public class Empresa_Usuario{
	private Integer id_empresa_usuario;
	
	private String telefono_oficina;
	
	private String fax_oficina;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Empresa_Usuario( ){
		
	}
	
	public void cr_Empresa_Usuario( ){
		
	}
	
	public void fin_Empresa_Usuario( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Empresa.
	 */
	public Empresa ref_empresa;
	
	/**
	 * Referencia al tipo de objeto maestro Cargo.
	 */
	public Cargo ref_cargo;
	
	/**
	 * Referencia al tipo de objeto maestro Usuario.
	 */
	public Usuario ref_usuario;
	
	
}
