/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.funciones_salida;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.mac.dominio.AC_Opcion;
import websae.mae.dominio.AE_Evento;
import websae.msu.dominio.SU_Cargo;
import websae.msu.dominio.SU_Ciudad;
import websae.msu.dominio.SU_Empresa;
import websae.msu.dominio.SU_Pais;
import websae.msu.dominio.SU_Tipo_Empresa;
import websae.msu.dominio.SU_Titulo;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_mostrar_datos extends HttpServlet {
   
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
        
        String tipo = (String) request.getParameter("tipo");
        
        if (tipo.compareTo("mostrar_paises") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Pais.toJSON( SU_Pais.mostrar_paises() ) );
            out.close();
        } else if (tipo.compareTo("mostrar_ciudades") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Ciudad.toJSON( SU_Ciudad.mostrar_ciudades( request.getParameter("id_pais") ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_titulos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Titulo.toJSON( SU_Titulo.mostrar_titulos() ) );
            out.close();
        } else if (tipo.compareTo("titulos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Titulo.toJSON( SU_Titulo.titulos() ) );
            out.close();
        } else if (tipo.compareTo("tipos_empresas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Tipo_Empresa.toJSON( SU_Tipo_Empresa.tipos_empresas() ) );
            out.close();
        } else if (tipo.compareTo("mostrar_tipos_empresas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Tipo_Empresa.toJSON( SU_Tipo_Empresa.mostrar_tipos_empresas() ) );
            out.close();
        } else if (tipo.compareTo("mostrar_cargos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Cargo.toJSON( SU_Cargo.mostrar_cargos_por_tipo_empresa( request.getParameter("id_tipo_empresa") ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_tipos_empresas_por_cargo") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Tipo_Empresa.toJSON( SU_Tipo_Empresa.mostrar_tipos_empresas_por_cargo( request.getParameter("id_cargo") ) ) );
            out.close();
        } else if (tipo.compareTo("cargos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Cargo.toJSON( SU_Cargo.cargos() ) );
            out.close();
        } else if (tipo.compareTo("empresas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Empresa.toJSON( SU_Empresa.empresas( ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_empresas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Empresa.toJSON( SU_Empresa.mostrar_empresas_por_tipo_empresa( request.getParameter("id_tipo_empresa") ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_empresa") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( SU_Empresa.toJSONObject( SU_Empresa.buscar_empresa(request.getParameter("id_empresa")) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_eventos_por_realizar") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AE_Evento.toJSON( AE_Evento.mostrar_eventos_por_realizar( lang ), lang ) );
            out.close();
        } else if (tipo.compareTo("mostrar_eventos_pasados") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AE_Evento.toJSON( AE_Evento.mostrar_eventos_pasados( lang ), lang ) );
            out.close();
        } else if (tipo.compareTo("mostrar_opciones_por_id_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AC_Opcion.toJSONOpciones( AC_Opcion.mostrar_opciones_por_id_evento( (String) sesion.getAttribute("id_evento"), lang, "E", true) ) );
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
