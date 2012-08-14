/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.funciones_entrada;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.msu.eventos.Recordar_Clave;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_recordar_clave extends HttpServlet {
   
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
        
        Recordar_Clave registro = new Recordar_Clave( request.getParameter("txt_correo"),
                                                      getServletContext().getInitParameter("email.host"),
                                                      getServletContext().getInitParameter("email.admin"),
                                                      getServletContext().getInitParameter("email.remitente"),
                                                      getServletContext().getInitParameter("admin.nombre"),
                                                      getServletContext().getRealPath("/")+"WEB-INF/emails/",
                                                      lang );
        registro.recordar_clave();
        
        /** Preparacion del mensaje a enviar al cliente. */
        response.setContentType("application/json; charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        out.print( Recordar_Clave.obtener_mensaje( registro.getMensaje(), lang ) );
        out.close();
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
