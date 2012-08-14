/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mae.funciones_entrada;

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
import websae.mae.eventos.Administrar_Actividad;
import websae.mae.eventos.Administrar_Auspicio;
import websae.mae.eventos.Administrar_Categoria;
import websae.mae.eventos.Administrar_Categoria_Evento;
import websae.mae.eventos.Administrar_Conferencista_Evento;
import websae.mae.eventos.Administrar_Evento;
import websae.mae.eventos.Administrar_Faq;
import websae.mae.eventos.Administrar_Fecha_Evento;
import websae.mae.eventos.Administrar_Material;
import websae.mae.eventos.Administrar_Organizador;
import websae.mae.eventos.Administrar_Subcategoria;
import websae.mae.eventos.Administrar_Subcategoria_Evento;
import websae.mae.eventos.Administrar_Subevento;
import websae.mae.eventos.Administrar_Tipo_Evento;
import websae.mae.eventos.Administrar_Tipo_Material;
import websae.mae.eventos.Administrar_Evento_Opcion;
import websae.mae.eventos.Administrar_Registro;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_ae_administrar_objetos extends HttpServlet {
   
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
        String id_subevento = (String) request.getParameter("id_subevento");
        
        // <editor-fold defaultstate="collapsed" desc="Administración del Tipo de Evento">
        if (tipo.compareTo("registrar_tipo_evento") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Evento registro = new Administrar_Tipo_Evento(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_tipo_evento") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Evento registro = new Administrar_Tipo_Evento(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_tipo_evento") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Tipo_Evento registro = new Administrar_Tipo_Evento(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de Eventos">
        } else if (tipo.compareTo("registrar_evento") == 0) {
            /** Registro del Evento. */
            Administrar_Evento registro = new Administrar_Evento(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_evento") == 0) {
            /** Registro del Evento. */
            Administrar_Evento registro = new Administrar_Evento(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evento.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la Categoría de los asistentes">
        } else if (tipo.compareTo("registrar_categoria") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Categoria registro = new Administrar_Categoria(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_categoria") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Categoria registro = new Administrar_Categoria(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_categoria") == 0) {
            /** Registro del Tipo de Empresa. */
            Administrar_Categoria registro = new Administrar_Categoria(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración del Tipo de Material">
        } else if (tipo.compareTo("registrar_tipo_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Tipo_Material registro = new Administrar_Tipo_Material(request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_tipo_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Tipo_Material registro = new Administrar_Tipo_Material(request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_tipo_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Tipo_Material registro = new Administrar_Tipo_Material(request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Tipo_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración del Material">
        } else if (tipo.compareTo("registrar_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.REGISTRAR, id_evento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.MODIFICAR, id_evento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_material") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.ELIMINAR, id_evento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración del Material de SubEvento">
        } else if (tipo.compareTo("registrar_material_subevento") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.REGISTRAR, id_subevento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_material_subevento") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.MODIFICAR, id_subevento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_material_subevento") == 0) {
            /** Registro del Tipo de Material. */
            Administrar_Material registro = new Administrar_Material(request, Constante.ELIMINAR, id_subevento);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Material.obtener_mensaje( registro.getRespuesta(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de la asignación de las opciones de un Evento">
        } else if (tipo.compareTo("asignar_evento_opcion") == 0) {
            /** Registro de la asignacion de un evento a diversas opciones. */
            Administrar_Evento_Opcion registro = new Administrar_Evento_Opcion( id_evento, request.getParameter("id_opcion"), request.getParameter("accion"));
            registro.actualizar_evento_opcion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Evento_Opcion.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Actualización de FAQs">
        } else if (tipo.compareTo("actualizar_faq") == 0) {
            Administrar_Faq registro = new Administrar_Faq( request, id_evento, Constante.ACTUALIZAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Faq.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de las fechas de una agenda">
        } else if (tipo.compareTo("registrar_agenda") == 0) {
            Administrar_Fecha_Evento registro = new Administrar_Fecha_Evento( request, id_subevento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Fecha_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_agenda") == 0) {
            Administrar_Fecha_Evento registro = new Administrar_Fecha_Evento( request, id_subevento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Fecha_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_agenda") == 0) {
            Administrar_Fecha_Evento registro = new Administrar_Fecha_Evento( request, id_subevento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Fecha_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de un auspiciante">
        } else if (tipo.compareTo("registrar_auspiciante") == 0) {
            Administrar_Auspicio registro = new Administrar_Auspicio( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Auspicio.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_auspiciante") == 0) {
            Administrar_Auspicio registro = new Administrar_Auspicio( request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Auspicio.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_auspiciante") == 0) {
            Administrar_Auspicio registro = new Administrar_Auspicio( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Auspicio.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de las actividades de una agenda, llamado horario">
        } else if (tipo.compareTo("registrar_horario") == 0) {
            Administrar_Actividad registro = new Administrar_Actividad( request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Actividad.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_horario") == 0) {
            Administrar_Actividad registro = new Administrar_Actividad( request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Actividad.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_horario") == 0) {
            Administrar_Actividad registro = new Administrar_Actividad( request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Actividad.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la subcategoria de un evento">
        } else if (tipo.compareTo("registrar_subcategoria") == 0) {
            Administrar_Subcategoria registro = new Administrar_Subcategoria( request, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_subcategoria") == 0) {
            Administrar_Subcategoria registro = new Administrar_Subcategoria( request, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_subcategoria") == 0) {
            Administrar_Subcategoria registro = new Administrar_Subcategoria( request, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la subcategoria asignadas a un evento, los porcentajes de los descuentos.">
        } else if (tipo.compareTo("registrar_subcategoria_evento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_subcategoria_evento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_subcategoria_evento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la subcategoria asignadas a un subevento, los porcentajes de los descuentos.">
        } else if (tipo.compareTo("registrar_subcategoria_subevento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_subevento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_subcategoria_subevento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_subevento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_subcategoria_subevento") == 0) {
            Administrar_Subcategoria_Evento registro = new Administrar_Subcategoria_Evento( request, id_subevento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subcategoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de la Categoría de los eventos">
        } else if (tipo.compareTo("registrar_categoria_evento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_categoria_evento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_categoria_evento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de la Categoría de los SubEventos">
        } else if (tipo.compareTo("registrar_categoria_subevento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_subevento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_categoria_subevento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_subevento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_categoria_subevento") == 0) {
            Administrar_Categoria_Evento registro = new Administrar_Categoria_Evento( request, id_subevento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Categoria_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los conferencistas del evento">
        } else if (tipo.compareTo("registrar_conferencista_evento") == 0) {
            Administrar_Conferencista_Evento registro = new Administrar_Conferencista_Evento( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Conferencista_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("actualizar_conferencista_evento") == 0) {
            Administrar_Conferencista_Evento registro = new Administrar_Conferencista_Evento( request, id_evento, Constante.ACTUALIZAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Conferencista_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_conferencista_evento") == 0) {
            Administrar_Conferencista_Evento registro = new Administrar_Conferencista_Evento( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Conferencista_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los conferencistas del subevento">
        } else if (tipo.compareTo("registrar_conferencista_subevento") == 0) {
            Administrar_Conferencista_Evento registro = new Administrar_Conferencista_Evento( request, id_subevento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Conferencista_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_conferencista_subevento") == 0) {
            Administrar_Conferencista_Evento registro = new Administrar_Conferencista_Evento( request, id_subevento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Conferencista_Evento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los Organizadores">
        } else if (tipo.compareTo("registrar_organizador") == 0) {
            Administrar_Organizador registro = new Administrar_Organizador( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Organizador.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_organizador") == 0) {
            Administrar_Organizador registro = new Administrar_Organizador( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Organizador.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Administración de los subeventos de un evento">
        } else if (tipo.compareTo("registrar_subevento") == 0) {
            Administrar_Subevento registro = new Administrar_Subevento( request, id_evento, Constante.REGISTRAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subevento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("modificar_subevento") == 0) {
            Administrar_Subevento registro = new Administrar_Subevento( request, id_evento, Constante.MODIFICAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subevento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("eliminar_subevento") == 0) {
            Administrar_Subevento registro = new Administrar_Subevento( request, id_evento, Constante.ELIMINAR);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Subevento.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Administración de los registros a un evento.">
        } else if (tipo.compareTo("registrar_usuario_evento") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Constante.REGISTRAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("cancelar_registrar_usuario_evento") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Constante.ELIMINAR, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_peticion();
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("aprobar_registro_usuario") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Estado.APROBAR_REGISTRO, null, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_registro( );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("rechazar_registro_usuario") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Estado.RECHAZAR_REGISTRO, null, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_registro( );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("pagar_usuario_evento") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Estado.PAGO_PENDIENTE, null, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_registro( );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("aprobar_pago_usuario") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Estado.REGISTRADO, null, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_registro( );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
            out.close();
        } else if (tipo.compareTo("rechazar_pago_usuario") == 0) {
            Administrar_Registro registro = new Administrar_Registro( request, Estado.RECHAZAR_PAGO, null, getServletContext().getInitParameter("email.host"), getServletContext().getRealPath( File.separator )+"WEB-INF"+File.separator+"emails"+File.separator);
            registro.procesar_registro( );
            /** Preparacion del mensaje a enviar al cliente. */
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( Administrar_Registro.obtener_mensaje( registro.getMensaje(), lang ) );
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
