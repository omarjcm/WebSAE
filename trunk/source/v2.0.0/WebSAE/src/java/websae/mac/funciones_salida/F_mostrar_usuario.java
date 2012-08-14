/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mac.funciones_salida;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.mac.dominio.AC_Usuario;
import websae.mac.eventos.Mostrar_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_mostrar_usuario extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        String lang = "es";
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("lang") != null)
            lang = (String) sesion.getAttribute("lang");
        String id_evento = "";
        if (sesion.getAttribute("id_evento") != null)
            id_evento = (String) sesion.getAttribute("id_evento");

        if (tipo.compareTo("por_email") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.obtener_mensaje(Mostrar_Usuario.buscar_usuario_por_email( (String) request.getParameter("email") ), lang) );
            out.close();
        } else if (tipo.compareTo("buscar_conferencista_por_email") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.obtener_mensaje(Mostrar_Usuario.buscar_conferencista_por_email( (String) request.getParameter("email"), id_evento ), lang) );
            out.close();
        } else if (tipo.compareTo("buscar_evaluador_por_email") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.obtener_mensaje(Mostrar_Usuario.buscar_evaluador_por_email( (String) request.getParameter("email"), id_evento ), lang) );
            out.close();
        } else if (tipo.compareTo("buscar_usuario") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AC_Usuario.toJSONObject( AC_Usuario.buscar_usuario( (String) request.getParameter("id_usuario") ) ) );
            out.close();
        } else if (tipo.compareTo("por_nombre_apellido") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.toJSON( Mostrar_Usuario.buscar_usuario_por_nombre_apellido( (String) request.getParameter("nombre_apellido"), "WebSAE" ), Integer.parseInt( request.getParameter("results") ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_conferencista_por_nombre_apellido") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.toJSON( Mostrar_Usuario.buscar_conferencista_por_nombre_apellido( (String) request.getParameter("nombre_apellido"), id_evento ), Integer.parseInt( request.getParameter("results") ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_evaluador_por_nombre_apellido") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.toJSON( Mostrar_Usuario.buscar_evaluador_por_nombre_apellido( (String) request.getParameter("nombre_apellido"), id_evento ), Integer.parseInt( request.getParameter("results") ) ) );
            out.close();
        } else if (tipo.compareTo("sagi_por_email") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.obtener_mensaje(Mostrar_Usuario.buscar_usuario_por_email( (String) request.getParameter("email"), "WebSAGI" ), lang) );
            out.close();
        } else if (tipo.compareTo("sagi_por_nombre_apellido") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Mostrar_Usuario.toJSON( Mostrar_Usuario.buscar_usuario_por_nombre_apellido( (String) request.getParameter("nombre_apellido"), "WebSAGI" ), Integer.parseInt( request.getParameter("results") ) ) );
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
