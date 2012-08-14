/**
 * @(#) Empresa.java
 */

package websae.dominio;

public class Empresa{
	private Integer id_empresa;
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	private String codigo_postal;
	
	private String fax;
	
	private String logo_direccion;
	
	private String pagina_web;
	
	private String siglas;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Empresa( ){
		
	}
	
	public void cr_Empresa( ){
		
	}
	
	public void fin_Empresa( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Tipo_Empresa.
	 */
	public Tipo_Empresa ref_tipo_empresa;
	
	/**
	 * Referencia al tipo de objeto maestro Ciudad.
	 */
	public Ciudad ref_ciudad;
	
	/**
	 * Referencia al tipo de objeto dependiente Empresa_Usuario.
	 */
	public java.util.List<Empresa_Usuario> ref_empresa_usuario;
	
	/**
	 * Referencia al tipo de objeto dependiente Auspicio.
	 */
	public java.util.List<Auspicio> ref_auspicio;
	
	
}
