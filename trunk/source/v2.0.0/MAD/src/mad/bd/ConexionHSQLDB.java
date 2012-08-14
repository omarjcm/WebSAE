/* =============================================================================
 * Archivo:             ConexionHSQLDB.java
 * Licencia:            
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * ========================================================================== */
 
package mad.bd;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import mad.mad.Conexion;
import mad.mad.Configuracion;
import mad.mad.Perfil;
import mad.objetos.*;

/** Clase base que permite las disversas conexiones a la Base de Datos HSQLDB que
 * utiliza AD para la configuración interna.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class ConexionHSQLDB extends AD {

    private Conexion conexion;
    private Perfil perfil;
    private Configuracion configuracion;

    public ConexionHSQLDB() {
        this.conexion = new Conexion();
        this.perfil = new Perfil();
        this.configuracion = new Configuracion();

        this.setDriver("org.hsqldb.jdbcDriver");
        this.setUsuario("sa");
        this.setClave("123da123");
        this.setBase_datos("mad");
        this.setHost("localhost");
        this.setUrl("jdbc:hsqldb:hsql://" + this.getHost() + "/" + this.getBase_datos());
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
    // </editor-fold>
    
    public Configuracion obtener_configuracion() {
        String query = "SELECT * FROM CONFIGURACION;";
        Registro objetos = this.ejecutar_consulta(query);
        Table registro = (Table) objetos.get(0);
        
        return ( new Configuracion( Integer.parseInt( registro.get("PUERTO").toString() ), registro.get("HOST").toString()) );
    }

    public String obtenerConfiguracion() {
        String query = "SELECT * FROM CONFIGURACION;";
        Registro objetos = this.ejecutar_consulta(query);
        Table registro = (Table) objetos.get(0);
        
        String xml = "<configuracion>";
        if (registro != null) {
            xml += "<host>" + registro.get("HOST").toString() + "</host>";
            xml += "<puerto>" + registro.get("PUERTO").toString() + "</puerto>";
        }
        xml += "</configuracion>";
        return xml;
    }

    public String actualizar_usuario(String usuario, String clave) {
        String text = "";
        this.conectar();
        String query = "UPDATE USUARIO SET USUARIO='" + usuario + "', CLAVE='" + clave + "' WHERE ID_USUARIO=0; commit; ";
        try {
            Statement stmt = this.getConnection().createStatement();
            stmt.executeQuery(query);
            stmt.close();
            this.getConnection().close();
            text = "Actualizacion del usuario exitosa.";
        } catch (SQLException e) {
            text = "Hubo problemas en la actualizacion del usuario.";
        }
        return text;
    }
    
    public String actualizar_configuracion(String host, String puerto) {
        String text = "";
        this.conectar();
        String query = "UPDATE CONFIGURACION SET HOST='" + host + "', PUERTO=" + puerto + " WHERE ID_CONFIGURACION=1; commit; ";
        try {
            Statement stmt = this.getConnection().createStatement();
            stmt.executeQuery(query);
            stmt.close();
            this.getConnection().close();
            text = "Actualización de la configuracion exitosa.";
        } catch (SQLException e) {
            text = "Hubo problemas en la actualizacion de la configuracion.";
        }
        return text;
    }

    public String ingresar_conexion(String modulo, String base_datos, String usuario, String clave) {
        String query = "SELECT * FROM CONEXION WHERE MODULO='"+modulo+"' AND BASE_DATOS='"+base_datos+"' AND USUARIO='"+usuario+"' AND CLAVE='"+clave+"';";
        Registro objetos = this.ejecutar_consulta(query);
        for (int i = 0; i < objetos.size(); i++) {
            Table registro = (Table) objetos.get(i);
            if (registro != null) {
                return "Esta conexión ya ha sido ingresada.";
            }
        }
        
        String text = "";
        this.conectar();
        query = "INSERT INTO CONEXION (MODULO, BASE_DATOS, USUARIO, CLAVE)" +
                       "VALUES ('" + modulo + "', '" + base_datos + "', '" + usuario + "', '" + clave + "'); " +
                       "CALL identity(); commit;";
        try {
            Statement stmt = this.getConnection().createStatement();
            stmt.executeQuery(query);
            stmt.close();
            this.getConnection().close();
            text = "Ingreso de la conexion exitosa.";
        } catch (SQLException e) {
            text = "Hubo problemas en el ingreso de la conexion.";
        }
        return text;
    }

    public String actualizar_conexion(String id_conexion, String modulo, String base_datos, String usuario, String clave) {
        String text = "";
        this.conectar();
        String query = "UPDATE CONEXION " +
                " SET MODULO='" + modulo + "', BASE_DATOS='" + base_datos +"'," +
                " USUARIO='" + usuario + "', CLAVE='" + clave + "' " +
                " WHERE id_conexion=" + id_conexion + "; commit; ";
        try {
            Statement stmt = this.getConnection().createStatement();
            stmt.executeQuery(query);
            stmt.close();
            this.getConnection().close();
            text = "Actualizacion de la conexion exitosa.";
        } catch (SQLException e) {
            text = "Hubo problemas en la actualizacion de la conexion.";
        }
        return text;
    }
    
    public String eliminar_conexion(String id_conexion) {
        String text = "";
        String query = "DELETE FROM CONEXION WHERE ID_CONEXION="+id_conexion+"; commit;";
        this.conectar();
        try {
            Statement stmt = this.getConnection().createStatement();
            stmt.executeQuery(query);
            stmt.close();
            this.getConnection().close();
            text = "Eliminación de la conexión exitosa.";
        } catch (SQLException e) {
            text = "Hubo problemas en la eliminación de la conexión.";
        }
        return text;
    }
    
    public LinkedList<Conexion> obtener_conexiones() {
        String query = "SELECT * FROM CONEXION;";
        Registro objetos = this.ejecutar_consulta(query);
        LinkedList<Conexion> lista = new LinkedList<Conexion>();
        
        for (int i = 0; i < objetos.size(); i++) {
            Table registro = (Table) objetos.get(i);
            if (registro != null) {
                Conexion conexion_ = new Conexion();
                conexion_.setId_conexion( Integer.parseInt(registro.get("ID_CONEXION").toString()) );
                conexion_.setModulo(registro.get("MODULO").toString());
                conexion_.setBase_datos(registro.get("BASE_DATOS").toString());
                conexion_.setUsuario(registro.get("USUARIO").toString());
                conexion_.setClave(registro.get("CLAVE").toString());
                lista.add(conexion_);
            }
        }
        return lista;
    }
    
    public String obtener_conexion() {
        String query = "SELECT * FROM CONEXION;";
        Registro objetos = this.ejecutar_consulta(query);
        String xml = "<conexiones>";
        for (int i = 0; i < objetos.size(); i++) {
            Table registro = (Table) objetos.get(i);
            if (registro != null) {
                xml += "<conexion id='" + registro.get("ID_CONEXION").toString() + "'>";
                xml += "<modulo>" + registro.get("MODULO").toString() + "</modulo>";
                xml += "<base_datos>" + registro.get("BASE_DATOS").toString() + "</base_datos>";
                xml += "<usuario>" + registro.get("USUARIO").toString() + "</usuario>";
                xml += "<clave>" + registro.get("CLAVE").toString() + "</clave>";
                xml += "</conexion>";
            }
        }
        xml += "</conexiones>";
        return xml;
    }

    public String buscar_conexion(String id_conexion) {
        String query = "SELECT * FROM CONEXION WHERE id_conexion='" + id_conexion + "';";
        Registro objetos = this.ejecutar_consulta(query);
        String xml = "";
        Table registro = (Table) objetos.get(0);
        if (registro != null) {
            xml += "<conexion id='" + registro.get("ID_CONEXION").toString() + "'>";
            xml += "<modulo>" + registro.get("MODULO").toString() + "</modulo>";
            xml += "<base_datos>" + registro.get("BASE_DATOS").toString() + "</base_datos>";
            xml += "<usuario>" + registro.get("USUARIO").toString() + "</usuario>";
            xml += "<clave>" + registro.get("CLAVE").toString() + "</clave>";
            xml += "</conexion>";
        }
        return xml;
    }

    private void configuracion_server() {
        String query = "select * from CONFIGURACION";
        Registro objetos = new Registro();
        objetos = this.ejecutar_consulta(query);
        Table registro = (Table) objetos.get(0);
        if (registro != null) {
            this.configuracion.setHost((String) registro.get("HOST"));
            Object puerto_ = registro.get("PUERTO");
            this.configuracion.setPuerto(Integer.parseInt(puerto_.toString()));
        }
    }

    public void configurar_conexion(String modulo) {
        String query = "select * from CONEXION where MODULO like '" + modulo + "'";
        Registro objetos = new Registro();
        objetos = this.ejecutar_consulta(query);
        Table registro = (Table) objetos.get(0);
        if (registro != null) {
            Object id_conexion_ = registro.get("ID_CONEXION");
            this.conexion.setId_conexion(Integer.parseInt(id_conexion_.toString()));
            this.conexion.setModulo((String) registro.get("MODULO"));
            this.conexion.setBase_datos((String) registro.get("BASE_DATOS"));
            this.conexion.setUsuario((String) registro.get("USUARIO"));
            this.conexion.setClave((String) registro.get("CLAVE"));
        }
        configuracion_server();
    }

    public boolean validar_usuario(String usuario, String clave) {
        String sql = "SELECT * FROM USUARIO WHERE USUARIO='" + usuario + "' AND CLAVE='" + clave + "';";
        Registro objetos = this.ejecutar_consulta(sql);
        try {
            Table registro = (Table) objetos.get(0);
            return true;
        } catch (Exception e) { }
        
        return false;
    }
}
