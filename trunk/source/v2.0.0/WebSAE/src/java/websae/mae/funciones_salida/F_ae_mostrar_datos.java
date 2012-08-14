/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websae.mae.funciones_salida;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websae.informacion.Funciones;
import websae.mac.dominio.AC_Opcion;
import websae.mac.dominio.AC_Usuario;
import websae.mae.dominio.AE_Actividad;
import websae.mae.dominio.AE_Auspicio;
import websae.mae.dominio.AE_Categoria;
import websae.mae.dominio.AE_Categoria_Evento;
import websae.mae.dominio.AE_Conferencista_Evento;
import websae.mae.dominio.AE_Evento;
import websae.mae.dominio.AE_Faq;
import websae.mae.dominio.AE_Fecha_Evento;
import websae.mae.dominio.AE_Material;
import websae.mae.dominio.AE_Organizador;
import websae.mae.dominio.AE_Registro;
import websae.mae.dominio.AE_Subcategoria;
import websae.mae.dominio.AE_Subcategoria_Evento;
import websae.mae.dominio.AE_Tipo_Evento;
import websae.mae.dominio.AE_Tipo_Material;

/**
 *
 * @author Guillermo Pizarro
 */
public class F_ae_mostrar_datos extends HttpServlet {

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
        if (sesion.getAttribute("lang") != null) {
            lang = (String) sesion.getAttribute("lang");
        }
        /** Inicializando la variable del ID del evento. */
        String id_evento = "";
        if (sesion.getAttribute("id_evento") != null) {
            id_evento = (String) sesion.getAttribute("id_evento");
        }

        String tipo = (String) request.getParameter("tipo");
        String id_subevento = (String) request.getParameter("id_subevento");

