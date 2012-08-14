/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package websae.informacion;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.eventos.Transaccion;
import mad.objetos.Registro;
import mad.objetos.Table;
import org.json.JSONObject;

/**
 *
 * @author Guillermo Pizarro
 */
public class Funciones {

    public static Integer getInteger(Object valor) {
        return ((valor != null && valor.toString().compareTo("") != 0) ? new Integer( valor.toString() ) : null );
    }

    public static Boolean getBoolean(String valor, String caracter) {
        if (valor != null && valor.trim().compareTo("") != 0) {
            if (valor.compareTo( caracter ) == 0) return true;
        }
        return false;
    }

    public static Boolean getBoolean(Object valor) {
        if (valor != null && valor.toString().trim().compareTo("") != 0) {
            return new Boolean( valor.toString() );
        }
        return false;
    }

    public static Boolean getBoolean(String cadena) {
        return (cadena != null && cadena.trim().compareTo("") != 0 && cadena.compareTo("on") == 0)? true : false;
    }

    public static String getString(String valor) {
        return ((valor != null && valor.trim().compareTo("") != 0) ? valor : null );
    }

    public static BigDecimal getBigDecimal(String numero) {
        return ((numero != null && numero.trim().compareTo("") != 0) ? new BigDecimal( numero ) : null );
    }

    public static BigDecimal getBigDecimal(Object dato) {
        return ((dato != null) ? new BigDecimal( dato.toString() ) : null );
    }

    public static Integer getInteger(String numero) {
        return ((numero != null && numero.trim().compareTo("") != 0) ? new Integer( numero ) : null );
    }

    /**
     * 
     * @param transaccion
     * @param sql
     * @param identificador
     * @return
     */
    public static BigDecimal obtener_id(Transaccion transaccion, String sql, String identificador) {
        Registro id_objeto = transaccion.consultar(sql);
        for (int i = 0; i < id_objeto.size(); i++) {
            return Funciones.getBigDecimal(((Table) id_objeto.get(i)).get(identificador));
        }
        return null;
    }

    public static String fecha_formato_agenda(String fecha_base) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            Date d = sdf1.parse(fecha_base);
            return sdf2.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static JSONObject fecha_actual_toJSON( ) {
        JSONObject json = new JSONObject();
        try {
            json.put("fecha_actual", String.format("%1$tY-%1$tm-%1$td", Calendar.getInstance()));
        } catch (Exception ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
