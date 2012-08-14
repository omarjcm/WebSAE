package vlir8.funcionesentrada;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import websae.CONSTANTE;
import websae.bd.BaseDatos;
import websae.objetos.*;

public class F_Actividad extends HttpServlet
{	
	public boolean OK = false;		//error por validaci?n
	public String razon_fallo = "";		//raz?n de fallo por error de validaci?n
	
	/* Para conecci?n con la base */	
	public BaseDatos BD = new BaseDatos();
		
	/* Instanciar al objeto involucrado */
	 private Actividad actividad;
		
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	    {   	
	 		response.setHeader("Cache-control","no-cache");
	 		String operacion = request.getParameter("operacion");
		 	HttpSession sesion = request.getSession(true);
		 	try{	
			 	if(operacion.equals("crear")) {
			 		String id_fecha_evento = (String)sesion.getAttribute("id_fecha_evento");	    
				    int id_fecha_evento_int = Integer.valueOf(id_fecha_evento).intValue();
			 		String accion = request.getParameter("accion");			 		
		        	this.cr_Actividad(id_fecha_evento_int, request.getParameter("hora_inicio"), request.getParameter("hora_fin"), request.getParameter("actividad"), request.getParameter("expositor"));
		        	String url= "/administracion/eventos/administracion_fecha.jsp";
		        	if (accion.equals("cerrar") == true)
		        		response.sendRedirect("administracion_fecha.jsp");		        				        		
		        	else if (accion.equals("borrar") == true)
		        		response.sendRedirect("nueva_actividad.jsp");		        	
			 	}
			 	else if(operacion.equals("modificar")) {
			 		String id_actividad = (String)sesion.getAttribute("id_actividad");	    
				    int id_actividad_int = Integer.valueOf(id_actividad).intValue();
				    String id_fecha_evento = (String)sesion.getAttribute("id_fecha_evento");	    
				    int id_fecha_evento_int = Integer.valueOf(id_fecha_evento).intValue();
		        	this.modificar_Actividad(id_actividad_int,id_fecha_evento_int, request.getParameter("hora_inicio"), request.getParameter("hora_fin"), request.getParameter("actividad"), request.getParameter("expositor"));
		        	response.sendRedirect("administracion_fecha.jsp");		        	      	
			 	}		 	
			 	else if(operacion.equals("eliminar")) {
			 		String id_actividad = (String)sesion.getAttribute("id_actividad");	    
				    int id_actividad_int = Integer.valueOf(id_actividad).intValue();	
				    this.end_Actividad(id_actividad_int);
				    response.sendRedirect("administracion_fecha.jsp");				   
			 	}			 	
		 	}
		 	 catch(SQLException e){
		 	 	System.out.println(e);
				System.out.println("Error en la Base de Datos, vuelva a intentarlo");
				response.sendRedirect("/AppVlir8/error/error_base.jsp");
			}		
	   }
			
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		doGet(request, response);
	}
		
	
	public void cr_Actividad (int ref_fecha_evento, String hora_inicio, String hora_fin, String actividad, String expositor) throws SQLException 
	{	
		OK = true;		
		this.actividad = new Actividad();
		this.actividad.cr_Actividad(BD.nuevo_id_entero("actividad","id_actividad"), ref_fecha_evento, hora_inicio, hora_fin, actividad, expositor);
	}
	
	public void modificar_Actividad (int id_actividad, int ref_fecha_evento, String hora_inicio, String hora_fin, String actividad, String expositor) throws SQLException 
	{	
		OK = true;		
		this.actividad = (Actividad)BD.Obtener("actividad","id_actividad", id_actividad);
		this.actividad.modificar_Actividad(ref_fecha_evento, hora_inicio, hora_fin, actividad, expositor);
	}
	
	public void end_Actividad (int id_actividad) throws SQLException 
	{	
		OK = true;	
		this.actividad = (Actividad)BD.Obtener("actividad","id_actividad", id_actividad);
		this.actividad.end_Actividad();
	}
	
	
		
	/*------- MAIN SOLO SIRVE PARA PROBAR -------*/
	public static void main(String[] args) 
	{	
		try{ //As? se debe poner en el servlet		
			/*String mensajeUsuario="";
			F_Actividad f_actividad = new F_Actividad();
			//f_actividad.cr_Actividad(1, "09:50","11:40", "COMER MUCHO", "Eduardo");
			//f_actividad.modificar_Actividad(19, 1, "09:50","11:40", "caminar MUCHO", "Eduardo");
			f_actividad.end_Actividad(19);
			if (f_actividad.OK)
				mensajeUsuario = "OK";				
			else 
				mensajeUsuario = f_actividad.razon_fallo;
			System.out.println(mensajeUsuario);
			*/
			String login = "eduardo@hotmail.com";
			BaseDatos BD = new BaseDatos();
			Usuario administrador = (Usuario)BD.Obtener("usuario", "correo", login);		
			Rol rol_administrador = (Rol)BD.Obtener("rol","nombre","Administrador");
			Rol_Usuario rol_usuario_administrador = (Rol_Usuario)BD.Obtener("rol_usuario", "ref_rol", "ref_usuario", rol_administrador.id_rol, administrador.id_usuario, CONSTANTE.ESTADO_FIN_ROL_USUARIO);
			if ( rol_usuario_administrador.id_rol_usuario == CONSTANTE.OBJETO_VACIO ) {
				System.out.println("Prohibido el ingreso");
			}
			else
				System.out.println("administrador admitido");
		}
		catch(SQLException e){
			System.out.println(e);	
		}		
		
		
	}
	
}