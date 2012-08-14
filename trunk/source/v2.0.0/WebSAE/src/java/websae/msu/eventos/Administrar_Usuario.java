/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.msu.eventos;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import mad.eventos.Datos;
import mad.objetos.Dato;
import mad.objetos.Parametros;
import mad.objetos.Registro;
import mad.objetos.Respuesta;
import mad.objetos.Tipo;
import org.json.JSONException;
import org.json.JSONObject;
import websae.informacion.Email;
import websae.informacion.Estado;
import websae.informacion.Lenguaje;
import websae.informacion.Constante;
import websae.mac.dominio.AC_Usuario;
import websae.msu.dominio.SU_Empresa_Usuario;

/**
 * 
 * @author Guillermo Pizarro
 */
public class Administrar_Usuario {

    private AC_Usuario usuario;
    private Datos datos;
    private Respuesta respuesta;
    private String tipo_accion;
    
    public Administrar_Usuario(HttpServletRequest request, String tipo_accion) {
        this.usuario = new AC_Usuario();
        
        this.usuario.setUs_email((String) request.getParameter("txt_correo"));
        this.usuario.setUs_clave((String) request.getParameter("txt_clave"));
        this.usuario.setUs_nombre((String) request.getParameter("txt_nombres"));
        this.usuario.setUs_apellido((String) request.getParameter("txt_apellidos"));
        this.usuario.setUs_genero((String) request.getParameter("genero"));
        this.usuario.setUs_direccion((String) request.getParameter("txt_direccion"));
        this.usuario.setUs_telefono((String) request.getParameter("txt_telefono"));
        this.usuario.setUs_celular((String) request.getParameter("txt_celular"));
        this.usuario.setUs_fecha_nacimiento( (String) request.getParameter("txt_nacimiento") );
        this.usuario.ref_ciudad.setCi_id_ciudad(new BigDecimal((String) request.getParameter("cmb_ciudad")));
        this.usuario.ref_titulo.setTi_id_titulo(new BigDecimal((String) request.getParameter("cmb_titulo")));
        
        this.usuario.ref_empresa_usuario = new SU_Empresa_Usuario();
        this.usuario.ref_empresa_usuario.ref_cargo.setCa_id_cargo(new BigDecimal((String) request.getParameter("cmb_cargo")));
        this.usuario.ref_empresa_usuario.ref_empresa.setEm_id_empresa(new BigDecimal((String) request.getParameter("cmb_empresa")));
        this.usuario.ref_empresa_usuario.setEu_telefono((String) request.getParameter("txt_telefono_oficina"));
        
        this.tipo_accion = tipo_accion;
        if (this.tipo_accion.compareTo( Constante.REGISTRAR ) == 0)
            this.usuario.setUs_estado( Estado.VIGENTE );
        else if (this.tipo_accion.compareTo( Constante.MODIFICAR ) == 0)
            this.usuario.setUs_estado( Estado.VIGENTE );
        
        this.datos = new Datos("WebSAE");
        this.respuesta = new Respuesta();
    }

    public String getRespuesta() {
        return this.respuesta.getMensaje();
    }

    public AC_Usuario getUsuario() {
        return usuario;
    }

    public void procesar_registro(String host, String email_admin, String email_remitente, String nombre_admin, String ruta, String language) {
        /** PASO 1: Registro del Usuario en la Base de Datos */
        this.procesar_peticion();

        /** PASO 2: Envio del email, se verifica que el proceso de registro en la Base se ha cumplido */
        if (this.respuesta.getMensaje().compareTo("OK:registro") == 0) {
            try {
                /** Seteo de los parametros para la plantilla de envio de email */
                HashMap<String, String> parametros = new HashMap<String, String>();
                if (this.usuario.getUs_genero().compareTo("m") == 0)
                    parametros.put("GENERO", "o");
                else
                    parametros.put("GENERO", "a");
                parametros.put("NOMBRE", this.usuario.getUs_nombre());
                parametros.put("APELLIDO", this.usuario.getUs_apellido());
                parametros.put("EMAIL_ADMIN", email_admin);
                parametros.put("CORREO", this.usuario.getUs_email());
                parametros.put("CLAVE", this.usuario.getUs_clave());
                parametros.put("REMITENTE", email_remitente);
                /** Inicializacion de los emails que se van a enviar. */
                Address[] to = new Address[1];
                to[0] = new InternetAddress(this.usuario.getUs_email(), this.usuario.getUs_nombre() + " " + this.usuario.getUs_apellido() );
                Address[] bcc = new Address[1];
                bcc[0] = new InternetAddress(email_admin, nombre_admin);
                /** Creacion del email que se va a enviar. */
                Email email = new Email("Bienvenido a Web S.A.E.", host, "suscripcion_"+language+".vm",
                                        ruta, parametros, new InternetAddress(email_admin, nombre_admin),
                                        to, null, bcc);
                email.enviar();
            } catch (UnsupportedEncodingException ex) {
                this.respuesta.setMensaje("ERROR:email");
                Logger.getLogger(Administrar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void procesar_actualizacion_registro(AC_Usuario admin) {
        /** PASO 1: Modificacion del Usuario en la Base de Datos */
        this.procesar_peticion();
        
        /** PASO 2: Actualizar el usuario cargado en memoria de la Aplicacion */
        String sql = "SELECT * FROM V_Usuario WHERE us_email = '"+admin.getUs_email()+"' AND us_clave = '"+admin.getUs_email()+"';";
        Registro usuario_nuevo = this.datos.consulta( sql );
        for (int i = 0; i < usuario_nuevo.size(); i++) {
            this.usuario = new AC_Usuario();
            this.usuario.cr_AC_Usuario(usuario_nuevo.get(i));
        }
    }
    
    private void procesar_peticion() {
        Parametros parametros = new Parametros();
        
        parametros.add(new Dato(Tipo.IN, this.tipo_accion));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_nombre(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_apellido(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_email(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_clave(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_genero(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_fecha_nacimiento(), "2009-04-11"));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_direccion(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_telefono(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_celular(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_actividad(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_hoja_vida(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_foto(), new String()));
        parametros.add(new Dato(Tipo.IN, this.usuario.getUs_estado()));
        parametros.add(new Dato(Tipo.IN, this.usuario.ref_titulo.getTi_id_titulo(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.usuario.ref_ciudad.getCi_id_ciudad(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.usuario.ref_empresa_usuario.ref_empresa.getEm_id_empresa(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.usuario.ref_empresa_usuario.ref_cargo.getCa_id_cargo(), new BigDecimal("0")));
        parametros.add(new Dato(Tipo.IN, this.usuario.ref_empresa_usuario.getEu_telefono(), new String()));
        parametros.add(new Dato(Tipo.OUT, new String()));
        
        String sql = "{call su_administrar_usuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        this.respuesta = datos.procedimiento(sql, parametros);
        Dato mensaje = this.respuesta.getObjetos().get(0);
        this.respuesta.setMensaje((String) mensaje.getDato());
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CUENTA_REGISTRAR[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("modificar")) {
                    json.put("tipo", "OK");
                    json.put("mensaje", Lenguaje.OK_CUENTA_MODIFICAR[ Lenguaje.parse(lang) ]);
                }
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("registro")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_TECNICO_REGISTRO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("email repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMAIL_REPETIDO[ Lenguaje.parse(lang) ]);
                } else if (valores[1].equals("email")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_EMAIL_ENVIO[ Lenguaje.parse(lang) ]);
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
