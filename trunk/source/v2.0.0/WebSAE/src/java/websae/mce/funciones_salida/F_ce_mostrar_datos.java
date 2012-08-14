/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mce.funciones_salida;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Estado;
import websae.informacion.Funciones;
import websae.mac.dominio.AC_Usuario;
import websae.mce.dominio.CE_Alternativa;
import websae.mce.dominio.CE_Archivo;
import websae.mce.dominio.CE_Articulo;
import websae.mce.dominio.CE_Autor_Articulo;
import websae.mce.dominio.CE_Convocatoria;
import websae.mce.dominio.CE_Evaluacion;
import websae.mce.dominio.CE_Evaluacion_Articulo;
import websae.mce.dominio.CE_Miembro_Comite_Evento;
import websae.mce.dominio.CE_Pregunta;
import websae.mce.dominio.CE_Seccion;
import websae.mce.dominio.CE_Tema;
import websae.mce.dominio.CE_Tipo_Pregunta;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_ce_mostrar_datos extends HttpServlet {
   
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
        /** Inicializando la variable del ID del evento. */
        String id_evento = "";
        if (sesion.getAttribute("id_evento") != null)
            id_evento = (String) sesion.getAttribute("id_evento");

        String tipo = (String) request.getParameter("tipo");

        if (tipo.compareTo("buscar_convocatoria") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Convocatoria.toJSONObject( CE_Convocatoria.buscar_convocatoria( id_evento) ) );
            out.close();
        } else if (tipo.compareTo("temas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Tema.toJSON( CE_Tema.temas( request.getParameter("id_convocatoria")  ) ) );
            out.close();
        } else if (tipo.compareTo("temas_por_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Tema.toJSON( CE_Tema.temas_por_evento( id_evento ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_evaluacion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion.toJSONObject( CE_Evaluacion.buscar_evaluacion( id_evento ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_evaluacion_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion.toJSONObject( CE_Evaluacion.buscar_evaluacion( request.getParameter("id_evento") ) ) );
            out.close();
        } else if (tipo.compareTo("secciones") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Seccion.toJSON( CE_Seccion.secciones( request.getParameter("id_evaluacion") ) ) );
            out.close();
        } else if (tipo.compareTo("tipo_preguntas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Tipo_Pregunta.toJSON( CE_Tipo_Pregunta.tipo_preguntas( ) ) );
            out.close();
        } else if (tipo.compareTo("preguntas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Pregunta.toJSON( CE_Pregunta.preguntas( request.getParameter("id_seccion") ) ) );
            out.close();
        } else if (tipo.compareTo("alternativas") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Alternativa.toJSON( CE_Alternativa.alternativas( request.getParameter("id_pregunta") ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_seccion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Seccion.toJSONObject( CE_Seccion.buscar_seccion( request.getParameter("id_seccion") ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_evaluacion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion.Evaluacion_toJSONObject( CE_Evaluacion.buscar_evaluacion( id_evento ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_evaluacion_articulo") == 0) {
            CE_Evaluacion evaluacion = (CE_Evaluacion) sesion.getAttribute("evaluacion");
            if (evaluacion == null) {
                evaluacion = CE_Evaluacion.buscar_evaluacion( Funciones.getBigDecimal( request.getParameter("id_articulo") ) );
                sesion.setAttribute("evaluacion", evaluacion);
            }
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion.Evaluacion_toJSONObject( evaluacion ) );
            out.close();
        } else if (tipo.compareTo("mostrar_autores") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            CE_Articulo articulo = (CE_Articulo) sesion.getAttribute("articulo");
            PrintWriter out = response.getWriter();
            out.print( CE_Autor_Articulo.toJSON( articulo.ref_autor_articulo ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.buscar_articulo((AC_Usuario) sesion.getAttribute("usuario"), id_evento) ) );
            out.close();
        } else if (tipo.compareTo("buscar_articulo_evaluacion") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSONObject( CE_Articulo.buscar_articulo( request.getParameter("id_articulo"), lang ) ) );
            out.close();
        } else if (tipo.compareTo("evaluacion_total_articulo") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion_Articulo.toJSON( CE_Evaluacion_Articulo.respuestas( request.getParameter("id_articulo"), Boolean.TRUE ) ) );
            out.close();
        } else if (tipo.compareTo("evaluacion_usuario_articulo") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Evaluacion_Articulo.toJSON( CE_Evaluacion_Articulo.respuestas( request.getParameter("id_articulo"), Boolean.FALSE ) ) );
            out.close();
        } else if (tipo.compareTo("buscar_articulo") == 0) {
            CE_Articulo articulo = CE_Articulo.buscar_articulo((AC_Usuario) sesion.getAttribute("usuario"), id_evento, request.getParameter("id_articulo"));
            sesion.setAttribute("articulo", articulo);
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSONObject( articulo ) );
            out.close();
        } else if (tipo.compareTo("articulo_asignado") == 0) {
            CE_Articulo articulo = CE_Articulo.articulo_asignado((AC_Usuario) sesion.getAttribute("usuario"), id_evento, request.getParameter("id_articulo"));
            sesion.setAttribute("articulo", articulo);
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSONObject( articulo ) );
            out.close();
        } else if (tipo.compareTo("mostrar_archivos_por_articulo") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Archivo.toJSON( CE_Archivo.mostrar_archivos_por_articulo( Funciones.getBigDecimal( request.getParameter("id_articulo") ) ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_recibidos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_recibidos( id_evento ) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_pendientes") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.PENDIENTE) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_en_consulta") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.EN_CONSULTA) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_evaluados") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.EVALUADO) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_aprobados") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.APROBADO) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_rechazados") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.RECHAZADO) ) );
            out.close();
        } else if (tipo.compareTo("mostrar_articulos_en_espera") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Articulo.toJSON( CE_Articulo.mostrar_articulos_por_estado(id_evento, Estado.EN_ESPERA) ) );
            out.close();
        } else if (tipo.compareTo("miembros_comite_tecnico") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Miembro_Comite_Evento.toJSON( CE_Miembro_Comite_Evento.miembros_comite_evento( id_evento ) ) );
            out.close();
        } else if (tipo.compareTo("evaluadores_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Miembro_Comite_Evento.toJSON( CE_Miembro_Comite_Evento.evaluadores_evento( id_evento, request.getParameter("id_articulo") ) ) );
            out.close();
        } else if (tipo.compareTo("evaluadores_articulo") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Miembro_Comite_Evento.toJSON( CE_Miembro_Comite_Evento.evaluadores_articulo( request.getParameter("id_articulo") ) ) );
            out.close();
        } else if (tipo.compareTo("articulos_evaluador") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            AC_Usuario usuario = (AC_Usuario) request.getSession().getAttribute("usuario");
            out.print( CE_Articulo.toJSON( CE_Articulo.articulos_evaluador( usuario.getUs_id_usuario() ) ) );
            out.close();
        } else if (tipo.compareTo("administradores_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( CE_Miembro_Comite_Evento.toJSON( CE_Miembro_Comite_Evento.administradores_evento( id_evento ) ) );
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
