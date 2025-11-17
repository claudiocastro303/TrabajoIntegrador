package Dao;

import Config.DatabaseConnection;
import Models.Vehiculo;
import Models.SeguroVehicular;
import java.lang.reflect.GenericDeclaration;  //ver depues que es
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO implements GenericDAO<Vehiculo>{
    //simiar a SeguroVehicularDAO, pero trabaja con personas y sus posibles SeguroVehicular
    @Override
    public void insertar(Vehiculo vehiculo) throws Exception{
        //insertar un vehivulo en la tabla
        //tambien guarda el ID generado y lo asigna al objeto.
        //Si el vehiculo tiene un SeguroVehicular, se guarda su ID como clavve forane
           String sql = "INSERT INTO vehiculo (dominio, marca, modelo, anio, seguro_id) VALUES (?,?,?,?,?)";
           
           try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);){
                     stmt.setString(1, vehiculo.getDominio());
                     stmt.setString(2, vehiculo.getMarca());
                    stmt.setString(3, vehiculo.getModelo());
                    stmt.setInt(4, vehiculo.getAnio());
                   if (vehiculo.getSeguro() != null && vehiculo.getSeguro().getId() > 0){
                         stmt.setInt(5, vehiculo.getSeguro().getId() );
                   } else {
                         stmt.setNull(5, java.sql.Types.INTEGER);
                   }
                     stmt.executeUpdate();
        
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                          if (generatedKeys.next()) {
                                 vehiculo.setId(generatedKeys.getInt(1));
                                  System.out.println("Vehiculo insertado con ID: " + vehiculo.getId());
                          }else { 
                                  throw new SQLException("La insercion del vehiculo fallo, no se obtuvo ID generado");
                          }
           }
        }
    }
    
    @Override
    public void actualizar(Vehiculo vehiculo) throws Exception {
        //modifica los datos de un vehiculo ya existente.
         String sql = "UPDATE vehiculo SET dominio = ?, marca = ?, modelo = ?, anio = ?, seguro_id = ? WHERE id = ? ";
         try(Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, vehiculo.getDominio());
             ps.setString(2,vehiculo.getMarca());
             ps.setString(3,vehiculo.getModelo() );
             ps.setInt(4, vehiculo.getAnio());
             if(vehiculo.getSeguro() != null && vehiculo.getSeguro().getId() > 0) {
                   ps.setInt(5, vehiculo.getSeguro().getId());
             } else {
                   ps.setNull(5, java.sql.Types.INTEGER );
             }
             ps.setInt(6, vehiculo.getId());
             ps.executeUpdate();
         }
     }
    
    @Override
    public void eliminar(Vehiculo entidad) throws Exception{
        int id =entidad.getId();
         //marca como eliminado una persona por su id (borrado logico)
         String sql = "UPDATE vehiculo SET eliminado = TRUE, seguro_id = NULL WHERE id = ?";
         try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
         }  catch (SQLException e) {
                throw new SQLException("No se pudo marcar como eliminado el vehiculo con id " + id + ":  ");
         }
    
    }
    
    @Override
    public Vehiculo getById(int id) throws Exception {
         //hace una consulta combinada (JOIN) par obtener el vehiculo y su seguro asociado
         //aca sea armo bardo porque habia que usar SELECT Y JOIN
         String sql = "SELECT p.id, p.dominio, p.marca, p.modelo, p.anio, p.seguro_id, p.eliminado AS vehiculo_eliminado, " +
             "d.id AS seg_id, d.aseguradora, d.nroPoliza, d.eliminado AS seg_eliminado " +
             "FROM vehiculo p LEFT JOIN seguro_vehicular d ON p.seguro_id = d.id " +
             "WHERE p.id = ?";
         try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                  stmt.setInt(1, id);
                  try (ResultSet rs = stmt.executeQuery()) {
                         if (rs.next()) {
                                Vehiculo vehiculo = new Vehiculo();
                                vehiculo.setId(rs.getInt("id"));
                                vehiculo.setDominio(rs.getString("dominio"));
                                vehiculo.setMarca(rs.getString("marca"));
                                vehiculo.setAnio(rs.getInt("anio"));
                                vehiculo.setEliminado(rs.getBoolean("vehiculo_eliminado"));
                                
                                int seguroId = rs.getInt("seguro_id");
                                if (seguroId > 0 ) {
                                    SeguroVehicular seguro = new SeguroVehicular();
                                    seguro.setId(rs.getInt("seg_id"));
                                    seguro.setAseguradora(rs.getString("aseguradora"));
                                    seguro.setNroPoliza(rs.getString("nroPoliza"));
                                    seguro.setEliminado(rs.getBoolean("seg_eliminado"));
                                    vehiculo.setSeguro(seguro);
                                } else {
                                      vehiculo.setSeguro(null);
                                }
                                return vehiculo;
                         }
                  }
         } catch (SQLException e) {
                 throw new Exception("error al obtener persona por id: " + e.getMessage(), e);
         }
         return null;
    }
    
    
    @Override
    public List<Vehiculo> getAll() throws Exception {
             List<Vehiculo> vehiculos = new ArrayList<>();
            String sql = "SELECT p.id, p.dominio, p.marca, p.modelo, p.anio, p.seguro_id, " +
             "d.id AS seg_id, d.aseguradora, d.nroPoliza, d.eliminado AS seg_eliminado " +
             "FROM vehiculo p LEFT JOIN seguro_vehicular d ON p.seguro_id = d.id " +
             "WHERE p.eliminado = FALSE";
             
             try (Connection conn = DatabaseConnection.getConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
             
                    while (rs.next()) {
                           Vehiculo vehiculo = new Vehiculo();
                           vehiculo.setId(rs.getInt("id"));
                           vehiculo.setDominio(rs.getString("dominio"));
                           vehiculo.setMarca(rs.getString("marca"));
                           vehiculo.setModelo(rs.getString("modelo"));
                           vehiculo.setAnio(rs.getInt("anio"));
                           
                           int seguroId = rs.getInt("seguro_id");
                           if (seguroId > 0) {
                                   SeguroVehicular seguro = new SeguroVehicular();
                                   seguro.setId(rs.getInt("seg_id"));
                                   seguro.setAseguradora(rs.getString("aseguradora"));
                                   seguro.setNroPoliza(rs.getString("nroPoliza"));
                                   seguro.setEliminado(rs.getBoolean("seg_eliminado"));
                                   vehiculo.setSeguro(seguro);
                           } else {
                                 vehiculo.setSeguro(null);
                           }
                           vehiculos.add(vehiculo);
                    }
             } catch (SQLException e) {
                     throw new Exception("error al obtener todas laas personas: " + e.getMessage(), e); 
             }
             return vehiculos;
    }
    
    /**
     * Bisca vehiculo cuyo dominio o marca coincida parcialmente con el filtro indicado
     * solo se incluyen vehiculos que noesten  marcadas como eliminadas
     */
    
    
    public  List<Vehiculo> buscarPorDominioMarca(String filtro) throws SQLException {
            List<Vehiculo> vehiculos = new ArrayList<>();
          String sql = "SELECT p.id, p.dominio, p.marca, p.modelo, p.anio, p.seguro_id, p.eliminado AS vehiculo_eliminado, " +
             "d.id AS seg_id, d.aseguradora, d.nroPoliza, d.eliminado AS seg_eliminado " +
             "FROM vehiculo p LEFT JOIN seguro_vehicular d ON p.seguro_id = d.id " +
             "WHERE p.eliminado = FALSE AND (p.dominio LIKE ? OR p.marca LIKE ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1,"%" + filtro + "%");
                    stmt.setString(2,"%" + filtro + "%");
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                            Vehiculo vehiculo = new Vehiculo();
                            vehiculo.setId(rs.getInt("id"));
                            vehiculo.setDominio(rs.getString("dominio"));
                            vehiculo.setMarca(rs.getString("marca"));
                            vehiculo.setModelo(rs.getString("modelo"));
                            vehiculo.setAnio(rs.getInt("anio"));
                            vehiculo.setEliminado(rs.getBoolean("vehiculo_eliminado"));
                            
                            int seguroId = rs.getInt("seguro_id");
                            if (seguroId > 0 ) {
                                    SeguroVehicular seguro = new SeguroVehicular();
                                    seguro.setId(rs.getInt("id"));
                                    seguro.setAseguradora(rs.getString("aseguradora"));
                                    seguro.setNroPoliza(rs.getString("nroPoliza"));
                                    seguro.setEliminado(rs.getBoolean("seg_eliminado"));
                                    vehiculo.setSeguro(seguro);
                            }
                            vehiculos.add(vehiculo);
                    }
                    
            }
            return vehiculos;
    }
    
    
    @Override
    public void recuperar(int id) throws SQLException {
            //RECUPERA UNA PERSONA POR ID
            String sql = "UPDATE vehiculo SET eliminado = FALSE WHERE id = ?;\n";
            try (Connection conn = DatabaseConnection.getConnection();
                   PreparedStatement stmt = conn.prepareStatement(sql)) { 
                   stmt.setInt(1, id);
                   stmt.executeUpdate();
            }
    }
                  
         
}
