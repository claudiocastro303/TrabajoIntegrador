package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Config.DatabaseConnection;


public class TestConexion {

  
    public static void main(String[] args) {
       
        
        
   
  
    

      // Intentamos obtener una conexión con try-with-resources para que se cierre sola
        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Conexión establecida con éxito.");
            } else {
                System.out.println("X No se pudo establecer la conexión.");
            }

        } catch (SQLException e) {
            // Captura y muestra errores relacionados con la base de datos
            System.err.println("✗ Error al conectar a la base de datos: " + e.getMessage());
             e.printStackTrace(); // ← ESTO MUESTRA EL ERROR REAL
        }
    }
}

        
        
        
        
        
        

   
   /*
     // Intentamos obtener una conexión con try-with-resources para que se cierre sola
        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn != null) {
                System.out.println("✓ Conexión establecida con éxito.");

                // Sentencia SQL para seleccionar todos los productos
                String sql = "SELECT * FROM propietario";

                // Preparar la sentencia para prevenir inyección SQL y optimizar ejecución
                try (PreparedStatement pstmt = conn.prepareStatement(sql);

                     // Ejecutar la consulta y obtener el resultado
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("Listado de productos:");

                    // Recorrer el ResultSet fila por fila mientras haya registros
                    while (rs.next()) {
                        // Obtener los campos id, nombre y precio de cada fila
                        int id = rs.getInt("id_propietario");
                        String nombre = rs.getString("nombre_propietario");
                        String apellido = rs.getString("apellido_propietario");
                        //double precio = rs.getDouble("precio");

                        // Mostrar los datos por consola
                        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido);
                    }
                }

            } else {
                // En caso que la conexión no se haya establecido correctamente
                System.out.println("X No se pudo establecer la conexión.");
            }

        } catch (SQLException e) {
            // Captura y muestra errores relacionados con la base de datos
            System.err.println("A Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}
*/
        
        
        
        
        
        
        //=========================================
        
      
