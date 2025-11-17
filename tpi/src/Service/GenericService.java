package Service;

import java.util.List;

public interface GenericService<T>  {
     //esta interfaz generica define lasoperaciones basicas que deben  implementar todos los servicios
    void insertar(T entidad) throws Exception;
    //void insertarTx(T entidad) throws Exception;
    void actualizar(T entidad) throws Exception;
    void eliminar(T entidad) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    
    
}
