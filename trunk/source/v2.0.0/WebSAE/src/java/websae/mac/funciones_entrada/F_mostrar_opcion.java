/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mac.funciones_entrada;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.mac.dominio.AC_Opcion;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_mostrar_opcion extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        /** PASO 1: Seteo del lenguaje a mostrar */
        String lang = "es";
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("lang") != null)
            lang = (String) sesion.getAttribute("lang");
            
        /** PASO 2: Se requiere el tipo de busqueda de las opciones en el menu */
        if ((AC_Usuario) sesion.getAttribute("usuario") == null) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AC_Opcion.toJSONOpciones( AC_Opcion.mostrar_opciones( lang, request.getParameter("tipo") ) ) );
            out.close();
        } else {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AC_Opcion.toJSONOpciones( AC_Opcion.mostrar_opciones(((AC_Usuario) sesion.getAttribute("usuario")).getUs_email(), lang, request.getParameter("tipo")) ) );
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
