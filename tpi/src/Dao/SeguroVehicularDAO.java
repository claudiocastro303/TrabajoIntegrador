package Dao;

import Config.DatabaseConnection;
import Models.Vehiculo;
import Models.SeguroVehicular;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeguroVehicularDAO implements GenericDAO<SeguroVehicular> {
      //implementa las operaciones necesarioas para guardar, actualizar, eliminar y obtener domicilios
    
    @Override
    public void insertar(SeguroVehicular seguro) throws SQLException {
           //inserta un nuevo domicilio uasndo una coneccion simple
           //prepara la consulta con valores "?" que se llenan con los datos del objeto.
           //luego recupera el id generado por la base de datospara asignarlo al objeto
           String sql = "INSERT INTO seguro_vehicular (aseguradora, nroPoliza) VALUES (?, ?)";
           
           try (Connection conn = DatabaseConnection.getConnection();
                   PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, seguro.getAseguradora());
                    stmt.setString(2, seguro.getNroPoliza());
                    stmt.executeUpdate();
                    
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                          if (generatedKeys.next()) {
                                 seguro.setId(generatedKeys.getInt(1));
                                 System.out.println("seguro insertado con id: " + seguro.getId());
                            }  else  {
                             throw new SQLException("La insercion del seguro fallo");
                           }
                    }
           }  
    
     }

     @Override
      public void actualizar(SeguroVehicular seguro) throws SQLException {
             //elimina un seguro por id
             String sql = "UPDATE seguro_vehicular SET aseguradora = ?, nroPoliza = ? WHERE id=?";
              try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) { 
              stmt.setString(1,seguro.getAseguradora());
              stmt.setString(2, seguro.getNroPoliza());
              stmt.setInt(3, seguro.getId());
              stmt.executeUpdate();
              }
      }

        
      @Override
      public void eliminar(SeguroVehicular entidad) throws SQLException {
          int id_seg = entidad.getId();
             //elimina un seguro por id
             String sql= "UPDATE seguro_vehicular SET eliminado = TRUE WHERE id =?;\n";
             try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {  
                    stmt.setInt(1, id_seg);
                    stmt.executeUpdate();
             }
      }
      
      
      @Override
      public void recuperar (int id)  throws SQLException {
             //recupera un domicilio por id
             String sql = "UPDATE seguro_vehicular SET eliminado = FALSE WHERE id = ?;\n";
             try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {  
                     stmt.setInt(1, id);
                     stmt.executeUpdate();
             }
      }
      
      
      @Override
      public SeguroVehicular getById(int id) throws SQLException {
              //obriene un domicilio por id
              String sql = "SELECT * FROM seguro_vehicular WHERE id=?";
              try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) { 
                     stmt.setInt(1, id);
                     ResultSet rs = stmt.executeQuery();
                     if (rs.next()) {
                             return new SeguroVehicular(
                                             rs.getInt("id"),
                             rs.getString("aseguradora"),
                                 rs.getString("nroPoliza") );
                     }
              }
              return null;
      }
      
      
      @Override
      public List<SeguroVehicular> getAll() throws SQLException {
              //obtiene todos los domicilios gusdados en la base de datos
              String sql = "SELECT * FROM seguro_vehicular WHERE eliminado = FALSE ";
              try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) { 
                     ResultSet rs = stmt.executeQuery();
                     List<SeguroVehicular> seguros = new ArrayList<SeguroVehicular>();
                     while (rs.next()) {
                              seguros.add(new SeguroVehicular(
                                                rs.getInt("id"),
                                                rs.getString("aseguradora"),
                                             rs.getString("nroPoliza"))) ;
                     }
                     return seguros;
              }
      }
      
      
        
}