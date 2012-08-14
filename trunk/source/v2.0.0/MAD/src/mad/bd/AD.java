/* =============================================================================
 * Archivo:             AD.java
 * Licencia:
 * Fecha de Creación:   10/11/2008
 * @author              Guillermo Pizarro
 * =============================================================================
 * HISTORIAL DE VERSIONAMIENTO:
 * Versión	Responsable         Fecha       Descripción
 * 1.0.0	Guillermo Pizarro   10/11/2008  Creación de la Clase
 * 1.1.0	Guillermo Pizarro    7/03/2009  Se le cambió el modo en que se retorna
 *                                          los parámetros, ya no se obtienen los
 *                                          resultados de una consulta a través de
 *                                          un índice, si no a través del nombre
 *                                          de la columna.
 * 1.2.0	Guillermo Pizarro   21/03/2009  Se le habilitó para que se pueda recibir parámetros nulos en un procedure.
 * ========================================================================== */

package mad.bd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet; 
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.objetos.*;

/** Clase base que permite las disversas conexiones a la Base de Datos.
 * @author Guillermo Pizarro
 * @version 1.0.0
 */
public class AD {
    
    private String driver;
    private String url;
    private String usuario;
    private String clave;
    private String host;
    private String base_datos;
    private int puerto;
    private String mensaje_error;
    
    private PreparedStatement prepared_statement;
    private CallableStatement callable_statement;
    private Statement statement;
    private ResultSet result_set;
    private Connection connection;
    
    
    public AD () {
        this.callable_statement = null;
        this.result_set = null;
    }

