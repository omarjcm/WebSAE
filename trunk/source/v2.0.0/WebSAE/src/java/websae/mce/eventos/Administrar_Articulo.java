
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

//~--- non-JDK imports --------------------------------------------------------

import java.io.UnsupportedEncodingException;
import mad.eventos.Transaccion;

import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Tipo;

import org.json.JSONException;
import org.json.JSONObject;

import websae.informacion.Constante;
import websae.informacion.Estado;
import websae.informacion.Funciones;
import websae.informacion.Lenguaje;

import websae.mce.dominio.CE_Archivo;
import websae.mce.dominio.CE_Articulo;
import websae.mce.dominio.CE_Convocatoria;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import websae.informacion.Email;
import websae.mac.dominio.AC_Usuario;
import websae.mae.dominio.AE_Evento;
import websae.mce.dominio.CE_Autor_Articulo;
import websae.msu.eventos.Administrar_Usuario;

/**
 *
 * @author Guillermo Pizarro
 * @email omarjcm@gmail.com
 */
public class Administrar_Articulo {
    private CE_Archivo  archivo;
    private CE_Articulo articulo;
    private BigDecimal  id_evento;
    private String      respuesta;
    private HttpSession sesion;
    private String      tipo_accion;
    private Transaccion transaccion;
    private String lang;
    private String host;
    private String ruta;

    public Administrar_Articulo(HttpServletRequest request, String id_evento, String tipo, String host, String ruta) {
        this.tipo_accion = tipo;
        this.host = host;
        this.ruta = ruta;
        
        this.sesion      = request.getSession();
        this.articulo    = (CE_Articulo) this.sesion.getAttribute("articulo");
        this.lang = (String) this.sesion.getAttribute("lang");
        this.articulo.setAr_titulo(request.getParameter("txt_nombre_articulo"));
        this.articulo.setAr_resumen(request.getParameter("txt_resumen"));
        this.id_evento = Funciones.getBigDecimal(id_evento);
        this.archivo   = new CE_Archivo();
        this.archivo.setAr_nombre(request.getParameter("txt_articulo2"));
    }

    public void procesar_peticion() {
        if (this.tipo_accion.compareTo(Constante.REGISTRAR) == 0) {
            subir_articulo();
        } else if (this.tipo_accion.compareTo(Constante.MODIFICAR) == 0) {
            modificar_articulo();
        }
    }

