/**
 * @(#) Material.java
 */

package websae.dominio;

public class Material{
	private Integer id_material;
	
	private float precio;
	
	private int cantidad_entregar;
	
	private String descripcion;
	
	/**
	 * indicador de estado
	 */
	private int estado;
	
	public Material( ){
		
	}
	
	public void cr_Material( ){
		
	}
	
	public void fin_Material( ){
		
	}
	
	/**
	 * Referencia al tipo de objeto maestro Evento.
	 */
	public Evento ref_evento;
	
	/**
	 * Referencia al tipo de objeto maestro Tipo_Material.
	 */
	public Tipo_Material ref_tipo_material;
	
	/**
	 * Referencia al tipo de objeto dependiente Material_Registro.
	 */
	public java.util.List<Material_Registro> ref_material_registro;
	
	
}
