
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

import websae.mce.dominio.CE_Articulo;
import websae.mce.dominio.CE_Tema;

//~--- JDK imports ------------------------------------------------------------

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Guillermo Pizarro
 */
public class Administrar_CE_Tema {
    private CE_Articulo articulo;
    private String      mensaje;
    private HttpSession sesion;
    private CE_Tema     tema;
    private String      tipo_accion;

    public Administrar_CE_Tema(HttpServletRequest request, String tipo) {
        this.tipo_accion = tipo;
        this.sesion      = request.getSession();
        this.articulo    = (CE_Articulo) this.sesion.getAttribute("articulo");
        this.tema        = new CE_Tema();
        this.tema.setTe_id_tema(Funciones.getBigDecimal(request.getParameter("id_tema")));
    }

    public CE_Tema getTema() {
        return this.tema;
    }

    public void procesar_peticion() {
        if (tipo_accion.compareTo(Constante.AGREGAR) == 0) {
            this.mensaje = this.articulo.agregar_tema(this.tema);
        } else if (tipo_accion.compareTo(Constante.ELIMINAR) == 0) {
            this.mensaje = this.articulo.eliminar_tema(this.tema);
        }

        this.sesion.setAttribute("articulo", this.articulo);
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public static JSONObject obtener_mensaje(String mensaje) {
        String valores[] = new String[2];

        valores = mensaje.split(":", 2);

        JSONObject json = new JSONObject();

        try {
            if (valores[0].equals("OK")) {
                if (valores[1].equals("exitoso")) {
                    json.put("tipo", "OK");
                }
            } else if (valores[0].equals("ERROR")) {
                json.put("tipo", "ERROR");
            }
        } catch (JSONException ex) {
            Logger.getLogger(Administrar_CE_Tema.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
