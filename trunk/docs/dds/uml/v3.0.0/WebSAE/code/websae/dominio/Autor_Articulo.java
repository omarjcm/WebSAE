/**
 * @(#) Autor_Articulo.java
 */

package websae.dominio;

public class Autor_Articulo{
	private Integer id_autor_articulo;
	
	private Integer autor_principal;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Autor_Articulo( ){
		
	}
	
	public void cr_Autor_Articulo( ){
		
	}
	
	public void fin_Autor_Articulo( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Rol_Usuario.
	 */
	public Rol_Usuario ref_rol_usuario;
	
	/**
	 * Referencia al tipo de objeto maestro Articulo.
	 */
	public Articulo ref_articulo;
	
	
}
