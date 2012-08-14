
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.mail.internet.AddressException;
import mad.eventos.Datos;
import mad.eventos.Transaccion;

import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;

import org.json.JSONException;
import org.json.JSONObject;

import websae.informacion.Constante;
import websae.informacion.Estado;
import websae.informacion.Funciones;

import websae.mce.dominio.CE_Articulo;
import websae.mce.dominio.CE_Evaluacion_Articulo;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mad.objetos.Registro;
import websae.informacion.Email;
import websae.mac.dominio.AC_Usuario;
import websae.mce.dominio.CE_Alternativa;
import websae.mce.dominio.CE_Autor_Articulo;
import websae.mce.dominio.CE_Convocatoria;
import websae.mce.dominio.CE_Evaluacion;
import websae.mce.dominio.CE_Miembro_Comite_Evento;
import websae.mce.dominio.CE_Pregunta;
import websae.mce.dominio.CE_Seccion;
import websae.mce.dominio.CE_Tipo_Pregunta;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Evaluacion_Articulo {
    
    private CE_Evaluacion_Articulo evaluacion_articulo;
    private CE_Evaluacion          evaluacion;
    private AC_Usuario             usuario;
    private BigDecimal             id_articulo;
    private String                 id_evento;
    private String                 estado;
    private String                 host;
    private String                 ruta;
    private String                 lang;
    private HttpSession            sesion;
    
    private Respuesta              respuesta;
    private String                 tipo_accion;
    private Transaccion            transaccion;
    private Datos                  datos;

    // <editor-fold defaultstate="collapsed" desc="Constructor para la Administracion de las Evaluaciones.">
    /**
     * Para la evaluacion de un resumen.
     * @param request
     * @param id_evento
     * @param estado
     * @param tipo_accion
     */
    public Administrar_Evaluacion_Articulo(HttpServletRequest request, String id_evento, String estado, String tipo_accion, String host, String ruta) {
        this.estado              = estado;
        this.tipo_accion = tipo_accion;
        this.id_evento = id_evento;
        this.evaluacion_articulo = new CE_Evaluacion_Articulo();
        this.evaluacion_articulo.setEt_comentario(request.getParameter("txt_observacion"));
        this.sesion = request.getSession();
        this.id_evento = id_evento;
        this.host = host;
        this.ruta = ruta;
        this.lang = (String) this.sesion.getAttribute("lang");
        this.usuario = (AC_Usuario) this.sesion.getAttribute("usuario");
        this.evaluacion_articulo.ref_articulo = (CE_Articulo) this.sesion.getAttribute("articulo");
        this.evaluacion_articulo.ref_articulo.setAr_id_articulo( Funciones.getBigDecimal(request.getParameter("id_articulo")) );

        this.transaccion = new Transaccion("WebSAE");
        this.respuesta   = new Respuesta();
    }

    /**
     * Para la evaluacion de un articulo.
     * @param request
     * @param id_evento
     * @param estado
     * @param tipo_accion
     */
    public Administrar_Evaluacion_Articulo(HttpServletRequest request, String estado, String host, String ruta) {
        this.estado = estado;
        this.host = host;
        this.ruta = ruta;
        this.sesion = request.getSession();
        this.lang = (String) this.sesion.getAttribute("lang");
        this.usuario = (AC_Usuario) this.sesion.getAttribute("usuario");
        this.evaluacion_articulo = new CE_Evaluacion_Articulo();
        this.evaluacion_articulo.ref_articulo.setAr_id_articulo( Funciones.getBigDecimal(request.getParameter("id_articulo")) );
        
        this.transaccion = new Transaccion("WebSAE");
        this.respuesta   = new Respuesta();
    }

    /**
     * Constructor para la asignacion de evaluador a un articulo.
     * @param request
     * @param id_evento
     * @param tipo_accion
     */
    public Administrar_Evaluacion_Articulo(HttpServletRequest request, String id_evento, String tipo_accion, String host, String ruta) {
        this.sesion = request.getSession();
        this.id_evento = id_evento;
        this.host = host;
        this.ruta = ruta;
        this.lang = (String) this.sesion.getAttribute("lang");
        
        this.evaluacion_articulo = new CE_Evaluacion_Articulo();
        this.evaluacion_articulo.ref_articulo.setAr_id_articulo( Funciones.getBigDecimal( request.getParameter("id_articulo") ) );
        this.evaluacion_articulo.ref_evaluador = new CE_Miembro_Comite_Evento();
        this.evaluacion_articulo.ref_evaluador.setMc_es_evaluador( Boolean.TRUE );
        this.evaluacion_articulo.ref_evaluador.ref_usuario.setUs_id_usuario( Funciones.getBigDecimal( request.getParameter("evaluador") ) );
        this.evaluacion_articulo.ref_evaluador.ref_evento.setEv_id_evento( Funciones.getBigDecimal( id_evento ) );
        
        this.tipo_accion = tipo_accion;
        this.datos = new Datos("WebSAE");
        this.respuesta   = new Respuesta();
    }

    /**
     * Constructor para el registro de una evaluacion de un articulo.
     * @param request
     * @param tipo_accion
     */
    public Administrar_Evaluacion_Articulo(HttpServletRequest request, String tipo_accion) {
        this.sesion = request.getSession();
        this.evaluacion = (CE_Evaluacion) this.sesion.getAttribute("evaluacion");
        this.evaluacion.setEv_id_evaluacion( Funciones.getBigDecimal( request.getParameter("id_evaluacion") ) );
        this.usuario = (AC_Usuario) this.sesion.getAttribute("usuario");
        this.id_articulo = Funciones.getBigDecimal( request.getParameter("id_articulo") );
        this.lang = (String) this.sesion.getAttribute("lang");
        
        this.tipo_accion = tipo_accion;
        this.transaccion = new Transaccion("WebSAE");
        this.respuesta   = new Respuesta();
    }
    // </editor-fold>
    
    public void procesar_peticion() {
        if (this.tipo_accion != null && this.tipo_accion.compareTo( Constante.REGISTRAR ) == 0 && this.estado.compareTo(Estado.APROBADO) == 0) {
            aprobar_resumen();
        } else if (this.tipo_accion != null && this.tipo_accion.compareTo( Constante.REGISTRAR ) == 0 && this.estado.compareTo(Estado.RECHAZADO) == 0) {
            rechazar_resumen();
        } else if (this.estado != null && (this.estado.compareTo(Estado.APROBADO) == 0 || this.estado.compareTo(Estado.RECHAZADO) == 0)) {
            decision_articulo();
        } else if (this.tipo_accion != null && (this.tipo_accion.compareTo( Constante.AGREGAR ) == 0 || this.tipo_accion.compareTo( Constante.ELIMINAR ) == 0)) {
            asignar_evaluador_articulo();
        } else if (this.tipo_accion != null && this.tipo_accion.compareTo( Constante.REGISTRAR+"_EVALUACION" ) == 0) {
            registrar_evaluacion();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Decision del articulo.">
    public void decision_articulo() {
        this.transaccion.iniciar();
        try {
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.estado));
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo()));
            String sql = "UPDATE CE_Articulo SET ar_estado = ? WHERE ar_id_articulo = ?;";
            this.respuesta.setMensaje(this.transaccion.actualizar(sql, parametros));
            
            this.transaccion.commit();
            if (this.estado.compareTo(Estado.APROBADO) == 0)
                this.respuesta.setMensaje("OK:aprobar-articulo");
            else if (this.estado.compareTo(Estado.RECHAZADO) == 0)
                this.respuesta.setMensaje("OK:rechazar-articulo");

            CE_Articulo articulo = CE_Articulo.buscar_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo().toString(), this.lang);
            articulo.ref_evento.ref_convocatoria = CE_Convocatoria.buscar_convocatoria( articulo.ref_evento.getEv_id_evento().toString() );

            AC_Usuario administrador = AC_Usuario.buscar_usuario(this.usuario.getUs_id_usuario().toString());
            
            List<CE_Autor_Articulo> autores = CE_Autor_Articulo.mostrar_autores_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo());
            for (int i=0; i<autores.size(); i++) {
                CE_Autor_Articulo elemento = autores.get(i);
                AC_Usuario autor = elemento.ref_usuario_perfil.ref_usuario;
                try {
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();
                    if (autor.getUs_genero().compareTo("m") == 0)
                        parametro.put("GENERO", "o");
                    else
                        parametro.put("GENERO", "a");

                    parametro.put("NOMBRE", autor.getUs_nombre());
                    parametro.put("APELLIDO", autor.getUs_apellido());
                    parametro.put("TITULO_ARTICULO", articulo.getAr_titulo());
                    parametro.put("NOMBRE_EVENTO", articulo.ref_evento.getEv_nombre(this.lang));
                    parametro.put("EMAIL_EVENTO", articulo.ref_evento.getEv_email());
                        
                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress(autor.getUs_email(), autor.getUs_nombre() + " " + autor.getUs_apellido() );
                    Address[] bcc = new Address[1];
                    bcc[0] = new InternetAddress(administrador.getUs_email(), administrador.getUs_nombre() + " " + administrador.getUs_apellido() );

                    Email email = null;
                    if (this.estado.compareTo(Estado.APROBADO) == 0) {
                        parametro.put("FECHA_MAX_CORRECCION", articulo.ref_evento.ref_convocatoria.getCo_fecha_max_correccion().toString() );
                        /** Creacion del email que se va a enviar. */
                        email = new Email("Aprobación de Artículo", this.host, "articulo_aprobado_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, bcc);
                    } else if (this.estado.compareTo(Estado.RECHAZADO) == 0) {
                        /** Creacion del email que se va a enviar. */
                        email = new Email("Rechazo de Artículo", this.host, "articulo_rechazado_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, bcc);
                    }
                    email.enviar();
                } catch (AddressException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            this.respuesta.setMensaje("ERROR:" + ex.getMessage());
            this.transaccion.rollback();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gestión de la asignación de los evaluadores con respecto a un artículo.">
    private void asignar_evaluador_articulo() {
        Parametros parametros = new Parametros();
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo(), BigDecimal.ZERO));
        parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_evaluador.ref_evento.getEv_id_evento(), BigDecimal.ZERO));
        parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_evaluador.ref_usuario.getUs_id_usuario(), BigDecimal.ZERO));
        parametros.add(new Dato(Tipo.IN, Estado.ESPERA, new String()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        this.respuesta = this.datos.procedimiento("{call ce_asignar_evaluador_articulo(?,?,?,?,?,?)}", parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
        
        if (this.respuesta.getMensaje().compareTo("OK:actualizar-evaluador") == 0) {
            CE_Articulo articulo = CE_Articulo.buscar_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo().toString(), this.lang);
            AC_Usuario evaluador = AC_Usuario.buscar_usuario(this.evaluacion_articulo.ref_evaluador.ref_usuario.getUs_id_usuario().toString());
            try {
                /** Seteo de los parametros para la plantilla de envio de email */
                HashMap<String, String> parametro = new HashMap<String, String>();
                if (evaluador.getUs_genero().compareTo("m") == 0)
                    parametro.put("GENERO", "o");
                else
                    parametro.put("GENERO", "a");
                parametro.put("NOMBRE", evaluador.getUs_nombre());
                parametro.put("APELLIDO", evaluador.getUs_apellido());
                parametro.put("TITULO_ARTICULO", articulo.getAr_titulo());
                parametro.put("NOMBRE_EVENTO", articulo.ref_evento.getEv_nombre(this.lang));
                parametro.put("EMAIL_EVENTO", articulo.ref_evento.getEv_email());
                /** Inicializacion de los emails que se van a enviar. */
                Address[] to = new Address[1];
                to[0] = new InternetAddress(evaluador.getUs_email(), evaluador.getUs_nombre() + " " + evaluador.getUs_apellido() );
                /** Creacion del email que se va a enviar. */
                Email email = new Email("Asignación de Evaluación", this.host, "articulo_asignado_evaluador_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, null);
                email.enviar();
            } catch (AddressException ex) {
                this.respuesta.setMensaje( "ERROR:email" );
                Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                this.respuesta.setMensaje( "ERROR:email" );
                Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gestión de la pre seleccion de los papers, mediante un resumen.">
    private void rechazar_resumen() {
        this.transaccion.iniciar();
        try {
            /** PASO 1: Cambio del seteo de estado a articulo para rechazo. */
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.RECHAZADO));
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo()));
            String sql = "UPDATE CE_Articulo SET ar_estado = ? WHERE ar_id_articulo = ?;";
            this.respuesta.setMensaje(this.transaccion.actualizar(sql, parametros));

            sql = "SELECT mc_id_miembro_comite_evento as id FROM V_Administrador WHERE us_id_usuario = "+this.usuario.getUs_id_usuario()+" AND ref_evento = "+this.id_evento+";";
            BigDecimal id_administrador = Funciones.obtener_id(this.transaccion, sql, "id");
            
            parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.getEt_comentario()));
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo()));
            parametros.add(new Dato(Tipo.IN, id_administrador));
            sql = "INSERT INTO CE_Evaluacion_Articulo (et_comentario, ref_articulo, ref_administrador) VALUES (?,?,?);";
            this.respuesta.setMensaje(this.transaccion.actualizar(sql, parametros));
            
            /** PASO 2: Envio de email de notificacion de rechazo a los autores del paper. */
            CE_Articulo articulo = CE_Articulo.buscar_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo().toString(), this.lang);
            AC_Usuario administrador = AC_Usuario.buscar_usuario(this.usuario.getUs_id_usuario().toString());

            List<CE_Autor_Articulo> autores = CE_Autor_Articulo.mostrar_autores_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo());
            for (int i=0; i<autores.size(); i++) {
                CE_Autor_Articulo elemento = autores.get(i);
                AC_Usuario autor = elemento.ref_usuario_perfil.ref_usuario;
                try {
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();
                    if (autor.getUs_genero().compareTo("m") == 0)
                        parametro.put("GENERO", "o");
                    else
                        parametro.put("GENERO", "a");
                    parametro.put("NOMBRE", autor.getUs_nombre());
                    parametro.put("APELLIDO", autor.getUs_apellido());
                    parametro.put("TITULO_ARTICULO", articulo.getAr_titulo());
                    parametro.put("NOMBRE_EVENTO", articulo.ref_evento.getEv_nombre(this.lang));
                    parametro.put("EMAIL_EVENTO", articulo.ref_evento.getEv_email());
                    parametro.put("MOTIVO_RECHAZO", this.evaluacion_articulo.getEt_comentario());
                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress(autor.getUs_email(), autor.getUs_nombre() + " " + autor.getUs_apellido() );
                    Address[] bcc = new Address[1];
                    bcc[0] = new InternetAddress(administrador.getUs_email(), administrador.getUs_nombre() + " " + administrador.getUs_apellido() );
                    /** Creacion del email que se va a enviar. */
                    Email email = new Email("Rechazo de Resumen", this.host, "articulo_prerechazado_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, bcc);
                    email.enviar();
                } catch (AddressException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.transaccion.commit();
            this.sesion.setAttribute("articulo", null);
            this.respuesta.setMensaje("OK:rechazar-resumen");
        } catch (Exception ex) {
            this.respuesta.setMensaje("ERROR:" + ex.getMessage());
            this.transaccion.rollback();
        }
    }

    private void aprobar_resumen() {
        this.transaccion.iniciar();
        try {
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.PENDIENTE));
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo()));
            String sql = "UPDATE CE_Articulo SET ar_estado = ? WHERE ar_id_articulo = ?;";
            this.respuesta.setMensaje( this.transaccion.actualizar(sql, parametros) );

            sql = "SELECT mc_id_miembro_comite_evento as id FROM V_Administrador WHERE us_id_usuario = "+this.usuario.getUs_id_usuario()+" AND ref_evento = "+this.id_evento+";";
            BigDecimal id_administrador = Funciones.obtener_id(this.transaccion, sql, "id");
            
            parametros = new Parametros();
            //parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo()));
            parametros.add(new Dato(Tipo.IN, this.evaluacion_articulo.ref_articulo.getAr_id_articulo(), new BigDecimal(0)));
            parametros.add(new Dato(Tipo.IN, id_administrador, new BigDecimal(0)));
            sql = "INSERT INTO CE_Evaluacion_Articulo (ref_articulo, ref_administrador) VALUES (?,?);";
            this.respuesta.setMensaje( this.transaccion.actualizar(sql, parametros) );

            Administrar_Tema_Articulo registro_tema_articulo = new Administrar_Tema_Articulo(this.transaccion, this.evaluacion_articulo.ref_articulo.getAr_id_articulo(), Constante.ELIMINAR);
            this.respuesta.setMensaje( registro_tema_articulo.procesar_peticion() );
            
            for (int i = 0; i < this.evaluacion_articulo.ref_articulo.ref_tema_articulo.size(); i++) {
                Administrar_Tema_Articulo tema_articulo = new Administrar_Tema_Articulo(this.transaccion, this.evaluacion_articulo.ref_articulo.ref_tema_articulo.get(i), this.evaluacion_articulo.ref_articulo.getAr_id_articulo(), Constante.AGREGAR);
                this.respuesta.setMensaje( tema_articulo.procesar_peticion() );
            }
            this.transaccion.commit();

            CE_Articulo articulo = CE_Articulo.buscar_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo().toString(), this.lang);
            CE_Convocatoria convocatoria = CE_Convocatoria.buscar_convocatoria(this.id_evento);
            AC_Usuario administrador = AC_Usuario.buscar_usuario(this.usuario.getUs_id_usuario().toString());
            
            List<CE_Autor_Articulo> autores = CE_Autor_Articulo.mostrar_autores_articulo(this.evaluacion_articulo.ref_articulo.getAr_id_articulo());
            for (int i=0; i<autores.size(); i++) {
                CE_Autor_Articulo elemento = autores.get(i);
                AC_Usuario autor = elemento.ref_usuario_perfil.ref_usuario;
                try {
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();
                    if (autor.getUs_genero().compareTo("m") == 0)
                        parametro.put("GENERO", "o");
                    else
                        parametro.put("GENERO", "a");
                    parametro.put("NOMBRE", autor.getUs_nombre());
                    parametro.put("APELLIDO", autor.getUs_apellido());
                    parametro.put("TITULO_ARTICULO", articulo.getAr_titulo());
                    parametro.put("NOMBRE_EVENTO", articulo.ref_evento.getEv_nombre(this.lang));
                    parametro.put("EMAIL_EVENTO", articulo.ref_evento.getEv_email());
                    parametro.put("FECHA_MAX_RESUMEN", convocatoria.getCo_fecha_max_presentacion().toString());
                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress(autor.getUs_email(), autor.getUs_nombre() + " " + autor.getUs_apellido() );
                    Address[] bcc = new Address[1];
                    bcc[0] = new InternetAddress(administrador.getUs_email(), administrador.getUs_nombre() + " " + administrador.getUs_apellido() );
                    /** Creacion del email que se va a enviar. */
                    Email email = new Email("Aprobación de Resumen", this.host, "articulo_preaprobado_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, bcc);
                    email.enviar();
                } catch (AddressException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.sesion.setAttribute("articulo", null);
            this.respuesta.setMensaje("OK:aprobar-resumen");
        } catch (Exception ex) {
            this.respuesta.setMensaje("ERROR:" + ex.getMessage());
            this.transaccion.rollback();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Registro de la evaluacion de un articulo.">
    public void registrar_evaluacion() {
        this.transaccion.iniciar();
        
        try {
            BigDecimal id_evaluador_articulo = null;
            String sql = "SELECT * FROM V_Evaluador WHERE us_id_usuario = "+this.usuario.getUs_id_usuario()+" AND ref_articulo = "+this.id_articulo+";";
            Registro registros = this.transaccion.consultar(sql);
            for (int i = 0; i < registros.size(); i++)
                id_evaluador_articulo = Funciones.getBigDecimal( registros.get(i).get("et_id_evaluacion_articulo") );
                
            /** PASO 1: Registro de las respuestas de un evaluador. */
            int m = this.evaluacion.ref_seccion.size();
            for (int i=0; i<m; i++) {
                CE_Seccion seccion = this.evaluacion.ref_seccion.get(i);
                int n = seccion.ref_pregunta.size();
                for (int j=0; j<n; j++) {
                    CE_Pregunta pregunta = seccion.ref_pregunta.get(j);
                    if (pregunta.ref_tipo_pregunta.getTp_id_tipo_pregunta().compareTo( CE_Tipo_Pregunta.ABIERTA ) == 0) {
                        Administrar_Respuesta registro = new Administrar_Respuesta(this.transaccion, pregunta.ref_respuesta, id_evaluador_articulo, pregunta.getPr_id_pregunta(), CE_Tipo_Pregunta.ABIERTA);
                        registro.procesar_peticion();
                    } else {
                        int p = pregunta.ref_alternativa.size();
                        for (int k=0; k<p; k++) {
                            CE_Alternativa alternativa = pregunta.ref_alternativa.get(k);
                            Administrar_Respuesta registro = new Administrar_Respuesta(this.transaccion, alternativa.ref_respuesta, id_evaluador_articulo, alternativa.getAl_id_alternativa(), pregunta.ref_tipo_pregunta.getTp_id_tipo_pregunta());
                            registro.procesar_peticion();
                        }
                    }
                }
            }
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.EN_EVALUACION));
            parametros.add(new Dato(Tipo.IN, this.id_articulo, BigDecimal.ONE));

            sql = "UPDATE CE_Articulo SET ar_estado = ? WHERE ar_id_articulo = ?;";
            this.respuesta.setMensaje( this.transaccion.actualizar(sql, parametros) );
            
            sql = "SELECT COUNT(*) as total FROM CE_Evaluacion_Articulo ea INNER JOIN CE_Articulo ar ON (ea.ref_articulo = ar.ar_id_articulo AND ar.ar_estado = '"+Estado.PENDIENTE+"') WHERE ea.ref_articulo = " + this.id_articulo + " AND ea.ref_administrador IS NULL;";
            BigDecimal num_evaluadores = Funciones.obtener_id(this.transaccion, sql, "total");
            sql = "SELECT COUNT( DISTINCT re.ref_evaluacion_articulo) as total FROM CE_Respuesta re INNER JOIN CE_Evaluacion_Articulo ea ON (re.ref_evaluacion_articulo = ea.et_id_evaluacion_articulo) WHERE ea.ref_articulo = "+this.id_articulo+" AND ea.ref_administrador IS NULL;";
            BigDecimal num_evaluaciones = Funciones.obtener_id(this.transaccion, sql, "total");
            
            if (num_evaluadores.compareTo( num_evaluaciones ) == 0) {
                parametros = new Parametros();
                parametros.add(new Dato(Tipo.IN, Estado.EVALUADO));
                parametros.add(new Dato(Tipo.IN, this.id_articulo, BigDecimal.ONE));
                
                sql = "UPDATE CE_Articulo SET ar_estado = ? WHERE ar_id_articulo = ?;";
                this.respuesta.setMensaje( this.transaccion.actualizar(sql, parametros) );

                CE_Articulo articulo =  CE_Articulo.buscar_articulo(this.id_articulo.toString(), this.lang);

                try {
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();

                    parametro.put("TITULO_ARTICULO", articulo.getAr_titulo());
                    parametro.put("NOMBRE_EVENTO", articulo.ref_evento.getEv_nombre( this.lang ));

                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress( articulo.ref_evento.getEv_email() );

                    Email email = new Email("Nuevo Artículo Evaluado", this.host, "articulo_evaluado_completo_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( articulo.ref_evento.getEv_email() ), to, null, null);
                    email.enviar();
                } catch (AddressException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, Estado.EVALUADO));
            parametros.add(new Dato(Tipo.IN, id_evaluador_articulo, BigDecimal.ONE));
            sql = "UPDATE CE_Evaluacion_Articulo SET et_estado = ? WHERE et_id_evaluacion_articulo = ?;";
            this.respuesta.setMensaje( this.transaccion.actualizar(sql, parametros) );
            
            this.transaccion.commit();
            this.sesion.setAttribute("evaluacion", null);
            this.respuesta.setMensaje("OK:registrar-resumen");
        } catch (Exception ex) {
            this.respuesta.setMensaje("ERROR:" + ex.getMessage());
            this.transaccion.rollback();
        }
    }
    // </editor-fold>
    
    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("rechazar-resumen")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha realizado el rechazo del resumen de manera correcta.");
                } else if (valores[1].equals("aprobar-resumen")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha realizado la aprobación del resumen de manera correcta.");
                } else if (valores[1].equals("rechazar-articulo")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha realizado el rechazo del articulo de manera correcta.");
                } else if (valores[1].equals("aprobar-articulo")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha realizado la aprobación del artículo de manera correcta.");
                } else if (valores[1].equals("actualizar-evaluador")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha asignado evaluador de manera satisfactoria.");
                } else if (valores[1].equals("eliminar-evaluador")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "Se ha eliminado el evaluador de manera satisfactoria.");
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", "El evaluador ya se encuentra asignado.");
                } else if (valores[1].equals("asignado")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", "No se puede eliminar, ya que el revisor ya evaluado al paper.");
                } else if (valores[1].equals("no-existe-evaluacion")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", "No se puede asignar evaluador debido a que no se ha creado la evaluación.");
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", "");
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Evaluacion_Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}