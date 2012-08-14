/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mgi.funciones_salida;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.mgi.dominio.GI_Grupo_Investigacion;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_gi_mostrar_datos extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String id_evento = "";
        if (sesion.getAttribute("id_evento") != null)
            id_evento = (String) sesion.getAttribute("id_evento");

        String tipo = (String) request.getParameter("tipo");

        if (tipo.compareTo("grupos_investigacion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( GI_Grupo_Investigacion.toJSON( GI_Grupo_Investigacion.grupos_investigacion() ) );
            out.close();
        } else if (tipo.compareTo("mostrar_grupos_investigacion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( GI_Grupo_Investigacion.toJSON( GI_Grupo_Investigacion.mostrar_grupos_investigacion("WebSAE") ) );
            out.close();
        } else if (tipo.compareTo("mostrar_grupos_investigacion_websagi") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( GI_Grupo_Investigacion.toJSON( GI_Grupo_Investigacion.mostrar_grupos_investigacion("WebSAGI") ) );
            out.close();
        } else if (tipo.compareTo("mostrar_grupos_investigacion_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( GI_Grupo_Investigacion.toJSON( GI_Grupo_Investigacion.mostrar_grupos_investigacion_evento( id_evento) ) );
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
