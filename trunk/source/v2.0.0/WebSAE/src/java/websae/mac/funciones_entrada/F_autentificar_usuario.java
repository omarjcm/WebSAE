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
import websae.mac.dominio.AC_Usuario;
import websae.mac.eventos.Autentificar_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_autentificar_usuario extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String lang = "es";
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("lang") != null)
            lang = (String) sesion.getAttribute("lang");

        if (accion != null && accion.compareTo("autentificar") == 0) {
            /** Extraer informacion del usuario, si la hubiere. */
            Autentificar_Usuario registro = new Autentificar_Usuario(request);    
            
            /** Preparacion del mensaje para enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Autentificar_Usuario.obtener_mensaje( registro.getMensaje(), registro.getAdmin(), lang ) );
            out.close();
        } else if (accion != null && accion.compareTo("verificar") == 0) {
            Autentificar_Usuario registro = new Autentificar_Usuario((AC_Usuario) sesion.getAttribute("usuario"));
            
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Autentificar_Usuario.obtener_mensaje( registro.getMensaje(), registro.getAdmin(), lang ) );
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
