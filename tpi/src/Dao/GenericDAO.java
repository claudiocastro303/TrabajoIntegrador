package Dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {
    //esta es una interfaz generica que define metodos comunes para trabajar con cualquier entidad
    //sirve como base para evitar repetir codigo en distintas clases DAO 
    
    void insertar(T entidad) throws Exception;
    void actualizar (T entidad) throws Exception;
    void eliminar(T entidad) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    void recuperar(int id) throws Exception;
            
}
