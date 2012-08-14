/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mgi.funciones_entrada;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Constante;
import websae.mgi.eventos.Administrar_Grupo_Investigacion;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_gi_administrar_objetos extends HttpServlet {
   
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
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los Grupos de Investigacion">
        if (tipo.compareTo("registrar_grupo_investigacion") == 0) {
            /** Registro de los Grupo de Investigacion. */
            Administrar_Grupo_Investigacion registro = new Administrar_Grupo_Investigacion(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Grupo_Investigacion.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_grupo_investigacion") == 0) {
            /** Registro de los Grupo de Investigacion. */
            Administrar_Grupo_Investigacion registro = new Administrar_Grupo_Investigacion(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Grupo_Investigacion.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_grupo_investigacion") == 0) {
            /** Registro de los Grupo de Investigacion. */
            Administrar_Grupo_Investigacion registro = new Administrar_Grupo_Investigacion(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Grupo_Investigacion.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        }
        // </editor-fold>
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
