/**
 * @(#) Grupo_Investigacion.java
 */

package websae.dominio;

public class Grupo_Investigacion{
	private Integer id_grupo_investigacion;
	
	private String nombre_grupo;
	
	private String objetivo_grupo;
	
	private String logo_direccion;
	
	private String pagina_web;
	
	private String telefono;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Grupo_Investigacion( ){
		
	}
	
	public void cr_Grupo_Investigacion( ){
		
	}
	
	public void fin_Grupo_Investigacion( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto dependiente Organizador.
	 */
	public java.util.List<Organizador> ref_organizador;
	
	
}