    public void subir_articulo() {
        this.transaccion = new Transaccion("WebSAE");
        this.transaccion.iniciar();
        try {
            // <editor-fold defaultstate="collapsed" desc="PASO 1: Verificación de la unicidad del título del artículo.">
            String sql = "SELECT ar_id_articulo AS id_articulo FROM CE_Articulo WHERE ar_titulo = '"
                         + this.articulo.getAr_titulo() + "';";

            this.articulo.setAr_id_articulo(Funciones.obtener_id(this.transaccion, sql, "id_articulo"));

            if (this.articulo.getAr_id_articulo() != null) {
                this.transaccion.commit();
                this.respuesta = "ERROR:repetido";

                return;
            }

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 2: Verificación de si tiene resumen la convocatoria.">
            Boolean convocatoria_tiene_resumen = CE_Convocatoria.tiene_resumen(this.id_evento.toString());

            if (convocatoria_tiene_resumen) {
                this.articulo.setAr_estado(Estado.EN_CONSULTA);
            } else {
                this.articulo.setAr_estado(Estado.PENDIENTE);
            }

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 3: Registro del articulo.">
            Parametros parametros = new Parametros();

            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_titulo()));
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_resumen()));
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_estado()));
            parametros.add(new Dato(Tipo.IN, this.id_evento));
            sql            = "INSERT INTO CE_Articulo (ar_titulo, ar_resumen, ar_estado, ref_evento) VALUES (?,?,?,?)";
            this.respuesta = this.transaccion.actualizar(sql, parametros);

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 4: Buscar el id del registro del articulo.">
            sql = "SELECT MAX(ar_id_articulo) AS id_articulo FROM CE_Articulo;";
            this.articulo.setAr_id_articulo(Funciones.obtener_id(this.transaccion, sql, "id_articulo"));
            this.archivo.ref_articulo.setAr_id_articulo(this.articulo.getAr_id_articulo());

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 5: Registro de los temas que un articulo tiene asociados.">
            for (int i = 0; i < this.articulo.ref_tema_articulo.size(); i++) {
                Administrar_Tema_Articulo tema_articulo = new Administrar_Tema_Articulo(this.transaccion,
                                                              this.articulo.ref_tema_articulo.get(i),
                                                              this.articulo.getAr_id_articulo(), Constante.AGREGAR);

                this.respuesta = tema_articulo.procesar_peticion();
            }

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 6: Registro de los autores que un articulo tiene asociados.">
            for (int i = 0; i < this.articulo.ref_autor_articulo.size(); i++) {
                Administrar_Autor_Articulo tema_articulo = new Administrar_Autor_Articulo(this.transaccion,
                                                               this.articulo.ref_autor_articulo.get(i),
                                                               this.articulo.getAr_id_articulo(), Constante.AGREGAR);
                this.respuesta = tema_articulo.procesar_peticion();
            }

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="PASO 7: Registro del archivo subido como tal.">
            Administrar_Archivo registro = new Administrar_Archivo(this.transaccion, this.archivo, Constante.AGREGAR);
            this.respuesta = registro.procesar_peticion();
            // </editor-fold>
            
            /** PASO 6: Envio de email por la participacion en el proceso de evaluacion. */
            AE_Evento evento = AE_Evento.buscar_evento(this.id_evento.toString(), this.lang);
            for (int i = 0; i < this.articulo.ref_autor_articulo.size(); i++) {
                CE_Autor_Articulo autor = this.articulo.ref_autor_articulo.get(i);
                AC_Usuario usuario_autor = autor.ref_usuario_perfil.ref_usuario;
                try {
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();
                    if (usuario_autor.getUs_genero().compareTo("m") == 0)
                        parametro.put("GENERO", "o");
                    else
                        parametro.put("GENERO", "a");
                    parametro.put("NOMBRE", usuario_autor.getUs_nombre());
                    parametro.put("APELLIDO", usuario_autor.getUs_apellido());
                    parametro.put("TITULO_ARTICULO", this.articulo.getAr_titulo());
                    parametro.put("NOMBRE_EVENTO", evento.getEv_nombre(this.lang));
                    parametro.put("EMAIL_EVENTO", evento.getEv_email());
                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress(usuario_autor.getUs_email(), usuario_autor.getUs_nombre() + " " + usuario_autor.getUs_apellido() );
                    /** Creacion del email que se va a enviar. */
                    Email email = new Email("Artículo Recibido", this.host, "articulo_recibido_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( evento.getEv_email() ), to, null, null);
                    email.enviar();
                } catch (UnsupportedEncodingException ex) {
                    this.respuesta = "ERROR:email";
                    Logger.getLogger(Administrar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.transaccion.commit();
            this.sesion.setAttribute("articulo", null);
            this.respuesta = "OK:registrar";
        } catch (Exception ex) {
            this.respuesta = "ERROR:" + ex.getMessage();
            this.transaccion.rollback();
        }
    }

    public void modificar_articulo() {
        this.transaccion = new Transaccion("WebSAE");
        this.transaccion.iniciar();
        try {
            /** PASO 1: Registro del articulo. */
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_titulo()));
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_resumen()));
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_estado()));
            parametros.add(new Dato(Tipo.IN, this.articulo.getAr_id_articulo()));

            String sql = "UPDATE CE_Articulo SET ar_titulo = ?, ar_resumen = ?, ar_estado = ? WHERE ar_id_articulo = ?;";
            this.respuesta = this.transaccion.actualizar(sql, parametros);

            /** PASO 2: Eliminacion de los temas asignados al articulo. */
            Administrar_Tema_Articulo registro_tema_articulo = new Administrar_Tema_Articulo(this.transaccion,
                                                                   this.articulo.getAr_id_articulo(),
                                                                   Constante.ELIMINAR);
            this.respuesta = registro_tema_articulo.procesar_peticion();

            /** PASO 3: Registro de los nuevos temas a un articulo que tiene asociados. */
            for (int i = 0; i < this.articulo.ref_tema_articulo.size(); i++) {
                Administrar_Tema_Articulo tema_articulo = new Administrar_Tema_Articulo(this.transaccion,
                                                              this.articulo.ref_tema_articulo.get(i),
                                                              this.articulo.getAr_id_articulo(), Constante.AGREGAR);
                this.respuesta = tema_articulo.procesar_peticion();
            }

            /** PASO 4: Eliminacion de los autores asignados al articulo. */
            Administrar_Autor_Articulo registro_autor_articulo = new Administrar_Autor_Articulo(this.transaccion,
                                                                     this.articulo.getAr_id_articulo(),
                                                                     Constante.ELIMINAR);
            this.respuesta = registro_autor_articulo.procesar_peticion();

            /** PASO 5: Registro de los autores que un articulo tiene asociados. */
            for (int i = 0; i < this.articulo.ref_autor_articulo.size(); i++) {
                Administrar_Autor_Articulo tema_articulo = new Administrar_Autor_Articulo(this.transaccion,
                                                               this.articulo.ref_autor_articulo.get(i),
                                                               this.articulo.getAr_id_articulo(), Constante.AGREGAR);
                this.respuesta = tema_articulo.procesar_peticion();
            }
            this.archivo.ref_articulo.setAr_id_articulo(this.articulo.getAr_id_articulo());
            /** PASO 6: Registro del nuevo archivo subido de un articulo existente. */
            Administrar_Archivo registro = new Administrar_Archivo(this.transaccion, this.archivo, Constante.AGREGAR);
            
            this.respuesta = registro.procesar_peticion();
            this.transaccion.commit();
            this.sesion.setAttribute("articulo", null);
            this.respuesta = "OK:modificar";
        } catch (Exception ex) {
            this.respuesta = "ERROR:" + ex.getMessage();
            this.transaccion.rollback();
        }
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ARTICULO_REGISTRAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ARTICULO_MODIFICAR[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("eliminar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_ARTICULO_ELIMINAR[Lenguaje.parse(lang)]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_ARTICULO_REPETIDO[Lenguaje.parse(lang)]);
                } else {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[Lenguaje.parse(lang)]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