    public void setAD(String driver, String usuario, String clave, String base_datos, int puerto, String host) {
        this.callable_statement = null;
        this.result_set = null;
        
        this.driver = driver;
        this.usuario = usuario;
        this.clave = clave;
        this.base_datos = base_datos;
        this.puerto = puerto;
        this.host = host;
        this.url = "jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.base_datos;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public String getBase_datos() {
        return base_datos;
    }

    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }

    public CallableStatement getCallable_statement() {
        return callable_statement;
    }

    public void setCallable_statement(CallableStatement callable_statement) {
        this.callable_statement = callable_statement;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMensaje_error() {
        return mensaje_error;
    }

    public void setMensaje_error(String mensaje_error) {
        this.mensaje_error = mensaje_error;
    }

    public PreparedStatement getPrepared_statement() {
        return prepared_statement;
    }

    public void setPrepared_statement(PreparedStatement prepared_statement) {
        this.prepared_statement = prepared_statement;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public ResultSet getResult_set() {
        return result_set;
    }

    public void setResult_set(ResultSet result_set) {
        this.result_set = result_set;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    // </editor-fold>
    
    public void conectar () {
        try {
            Class.forName( this.driver ).newInstance();
            this.connection = DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (InstantiationException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            this.mensaje_error = ex.getMessage();
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            this.mensaje_error = ex.getMessage();
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrar () {
        try {
            if (this.prepared_statement != null) this.prepared_statement.close();
            if (this.statement != null) this.statement.close();
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Permite consultas de datos a la Base de la aplicación correspondiente.
     * <p><strong>Precondiciones:</strong> sql != null</p>
     * <p><strong>Poscondiciones:</strong> Registro contiene una lista de los
     * datos solicitados, como también puede ser nulo.</p>
     * @param sql El correspondiente query a ser ejecutado para extraer información de la misma.
     * @return una lista de los registros consultados.
     */
    public Registro ejecutar_consulta (String sql) {
        Registro objetos = new Registro();
        this.conectar();
        try {
            this.prepared_statement = this.connection.prepareStatement( sql );
            this.result_set = this.prepared_statement.executeQuery();
            objetos = convertir_registro( this.result_set );
        } catch (SQLException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cerrar();
        return objetos;
    }

    /** Permite la ejecución de procedures almacenados en la Base de Datos, con
     * la correspondiente autenticación.
     * <p><strong>Precondiciones:</strong> procedure!=null && objetos!=null</p>
     * <p><strong>Poscondiciones:</strong> Una respuesta que contiene un mensaje en caso de error, y los objetos que se puedan obtener del procedure.</p>
     * @param sql El correspondiente procedure que va a ser ejecutado.
     * @return una respuesta que contiene un mensaje y una lista de objetos.
     */
    public Respuesta ejecutar_procedimiento (String procedure, Parametros objetos) {
        boolean centinela = false;
        Respuesta respuesta = new Respuesta();

        this.conectar();
        try {
            this.callable_statement = (CallableStatement) this.connection.prepareCall(procedure);
            /** Seteo de los correspondientes datos en el procedure. */
            for (int i=0; i<objetos.size(); i++) {
                Dato dato = new Dato();
                dato = objetos.get(i);
                if (dato.getTipo() == Tipo.IN) {
                    /** Seteo de los datos de tipo IN. */
                    if (dato.getDato() != null)
                        this.callable_statement.setObject(i+1, dato.getDato());
                    else
                        this.callable_statement.setNull(i+1, retornarTipo(dato.getTipo_dato()));
                } else if (dato.getTipo() == Tipo.OUT) {
                    /** Seteo de los datos de tipo OUT, se cambia de manera lógica
                     * un centinela para que luego se pueda obtener los datos de
                     * salida de un procedure. */
                    if (dato.getDato() != null)
                        this.callable_statement.registerOutParameter(i+1, retornarTipo(dato.getDato().getClass().toString()));
                    else
                        //this.cs.registerOutParameter(i+1, Types.NULL);
                        this.callable_statement.registerOutParameter(i+1, retornarTipo(dato.getTipo_dato()));
                    /** Centinela para conocer si hay parametros tipos OUT. */
                    centinela = true;
                }
            }
            /** Ejecución del correspondiente procedure. */
            this.callable_statement.execute();
            /** Asignación de los datos de salida de un procedure, si los hubiera. */
            if (centinela) {
                ArrayList<Dato> datos = new ArrayList<Dato>();
                for (int i=0; i<objetos.size(); i++) {
                    Dato dato = new Dato();
                    dato = objetos.get(i);
                    if (dato.getTipo() == Tipo.OUT) {
                        dato.setDato(this.callable_statement.getObject(i+1));
                        datos.add(dato);
                    }
                }
                respuesta.setObjetos(datos);
            }
            /** Asignación del mensaje de salida. */
            respuesta.setMensaje("OK:Exitoso");
        } catch (SQLException ex) {
            respuesta.setMensaje("ERROR:"+ex.getMessage());
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cerrar();

        return respuesta;
    }

    public String ejecutar_actualizacion (Connection connection, String sql, Parametros objetos) {
        String mensaje = "";
        try {
            this.prepared_statement = connection.prepareStatement(sql);
            /** Seteo de los correspondientes datos en el SQL. */
            for (int i=0; i<objetos.size(); i++) {
                Dato dato = new Dato();
                dato = objetos.get(i);
                if (dato.getDato() != null)
                    this.prepared_statement.setObject(i+1, dato.getDato());
                else
                    this.prepared_statement.setNull(i+1, retornarTipo(dato.getTipo_dato()));
            }
            /** Ejecución del correspondiente SQL. */
            this.prepared_statement.executeUpdate();
            mensaje = "OK:Exitoso";
        } catch (SQLException ex) {
            try {
                this.connection.rollback();
                this.connection.setAutoCommit(true);
                mensaje = "ERROR:" + ex.getMessage();
            } catch (SQLException ex1) {
                mensaje = "ERROR:" + ex1.getMessage();
                Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    public Registro ejecutar_consulta (Connection connection, String sql) {
        Registro objetos = new Registro();
        try {
            this.statement = connection.createStatement( );
            this.result_set = this.statement.executeQuery( sql );
            objetos = convertir_registro( this.result_set );
        } catch (SQLException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objetos;
    }

    private Registro convertir_registro(ResultSet result_set) {
        Registro objetos = new Registro();
        try {
            while (result_set.next()) {
                /** Creación del Objeto en el que se va a almacenar el registro entero contenido en el ResulSet. */
                Table<String, Object> dato = new Table<String, Object>();
                int num_elementos = result_set.getMetaData().getColumnCount();
                for (int i = 1; i <= num_elementos; i++) {
                    if (result_set.getObject(i) != null)
                        dato.put(result_set.getMetaData().getColumnLabel(i), result_set.getObject(i));
                    else
                        dato.put(result_set.getMetaData().getColumnLabel(i), new Object());
                }
                objetos.add( dato );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objetos;
    }

    private int retornarTipo (String dato) {
        if (dato.compareTo("class java.math.BigDecimal") == 0)
            return Types.DECIMAL;
        else if (dato.compareTo("class java.lang.Integer") == 0)
            return Types.INTEGER;
        else if (dato.compareTo("class java.lang.String") == 0)
            return Types.VARCHAR;
        else if (dato.compareTo("class java.lang.Double") == 0)
            return Types.DOUBLE;
        else if (dato.compareTo("class java.sql.Date") == 0)
            return Types.DATE;
        else if (dato.compareTo("class java.sql.Time") == 0)
            return Types.TIME;
        else if (dato.compareTo("class java.lang.Float") == 0)
            return Types.FLOAT;
        else if (dato.compareTo("class java.lang.Boolean") == 0 || dato.compareTo("class java.lang.boolean") == 0)
            return Types.BIT;
        
        return Types.NULL;
    }
}