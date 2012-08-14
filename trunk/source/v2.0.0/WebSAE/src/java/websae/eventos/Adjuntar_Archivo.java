/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.eventos;

import java.io.File;
import java.util.Calendar;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import websae.mac.dominio.AC_Usuario;

/**
 *
 * @author Guillermo Pizarro
 */
public class Adjuntar_Archivo {

    private String mensaje;
    private String tipo;
    private String nombre_archivo;
    
    public Adjuntar_Archivo() {
        this.mensaje = "";
        this.tipo = "";
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }


    public void escribir_archivo(HttpServletRequest req, ServletContext ruta) {
        /** Create a factory for disk-based file items */
        HttpSession sesion = req.getSession();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        /** Set factory constraints */
        factory.setSizeThreshold(1024 * 1024 * 1024);
        factory.setRepository(new File(ruta.getRealPath( File.separator ) + req.getParameter("tipo") + File.separator + req.getParameter("directorio") + File.separator ));
        /** Create a new file upload handler */
        ServletFileUpload upload = new ServletFileUpload(factory);
        /** Set overall request size constraint */
        upload.setSizeMax(1024 * 1024 * 1024);
        
        try {
            /** Parse the request */
            List items = upload.parseRequest(req);
            if (items == null) {
                this.mensaje = "Por favor escoja un archivo.";
                this.tipo = "ERROR";
                throw new FileUploadException();
            }
            
            Iterator i = items.iterator();
            FileItem actual = null;
            
            while (i.hasNext()) {
                actual = (FileItem) i.next();
                /** construimos un objeto file para recuperar el trayecto completo */
                File fichero = new File( actual.getName() );

                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DATE);
                int month = cal.get(Calendar.MONTH) + 1;
                int year = cal.get(Calendar.YEAR);
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);

                String nombre, extension;
                int dotPos = actual.getName().lastIndexOf(".");
                nombre = actual.getName().substring(0,dotPos);
                extension = actual.getName().substring(dotPos);
                
                /** Con esto nos aseguramos saber quien es el que sube el archivo. */
                AC_Usuario usuario = (AC_Usuario) sesion.getAttribute("usuario");
                if (usuario != null) {
                    String nombre_nuevo = nombre + "_" + usuario.getUs_id_usuario() + "_(" + year + "-" + month + "-" + day + "_"+hour+"."+minute+")" + extension;
                    fichero = new File(ruta.getRealPath( File.separator ) + req.getParameter("tipo") + File.separator + req.getParameter("directorio") + File.separator + nombre_nuevo);
                    System.out.println(fichero.getAbsolutePath());

                    /** Escritura del archivo en el servidor */
                    try {
                        actual.write(fichero);
                        this.nombre_archivo = nombre_nuevo;
                        this.mensaje = "Archivo subido con éxito!";
                        this.tipo = "OK";
                    } catch (Exception ex) {
                        this.mensaje = "Error al subir archivo. Intente más tarde.";
                        this.tipo = "ERROR";
                        Logger.getLogger(Adjuntar_Archivo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (FileUploadException ex) {
            this.mensaje = "Error al subir archivo. Intente más tarde.";
            this.tipo = "ERROR";
            Logger.getLogger(Adjuntar_Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONObject toJSON(String tipo, String mensaje, String nombre_archivo) {
        JSONObject json = new JSONObject();
        try {
            json.put("tipo",  tipo );
            json.put("mensaje",  mensaje );
            json.put("nombre_archivo",  nombre_archivo );
        } catch (Exception ex) {
            Logger.getLogger(Adjuntar_Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }    
}