
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package websae.mce.eventos;

//~--- non-JDK imports --------------------------------------------------------

import org.json.JSONException;
import org.json.JSONObject;

import websae.informacion.Constante;
import websae.informacion.Funciones;
import websae.informacion.Lenguaje;

import websae.mac.dominio.AC_Usuario;

import websae.mce.dominio.CE_Articulo;
import websae.mce.dominio.CE_Autor_Articulo;

//~--- JDK imports ------------------------------------------------------------

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_CE_Autor_Articulo {
    private CE_Articulo       articulo;
    private CE_Autor_Articulo autor_articulo;
    private String            mensaje;
    private HttpSession       sesion;
    private String            tipo_accion;

    /**
     * Constructor valido para agregar de manera automatica el usuario que va a subir el articulo.
     * @param request
     */
    public Administrar_CE_Autor_Articulo(HttpServletRequest request) {
        this.sesion   = request.getSession();
        this.articulo = new CE_Articulo();

        CE_Autor_Articulo autor = new CE_Autor_Articulo();

        /** Seteo la informacion del usuario que sube el articulo cientifico. */
        autor.setAa_autor_principal(Boolean.TRUE);
        autor.ref_usuario_perfil.ref_usuario = (AC_Usuario) this.sesion.getAttribute("usuario");

        /** Lo agrego a la lista de los autores. */
        this.articulo.ref_autor_articulo.add(autor);

        /**
         * Inicializo la varibale articulo para que pueda ser seteado en su debido
         * momento con los datos del paper a subir.
         */
        this.sesion.setAttribute("articulo", this.articulo);
        this.mensaje = "OK:exitoso";
    }

    public Administrar_CE_Autor_Articulo(HttpServletRequest request, String tipo) {
        this.tipo_accion    = tipo;
        this.sesion         = request.getSession();
        this.articulo       = (CE_Articulo) this.sesion.getAttribute("articulo");
        this.autor_articulo = new CE_Autor_Articulo();
        this.autor_articulo.ref_usuario_perfil.ref_usuario.setUs_email(request.getParameter("email"));
        this.autor_articulo.setAa_autor_principal(Funciones.getBoolean(request.getParameter("autor_principal"),
                "true"));
    }

    public void procesar_peticion() {
        if (tipo_accion.compareTo(Constante.AGREGAR) == 0) {
            this.mensaje = this.articulo.agregar_autor(this.autor_articulo);
        } else if (tipo_accion.compareTo(Constante.MODIFICAR) == 0) {
            this.mensaje = this.articulo.modificar_autor(this.autor_articulo);
        } else if (tipo_accion.compareTo(Constante.ELIMINAR) == 0) {
            this.mensaje = this.articulo.eliminar_autor(this.autor_articulo.ref_usuario_perfil.ref_usuario);
        }

        /**
         * Solo si la accion deseada se realizo con exito, podemos setear el
         * nuevo valor en la variable.
         */
        if (this.mensaje.compareTo("OK:exitoso") == 0) {
            this.sesion.setAttribute("articulo", this.articulo);
        }
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public static JSONObject obtener_mensaje(String mensaje, String lang) {
        String valores[] = new String[2];
        valores = mensaje.split(":", 2);
        JSONObject json = new JSONObject();
        try {
            if (valores[0].equals("OK")) {
                json.put("tipo", "OK");
            } else if (valores[0].equals("ERROR")) {
                if (valores[1].equals("repetido")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_AUTOR_ARTICULO_REPETIDO[Lenguaje.parse(lang)]);
                } else if (valores[1].equals("no existe")) {
                    json.put("tipo", "ERROR");
                    json.put("mensaje", Lenguaje.ERROR_AUTOR_ARTICULO_NO_EXISTE[Lenguaje.parse(lang)]);
                } else {
                    json.put("tipo", "ERROR");
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_CE_Autor_Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
