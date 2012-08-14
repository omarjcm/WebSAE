/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.funciones_entrada;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Constante;
import websae.mac.dominio.AC_Usuario;
import websae.msu.eventos.Administrar_Cargo;
import websae.msu.eventos.Administrar_Empresa;
import websae.msu.eventos.Administrar_Tipo_Empresa;
import websae.msu.eventos.Administrar_Tipo_Empresa_Cargo;
import websae.msu.eventos.Administrar_Titulo;
import websae.msu.eventos.Administrar_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_su_administrar_objetos extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        /** Inicializando la variable de lenguage para conocer que idioma esta usando el usuario */
        String lang = "es";
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("lang") != null)
            lang = (String) sesion.getAttribute("lang");
            
        String tipo = (String) request.getParameter("tipo");
        if (tipo.compareTo("registrar_empresa") == 0) {
            /** Registro de la Empresa. */
            Administrar_Empresa registro = new Administrar_Empresa( request, Constante.REGISTRAR );
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Empresa.obtener_mensaje( registro.getRespuesta(), registro.getId_Empresa(),lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_empresa") == 0) {
            /** Modificacion de la Empresa. */
            Administrar_Empresa registro = new Administrar_Empresa( request, Constante.MODIFICAR );
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Empresa.obtener_mensaje( registro.getRespuesta(), null, lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_empresa") == 0) {
            /** Modificacion de la Empresa. */
            Administrar_Empresa registro = new Administrar_Empresa( request, Constante.ELIMINAR );
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Empresa.obtener_mensaje( registro.getRespuesta(), null, lang ) );
            out.close();
        } else if (tipo.compareTo("registrar_usuario") == 0) {
            /** Registro del Usuario. */
            Administrar_Usuario registro = new Administrar_Usuario( request, Constante.REGISTRAR );
            /** Ejecucion del registro del usuario y del envio de email */
            registro.procesar_registro( getServletContext().getInitParameter("email.host"),
                                        getServletContext().getInitParameter("email.admin"),
                                        getServletContext().getInitParameter("email.remitente"),
                                        getServletContext().getInitParameter("admin.nombre"),
                                        getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator,
                                        lang );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Usuario.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_usuario") == 0) {
            /** Mdificacion del Usuario. */
            Administrar_Usuario registro = new Administrar_Usuario( request, Constante.MODIFICAR );
            registro.procesar_actualizacion_registro((AC_Usuario) sesion.getAttribute("usuario"));
            /** Actualizar el usuario cargado en la variable de sesion */
            sesion.setAttribute("usuario", registro.getUsuario());
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Usuario.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("registrar_tipo_empresa") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Empresa registro = new Administrar_Tipo_Empresa(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Empresa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_tipo_empresa") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Empresa registro = new Administrar_Tipo_Empresa(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Empresa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_tipo_empresa") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Empresa registro = new Administrar_Tipo_Empresa(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Empresa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("registrar_titulo") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Titulo registro = new Administrar_Titulo(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Titulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_titulo") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Titulo registro = new Administrar_Titulo(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Titulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_titulo") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Titulo registro = new Administrar_Titulo(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Titulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("registrar_cargo") == 0) {
            Administrar_Cargo registro = new Administrar_Cargo(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Cargo.obtener_mensaje( registro.getRespuesta(), registro.getId_Cargo(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_cargo") == 0) {
            Administrar_Cargo registro = new Administrar_Cargo(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Cargo.obtener_mensaje( registro.getRespuesta(), null, lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_cargo") == 0) {
            Administrar_Cargo registro = new Administrar_Cargo(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Cargo.obtener_mensaje( registro.getRespuesta(), null, lang ) );
            out.close();
        } else if (tipo.compareTo("asignar_tipo_empresa_cargo") == 0) {
            Administrar_Tipo_Empresa_Cargo registro = new Administrar_Tipo_Empresa_Cargo(request);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Empresa_Cargo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
