package Service;

import Config.DatabaseConnection;
//import Config.TransactionManager;
import Dao.GenericDAO;
import Models.SeguroVehicular;
        
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;


public class SeguroServiceImpl implements GenericService<SeguroVehicular> {
          //esta clase implementa lasoperciones de negocio relacionadas con los seguros
       //se usa una interface generica para acceder a la base de datos (DAO)
         private final GenericDAO<SeguroVehicular> seguroDAO;
         
         //Constructor: recibe el DAO que se va a usar para trabajar con seguros
         public SeguroServiceImpl(GenericDAO<SeguroVehicular> seguroDAO)  {
                  this.seguroDAO = seguroDAO;
         }
         
         //metodo para insertar un seguro sin transaccion
         @Override
         public  void insertar(SeguroVehicular seguro) throws Exception {
                  //vlidaciones basicas para evitar datos vacios
                  if (seguro.getAseguradora() == null || seguro.getAseguradora().trim().isEmpty()) {
                          throw new IllegalArgumentException("el nombre de la aseguradora no puede ser nulo");
                  }
                  if (seguro.getNroPoliza() == null || seguro.getNroPoliza().trim().isEmpty()) {
                          throw new IllegalArgumentException("el numero de poliza no puede ser nulo");
                  }
                  System.out.println("insertando Seguro: " + seguro.getAseguradora());
                  //insertael domicilio usando el PAO
                  seguroDAO.insertar(seguro);
         }
         
    
         
          @Override
          public void actualizar(SeguroVehicular entidad) throws Exception {
                seguroDAO.actualizar(entidad);
          }
         
         
          
          //elimina un seguro segun su id
          @Override
          public void eliminar(SeguroVehicular entidad) throws Exception {
                seguroDAO.eliminar(entidad);
          }
         
         //busca un domicilio por id
          @Override
          public SeguroVehicular getById(int id) throws Exception {
                      return seguroDAO.getById(id);
          }
          
          
          //devuelve todos los segurosregistrados
          @Override
          public List<SeguroVehicular> getAll() throws Exception {
                    return seguroDAO.getAll();
          }
         
}
