/**
 * @(#) Rol_Usuario.java
 */

package websae.dominio;

import websae.informacion.*;

public class Rol_Usuario{
	/**
	 * indicador de estado
	 */
	private int estado;
	
	
	private Integer id_rol_usuario;
	
	public Rol_Usuario( ){
		
	}
	
	public void cr_Rol_Usuario( ){
		
	}
	
	public void fin_Rol_Usuario( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Rol.
	 */
	public Rol ref_rol;
	
	/**
	 * Referencia al tipo de objeto maestro Usuario.
	 */
	public Usuario ref_usuario;
	
	/**
	 * Referencia al tipo de objeto dependiente Articulo_Articulo.
	 */
	public java.util.List<Autor_Articulo> ref_autor_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Conferencista_Evento.
	 */
	public java.util.List<Conferencista_Evento> ref_conferencista_evento;
	
	/**
	 * Referencia al tipo de objeto dependiente Evaluador_Articulo.
	 */
	public java.util.List<Evaluador_Articulo> ref_evaluador_articulo;
	
	/**
	 * Referencia al tipo de objeto dependiente Registro.
	 */
	public java.util.List<Registro> ref_registro;
	
	/**
	 * Referencia al tipo de objeto dependiente Menu.
	 */
	public java.util.List<Menu> ref_menu;
	
	
}
