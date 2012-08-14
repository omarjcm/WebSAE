/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mce.funciones_entrada;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Constante;
import websae.informacion.Estado;
import websae.mce.eventos.Administrar_Alternativa;
import websae.mce.eventos.Administrar_Articulo;
import websae.mce.eventos.Administrar_Convocatoria;
import websae.mce.eventos.Administrar_Evaluacion;
import websae.mce.eventos.Administrar_Evaluacion_Articulo;
import websae.mce.eventos.Administrar_Miembro_Comite_Evento;
import websae.mce.eventos.Administrar_Pregunta;
import websae.mce.eventos.Administrar_Seccion;
import websae.mce.eventos.Administrar_Tema;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_ce_administrar_objetos extends HttpServlet {
   
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
        /** Inicializando la variable del ID del evento. */
        String id_evento = "";
        if (sesion.getAttribute("id_evento") != null)
            id_evento = (String) sesion.getAttribute("id_evento");

        String tipo = (String) request.getParameter("tipo");
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la Convocatoria.">
        if (tipo.compareTo("registrar_convocatoria") == 0) {
            Administrar_Convocatoria registro = new Administrar_Convocatoria(request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Convocatoria.obtener_mensaje( registro.getRespuesta(), registro.getId_Convocatoria(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_convocatoria") == 0) {
            Administrar_Convocatoria registro = new Administrar_Convocatoria(request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Convocatoria.obtener_mensaje( registro.getRespuesta(), registro.getId_Convocatoria(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los Temas de una convocatoria.">
        } else if (tipo.compareTo("registrar_tema") == 0) {
            Administrar_Tema registro = new Administrar_Tema(request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tema.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_tema") == 0) {
            Administrar_Tema registro = new Administrar_Tema(request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tema.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_tema") == 0) {
            Administrar_Tema registro = new Administrar_Tema(request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tema.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de la Evaluacion de los Papers.">
        } else if (tipo.compareTo("registrar_evaluacion") == 0) {
            Administrar_Evaluacion registro = new Administrar_Evaluacion(request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_evaluacion") == 0) {
            Administrar_Evaluacion registro = new Administrar_Evaluacion(request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_evaluacion") == 0) {
            Administrar_Evaluacion registro = new Administrar_Evaluacion(request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de la Seccion de una Evaluacion.">
        } else if (tipo.compareTo("registrar_seccion") == 0) {
            Administrar_Seccion registro = new Administrar_Seccion(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Seccion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_seccion") == 0) {
            Administrar_Seccion registro = new Administrar_Seccion(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Seccion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_seccion") == 0) {
            Administrar_Seccion registro = new Administrar_Seccion(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Seccion.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de Preguntas de una Seccion.">
        } else if (tipo.compareTo("registrar_pregunta") == 0) {
            Administrar_Pregunta registro = new Administrar_Pregunta(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Pregunta.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_pregunta") == 0) {
            Administrar_Pregunta registro = new Administrar_Pregunta(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Pregunta.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_pregunta") == 0) {
            Administrar_Pregunta registro = new Administrar_Pregunta(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Pregunta.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de las alternativas de una Pregunta.">
        } else if (tipo.compareTo("registrar_alternativa") == 0) {
            Administrar_Alternativa registro = new Administrar_Alternativa(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Alternativa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_alternativa") == 0) {
            Administrar_Alternativa registro = new Administrar_Alternativa(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Alternativa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_alternativa") == 0) {
            Administrar_Alternativa registro = new Administrar_Alternativa(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Alternativa.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración del Articulo.">
        } else if (tipo.compareTo("subir_articulo") == 0) {
            Administrar_Articulo registro = new Administrar_Articulo(request, id_evento, Constante.REGISTRAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_articulo") == 0) {
            Administrar_Articulo registro = new Administrar_Articulo(request, id_evento, Constante.MODIFICAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la asignacion de los Miembros del Comite (Evaluadores y Miembros del CP).">
        } else if (tipo.compareTo("registrar_miembro_comite") == 0) {
            Administrar_Miembro_Comite_Evento registro = new Administrar_Miembro_Comite_Evento(request, id_evento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Miembro_Comite_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_miembro_comite") == 0) {
            Administrar_Miembro_Comite_Evento registro = new Administrar_Miembro_Comite_Evento(request, id_evento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Miembro_Comite_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración del evaluacion del artículo.">
        } else if (tipo.compareTo("aprobar_resumen") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, id_evento, Estado.APROBADO, Constante.REGISTRAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("rechazar_resumen") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, id_evento, Estado.RECHAZADO, Constante.REGISTRAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("aprobar_articulo") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, Estado.APROBADO, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("rechazar_articulo") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, Estado.RECHAZADO, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("registrar_asignar_evaluador") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, id_evento, Constante.AGREGAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_asignar_evaluador") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, id_evento, Constante.ELIMINAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("evaluar_articulo") == 0) {
            Administrar_Evaluacion_Articulo registro = new Administrar_Evaluacion_Articulo(request, Constante.REGISTRAR+"_EVALUACION" );
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evaluacion_Articulo.obtener_mensaje( registro.getRespuesta(), lang ) );
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
