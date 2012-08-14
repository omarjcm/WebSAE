/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.mae.eventos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Constante;
import websae.informacion.Email;
import websae.informacion.Estado;
import websae.informacion.Funciones;
import websae.informacion.Lenguaje;
import websae.mac.dominio.AC_Usuario;
import websae.mae.dominio.AE_Categoria_Evento;
import websae.mae.dominio.AE_Evento;
import websae.mae.dominio.AE_Registro;
import websae.mae.dominio.AE_Subcategoria_Evento;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_Registro {
    
    private AE_Registro registro;

    private String categoria;
    private String subcategoria;
    private String id_evento;
    private String lang;
    private String host;
    private String ruta;
    
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Registro(HttpServletRequest request, String tipo) {
        this.registro = new AE_Registro();

        this.registro.ref_evento = new AE_Evento();
        this.registro.ref_evento.setEv_id_evento( Funciones.getBigDecimal(request.getParameter("id")) );
        
        this.registro.setRe_registro( true );
        this.registro.setRe_pagado( Estado.NO_PAGADO );

        HttpSession session = request.getSession();
        this.registro.ref_usuario = (AC_Usuario) session.getAttribute("usuario");
        this.id_evento = (String) session.getAttribute("id_evento");
        this.lang = (String) session.getAttribute("lang");
        this.tipo_accion = tipo;

        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }
    
    public Administrar_Registro(HttpServletRequest request, String estado, Boolean pagado, String host, String ruta) {
        this.registro = new AE_Registro();
        if (request.getParameter("id_registro") != null) {
            this.registro.setRe_id_registro( Funciones.getBigDecimal( request.getParameter("id_registro") ) );
        }
        this.registro.setRe_estado( estado );
        
        HttpSession session = request.getSession();
        this.registro.ref_usuario = (AC_Usuario) session.getAttribute("usuario");
        this.id_evento = (String) session.getAttribute("id_evento");
        if (request.getParameter("id_evento") != null) {
            this.registro.ref_evento = new AE_Evento();
            this.registro.ref_evento.setEv_id_evento( Funciones.getBigDecimal( request.getParameter("id_evento") ) );
        }
        this.lang = (String) session.getAttribute("lang");
        this.host = host;
        this.ruta = ruta;

        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }
    
    public Administrar_Registro(HttpServletRequest request, String tipo, String host, String ruta) {
        this.registro = new AE_Registro();

        this.registro.ref_evento = new AE_Evento();
        this.registro.ref_evento.setEv_id_evento( Funciones.getBigDecimal(request.getParameter("id")) );
        
        this.registro.ref_categoria_evento = new AE_Categoria_Evento();
        this.categoria  = request.getParameter("categoria");
        if (this.categoria != null && this.categoria.compareTo("-1") == 0)
            this.registro.ref_categoria_evento.setCe_id_categoria_evento( null );
        else if (this.categoria != null)
            this.registro.ref_categoria_evento.setCe_id_categoria_evento( Funciones.getBigDecimal(this.categoria) );
            
        this.registro.ref_subcategoria_evento = new AE_Subcategoria_Evento();
        this.subcategoria  = request.getParameter("subcategoria");
        if (this.subcategoria != null && this.subcategoria.compareTo("-1") == 0)
            this.registro.ref_subcategoria_evento.setSe_id_subcategoria_evento( null );
        else  if (this.subcategoria != null)
            this.registro.ref_subcategoria_evento.setSe_id_subcategoria_evento( Funciones.getBigDecimal( this.subcategoria ) );
        this.registro.setRe_registro( true );
        this.registro.setRe_pagado( Estado.NO_PAGADO );
        
        HttpSession session = request.getSession();
        this.registro.ref_usuario = (AC_Usuario) session.getAttribute("usuario");
        this.id_evento = (String) session.getAttribute("id_evento");
        this.lang = (String) session.getAttribute("lang");
        this.host = host;
        this.ruta = ruta;
        this.tipo_accion = tipo;
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }
    
    public String getMensaje() {
        return this.respuesta.getMensaje();
    }

    public void procesar_registro( ) {
        try {
            Parametros parametros = new Parametros();
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_id_registro(), new BigDecimal("0")));
            if (this.registro.ref_evento != null)
                parametros.add(new Dato(Tipo.IN, this.registro.ref_evento.getEv_id_evento(), new BigDecimal("0")));
            else
                parametros.add(new Dato(Tipo.IN, null, new BigDecimal("0")));
            parametros.add(new Dato(Tipo.IN, this.registro.ref_usuario.getUs_id_usuario(), new BigDecimal("0")));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_estado(), new String()));
            parametros.add(new Dato(Tipo.OUT, new String()));
            
            this.respuesta = datos.procedimiento("{call ae_administrar_registro_usuario(?,?,?,?,?)}", parametros);
            Dato mensaje = this.respuesta.getObjetos().get(0);
            this.respuesta.setMensaje((String) mensaje.getDato());
        } catch (Exception ex) {
        }
    }

    public void procesar_peticion( ) {
        Dato mensaje = null;
        try {
            if (this.categoria.compareTo("-1") == 0 && this.subcategoria.compareTo("-1") == 0) {
                this.registro.setRe_estado( Estado.REGISTRADO );
            } else if (this.categoria.compareTo("-1") != 0 && this.subcategoria.compareTo("-1") == 0) {
                this.registro.setRe_estado( Estado.PENDIENTE );
            } else if (this.categoria.compareTo("-1") != 0 && this.subcategoria.compareTo("-1") != 0) {
                this.registro.setRe_estado( Estado.EN_ESPERA );
            }
            Parametros parametros = new Parametros();
            
            parametros.add(new Dato(Tipo.IN, this.tipo_accion));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_id_registro(), new BigDecimal("0")));
            parametros.add(new Dato(Tipo.IN, String.format("%1$td-%1$tm-%1$ty %1$tH:%1$tM:%1$tS", Calendar.getInstance()) ));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_valor_abonado(), new Float(0.0)));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_valor_total(), new Float(0.0)));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_aprobar_descuento(), Boolean.TRUE));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_asistencia(), Boolean.TRUE));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_pagado(), Boolean.TRUE));
            parametros.add(new Dato(Tipo.IN, this.registro.getRe_estado(), Boolean.TRUE));
            parametros.add(new Dato(Tipo.IN, this.registro.ref_categoria_evento.getCe_id_categoria_evento(), BigDecimal.ZERO));
            parametros.add(new Dato(Tipo.IN, this.registro.ref_subcategoria_evento.getSe_id_subcategoria_evento(), BigDecimal.ZERO));
            parametros.add(new Dato(Tipo.IN, this.registro.ref_evento.getEv_id_evento(), BigDecimal.ZERO));
            parametros.add(new Dato(Tipo.IN, this.registro.ref_usuario.getUs_id_usuario(), BigDecimal.ZERO));
            parametros.add(new Dato(Tipo.OUT, new String()));
            
            this.respuesta = datos.procedimiento("{call ae_administrar_registro(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", parametros);
            mensaje = this.respuesta.getObjetos().get(0);
            this.respuesta.setMensaje((String) mensaje.getDato());

            if (tipo_accion.compareTo(Constante.REGISTRAR) == 0) {
                try {
                    this.registro.ref_evento = AE_Evento.buscar_evento( this.id_evento );
                    /** Seteo de los parametros para la plantilla de envio de email */
                    HashMap<String, String> parametro = new HashMap<String, String>();

                    parametro.put("NOMBRE", this.registro.ref_usuario.getUs_nombre());
                    parametro.put("APELLIDO", this.registro.ref_usuario.getUs_apellido());
                    parametro.put("NOMBRE_EVENTO", this.registro.ref_evento.getEv_nombre( this.lang ));
                    
                    /** Inicializacion de los emails que se van a enviar. */
                    Address[] to = new Address[1];
                    to[0] = new InternetAddress( this.registro.ref_evento.getEv_email() );

                    Email email = new Email("Registro a Evento / Subevento", this.host, "registro_evento_"+this.lang+".vm", this.ruta, parametro, new InternetAddress( this.registro.ref_evento.getEv_email() ), to, null, null);
                    email.enviar();
                } catch (AddressException ex) {
                    this.respuesta.setMensaje( "ERROR:email" );
                    Logger.getLogger(Administrar_Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
        }
    }
    
    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_REGISTRO_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("actualizar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", "El estado del registro ha sido actualizado exitosamente.");
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registrar")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_PROBLEMAS[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_REGISTRO_REPETIDO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
