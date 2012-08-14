/**
 * @(#) Usuario.java
 */

package websae.dominio;

import java.util.List;

/**
 * <html>
 * <head>
 * 
 * </head>
 * <body>
 * <p>
 * Persona que se suscribe al portal y se obtiene de ella sus datos, se
 * relaciona con <b>Rol_Usuario</b> para asignarle roles dentro del
 * sistema, con <b>Empresa_Usuario</b> para indicar la empresa o
 * universidad en la cual labora o estudia, y con <b>Registro</b> para
 * tener constancia del registro a los eventos que realicen los Grupos de
 * Investigaci&#243;n.
 * </p>
 * </body>
 * </html>
 */
public class Usuario{
	/**
	 * identificador del objeto Usuario dentro del portal
	 */
	private Integer id_usuario;
	
	/**
	 * N�mero de c�dula para personas de Ecuador o n�mero de pasaporte para extranjeros.
	 */
	private String identificacion;
	
	/**
	 * Debido a que puede ser CI (Nacional), CI (Extranjera) o No. de pasaporte.
	 */
	private char tipo_identificacion;
	
	/**
	 * Nombre del usuario
	 */
	private String nombre;
	
	/**
	 * Apellido del usuario
	 */
	private String apellido;
	
	/**
	 * Email del usuario
	 */
	private String correo;
	
	/**
	 * Clave del usuario
	 */
	private String clave;
	
	/**
	 * sexo del usuario (masculino o femenino)
	 */
	private char genero;
	
	/**
	 * fecha (dd,mm,aaaa) en que naci� el usuario
	 */
	private String fecha_nacimiento;
	
	/**
	 * Direcci�n de domicilio del usuario
	 */
	private String direccion;
	
	/**
	 * Tel�fono convencional del usuario
	 */
	private String telefono;
	
	/**
	 * N�mero de tel�fono celular del usuario
	 */
	private String celular;
	
	/**
	 * si el usuario estudia o trabaja.
	 */
	private char actividad;
	
	/**
	 * Informaci�n acerca del perfil profesional que ha llevado el usuario.
	 */
	private String hoja_vida;
	
	/**
	 * Ruta a la imagen de la foto del usuario.
	 */
	private String foto_direccion;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Usuario( ){
		
	}
	
	public void cr_Usuario( ){
		
	}
	
	public void fin_Usuario( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Titulo.
	 */
	public Titulo ref_titulo;
	
	/**
	 * Referencia al tipo de objeto maestro Ciudad.
	 */
	public Ciudad ref_ciudad;
	
	/**
	 * Referencia al tipo de objeto dependiente Rol_Usuario.
	 */
	public java.util.List<Rol_Usuario> ref_rol_usuario;
	
	/**
	 * Referencia al tipo de objeto dependiente Empresa_Usuario.
	 */
	public Empresa_Usuario ref_empresa_usuario;
	
	/**
	 * Referencia al tipo de objeto dependiente Registro.
	 */
	public java.util.List<Registro> ref_registro;
	
	
}
