package Config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    //datos de conexion- se configuran directamente en el codigo
    private static final String URL = "jdbc:mysql://localhost:3306/vehiculo_seguro";
    private static final String USER = "root";
    private static final String PASSWORD = "3035";
    
    static {
           try {
                  //carga del driver JDBC de MySQL una sola vez
                  Class.forName("com.mysql.cj.jdbc.Driver");
           }   catch (ClassNotFoundException e)  {
                     //se lanza una excepcion en cso de que el driver no este disponible
                     throw new RuntimeException("Error: No se encontro el driver JDBC.", e);
           }
    }
    
    
    /**
     * Metodo para obtener una coneccion a la base de datos
     * @return Connection si la coneccion es exitosa
     * @throws SQLException Si hay un problema al conectarse
     */
    
     // Método estático que devuelve una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Validación simple para evitar URLs o credenciales vacías
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Configuración de la base de datos incompleta o inválida");
        }
        // Se obtiene la conexión con DriverManager usando URL, usuario y contraseña
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