        if (tipo.compareTo("tipos_eventos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Tipo_Evento.toJSON(AE_Tipo_Evento.tipos_eventos()));
            out.close();
        } else if (tipo.compareTo("eventos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Evento.toJSON(AE_Evento.eventos(lang), lang));
            out.close();
        } else if (tipo.compareTo("subeventos") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Evento.toJSON(AE_Evento.subeventos(id_evento, lang), lang));
            out.close();
        } else if (tipo.compareTo("categorias") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria.toJSON(AE_Categoria.categorias()));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria.toJSON(AE_Categoria.mostrar_categorias()));
            out.close();
        } else if (tipo.compareTo("buscar_categoria") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria.toJSONObject(AE_Categoria.buscar_categoria((String) request.getParameter("id_categoria"))));
            out.close();
        } else if (tipo.compareTo("buscar_evento_lang") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            AE_Evento evento = AE_Evento.buscar_evento(request.getParameter("id_evento"), lang);
            sesion.setAttribute("evento_logo", evento.getEv_imagen());
            out.print(AE_Evento.toJSONObject(evento, lang));
            out.close();
        } else if (tipo.compareTo("buscar_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Evento.toJSONObject(AE_Evento.buscar_evento(request.getParameter("id_evento"))));
            out.close();
        } else if (tipo.compareTo("tipos_materiales") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Tipo_Material.toJSON(AE_Tipo_Material.tipos_materiales()));
            out.close();
        } else if (tipo.compareTo("materiales") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Material.toJSON(AE_Material.materiales(request.getParameter("id_evento")), lang));
            out.close();
        } else if (tipo.compareTo("materiales_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Material.toJSON(AE_Material.materiales(id_subevento), lang));
            out.close();
        } else if (tipo.compareTo("mostrar_opciones_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AC_Opcion.toJSONOpciones(AC_Opcion.mostrar_opciones_evento(lang, "E")));
            out.close();
        } else if (tipo.compareTo("mostrar_opciones_por_id_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AC_Opcion.toJSONOpciones(AC_Opcion.mostrar_opciones_por_id_evento(id_evento, lang, "E", false)));
            out.close();
        } else if (tipo.compareTo("mostrar_fecha_actual") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(Funciones.fecha_actual_toJSON());
            out.close();
        } else if (tipo.compareTo("mostrar_agenda") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Fecha_Evento.toJSON(AE_Fecha_Evento.buscar_fecha_evento(id_evento), id_evento));
            out.close();
        } else if (tipo.compareTo("mostrar_agenda_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Fecha_Evento.toJSON(AE_Fecha_Evento.buscar_fecha_evento(id_subevento), id_subevento));
            out.close();
        } else if (tipo.compareTo("mostrar_actividad") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Actividad.toJSON(AE_Actividad.buscar_actividad(request.getParameter("id_fecha_evento"))));
            out.close();
        } else if (tipo.compareTo("mostrar_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            AE_Evento evento = AE_Evento.buscar_evento(id_evento);
            out.print(AE_Evento.toJSONObject(evento));
            sesion.setAttribute("permiso", AE_Evento.comprobar_permiso(evento));
            out.close();
        } else if (tipo.compareTo("mostrar_faq") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Faq.toJSONObject(AE_Faq.buscar_faq(id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_auspiciantes") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Auspicio.toJSON(AE_Auspicio.mostrar_auspiciantes(id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_subcategorias") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Subcategoria.toJSON(AE_Subcategoria.mostrar_subcategorias(request.getParameter("id_categoria"))));
            out.close();
        } else if (tipo.compareTo("subcategorias") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Subcategoria.toJSON(AE_Subcategoria.subcategorias()));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria_Evento.toJSON(AE_Categoria_Evento.mostrar_categorias_evento(id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias_evento_entrehoy") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria_Evento.toJSON(AE_Categoria_Evento.mostrar_categorias_evento_entrehoy(id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria_Evento.toJSON(AE_Categoria_Evento.mostrar_categorias_evento(id_subevento)));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias_subevento_entrehoy") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria_Evento.toJSON(AE_Categoria_Evento.mostrar_categorias_evento_entrehoy(id_subevento)));
            out.close();

        } else if (tipo.compareTo("mostrar_categorias_por_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria.toJSON(AE_Categoria.mostrar_categorias_por_evento(id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_categorias_por_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Categoria.toJSON(AE_Categoria.mostrar_categorias_por_evento(id_subevento)));
            out.close();
        } else if (tipo.compareTo("conferencistas_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_evento, AE_Conferencista_Evento.CONFERENCISTA)));
            out.close();
        } else if (tipo.compareTo("conferencistas_evento_invitado") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_evento, AE_Conferencista_Evento.CONFERENCISTA_INVITADO)));
            out.close();
        } else if (tipo.compareTo("conferencistas_evento_expositor") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_evento, AE_Conferencista_Evento.CONFERENCISTA_EXPOSITOR)));
            out.close();
        } else if (tipo.compareTo("conferencistas_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_subevento, AE_Conferencista_Evento.CONFERENCISTA)));
            out.close();
        } else if (tipo.compareTo("conferencistas_subevento_invitado") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_subevento, AE_Conferencista_Evento.CONFERENCISTA_INVITADO)));
            out.close();
        } else if (tipo.compareTo("conferencistas_subevento_expositor") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Conferencista_Evento.toJSON(AE_Conferencista_Evento.conferencistas_evento(id_subevento, AE_Conferencista_Evento.CONFERENCISTA_EXPOSITOR)));
            out.close();
        } else if (tipo.compareTo("mostrar_subcategorias_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Subcategoria_Evento.toJSON(AE_Subcategoria_Evento.mostrar_subcategorias_evento(request.getParameter("id_categoria"), id_evento)));
            out.close();
        } else if (tipo.compareTo("mostrar_subcategorias_subevento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Subcategoria_Evento.toJSON(AE_Subcategoria_Evento.mostrar_subcategorias_evento(request.getParameter("id_categoria"), id_subevento)));
            out.close();
        } else if (tipo.compareTo("mostrar_organizadores_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Organizador.toJSON(AE_Organizador.mostrar_organizadores_evento(id_evento)));
            out.close();
        } else if (tipo.compareTo("registros") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Registro.toJSON(AE_Registro.registros(id_evento, (AC_Usuario) sesion.getAttribute("usuario"))));
            out.close();
        } else if (tipo.compareTo("estado_registro_evento") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print(AE_Registro.toJSON(AE_Registro.registros(request.getParameter("id_evento"), (AC_Usuario) sesion.getAttribute("usuario"))));
            out.close();
        } else if (tipo.compareTo("usuarios_registrados") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AE_Registro.toJSON_Registro(AE_Registro.usuarios_registrados(id_evento, (String) sesion.getAttribute("lang")), (String) sesion.getAttribute("lang")) );
            out.close();
        } else if (tipo.compareTo("usuarios_pendientes") == 0) {
            response.setContentType("application/json; charset=ISO-8859-1");
            PrintWriter out = response.getWriter();
            out.print( AE_Registro.toJSON_Registro(AE_Registro.usuarios_pendientes(id_evento, (String) sesion.getAttribute("lang")), (String) sesion.getAttribute("lang")) );
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
