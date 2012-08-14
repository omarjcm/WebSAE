/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mce.funciones_entrada;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Constante;
import websae.mce.eventos.Administrar_CE_Autor_Articulo;
import websae.mce.eventos.Administrar_CE_Evaluacion;
import websae.mce.eventos.Administrar_CE_Tema;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_ce_administrar_detalles extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String lang = "es";
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("lang") != null)
            lang = (String) sesion.getAttribute("lang");
        
        String tipo = request.getParameter("tipo");
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los Temas.">
        if (tipo.compareTo("agregar_tema") == 0) {
            Administrar_CE_Tema registro = new Administrar_CE_Tema(request, Constante.AGREGAR);
            registro.procesar_peticion();
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Tema.obtener_mensaje( registro.getMensaje() ) );
            out.close();
        } else if (tipo.compareTo("eliminar_tema") == 0) {
            Administrar_CE_Tema registro = new Administrar_CE_Tema(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Tema.obtener_mensaje( registro.getMensaje() ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los Autores de un paper.">
        } else if (tipo.compareTo("agregar_autor_convocatoria") == 0) {
            Administrar_CE_Autor_Articulo registro = new Administrar_CE_Autor_Articulo(request);
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Autor_Articulo.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("agregar_autor") == 0) {
            Administrar_CE_Autor_Articulo registro = new Administrar_CE_Autor_Articulo(request, Constante.AGREGAR);
            registro.procesar_peticion();
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Autor_Articulo.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_autor") == 0) {
            Administrar_CE_Autor_Articulo registro = new Administrar_CE_Autor_Articulo(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Autor_Articulo.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_autor") == 0) {
            Administrar_CE_Autor_Articulo registro = new Administrar_CE_Autor_Articulo(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Autor_Articulo.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de la evaluacion de un artículo.">
        } else if (tipo.compareTo("ingresar_respuesta") == 0) {
            Administrar_CE_Evaluacion registro = new Administrar_CE_Evaluacion(request);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_CE_Evaluacion.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
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
