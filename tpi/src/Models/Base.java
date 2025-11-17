package Models;

//clase abstracta, no sirve para instanciar, tiene atributos comunes , los hijos eredan esos atributos
public abstract class Base {
    //atributos
    private int id;  //  identificador unico
    private  Boolean eliminado;  //Marce de nuestra base da datos cuando un elemento fue eliminado

    //constructor
    public Base(int id, Boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    
    //geters y seters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    //constructor vacio
    public Base() {
    }
    
    
}
