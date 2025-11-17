package Service;


import Config.DatabaseConnection;
//import Config.TransactionManager;
import Dao.SeguroVehicularDAO;
import Dao.VehiculoDAO;
import Models.Vehiculo;
        
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;


//esta clase implementa las operaciones de negocio relacionadas con vehiculos
public class VehiculoServiceImpl implements GenericService<Vehiculo> {
      private final VehiculoDAO vehiculoDAO;
      
      //constructor que recibe los objetos DAO para vehiculo y seguro
      public VehiculoServiceImpl(VehiculoDAO vehiculoDAO) {
            this.vehiculoDAO = vehiculoDAO;
      }
      
    
      //inserta un vehiculo sin usar tansaccion (uso derecto del DAO)
      @Override
      public void insertar(Vehiculo vehiculo) throws Exception {
             //validaciones para asegurarse de que losdatos esten completos
             if (vehiculo.getDominio() == null || vehiculo.getDominio().trim().isEmpty()) {
                   throw new IllegalArgumentException("el dominio del vehiculo no puede ser vacio");
             }
             
            if (vehiculo.getMarca() == null || vehiculo.getMarca().trim().isEmpty()) {
                   throw new IllegalArgumentException("la marca del vehiculo no puede ser vacio");
             }
              
            if (vehiculo.getModelo() == null || vehiculo.getModelo().trim().isEmpty()) {
                   throw new IllegalArgumentException("el modelo del vehiculo no puede ser vacio");
            }
              
           if (vehiculo.getAnio() <= 0 ) {
                   throw new IllegalArgumentException("el año del vehiculo no puede ser vacio");
            }
            
            System.out.println("insertando vehiculo: " + vehiculo.getDominio());
            vehiculoDAO.insertar(vehiculo); //inserta el vehiculo en labase de datos
             
      }
      
       // ctualiza una vehiculo(solo sus datos, no el seguro)
      @Override
      public void  actualizar(Vehiculo entidad) throws Exception {
              if( entidad.getDominio() == null || entidad.getDominio().trim().isEmpty()) {
                      throw new IllegalArgumentException("E dominio no puede ser vacio");
              }
      
              if( entidad.getMarca() == null || entidad.getMarca().trim().isEmpty()) {
                      throw new IllegalArgumentException("la marca no puede ser vacio");
              }
              
               if (entidad.getModelo() == null || entidad.getModelo().trim().isEmpty()) {
                   throw new IllegalArgumentException("el modelo del vehiculo no puede ser vacio");
            }
              
             if (entidad.getAnio() <= 0 ) {
                   throw new IllegalArgumentException("el año del vehiculo no puede ser vacio");
            }   
             
             vehiculoDAO.actualizar(entidad);
      }
      
      
      //elimina un vehiculo por su id
      @Override
      public void eliminar(Vehiculo entidad) throws Exception {
            vehiculoDAO.eliminar(entidad);
      }
      
      
      //obtiene un ehiculo por su id
      @Override
      public Vehiculo getById(int id) throws Exception {
              return vehiculoDAO.getById(id);
      }
      
      //obtiene todos los vehiculos registrados
      @Override
      public List<Vehiculo> getAll() throws Exception {
                return vehiculoDAO.getAll();
      }
      
      //devuelve el servicio de seguro (para usar en el menu o desde otras clases)
      
      //devuelve una lista de vehiculos cuyo diminio o marca contenga el texto indicado
      public List<Vehiculo> buscarPorDominioMarca(String filtro) throws Exception {
               return vehiculoDAO.buscarPorDominioMarca(filtro);
      }
      
}
