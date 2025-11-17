package Models;


public class Vehiculo extends Base {
    private String dominio;
    private String marca;
    private String modelo;
    private int anio;
    private SeguroVehicular seguro;

    //Construcor
    public Vehiculo(int id, String dominio, String marca, String modelo, int anio, SeguroVehicular seguro) {
        super(id, false);
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.seguro = seguro;
    }
//constrtuctor vacio
    public Vehiculo() {
        super();
    }
    
//getters y setters

    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    public SeguroVehicular getSeguro() {
        return seguro;
    }
    public void setSeguro(SeguroVehicular seguro) {
        this.seguro = seguro;
    }
    
  
    //metodo tostring

    @Override
    public String toString() {
        return "Vehiculo{" + 
                       " id= " + getId() + 
                       ", dominio=" + dominio + 
                       ", marca=" + marca + 
                       ", modelo=" + modelo + 
                       ", anio=" + anio + 
                       ", seguro=" + seguro + 
                       ", eliminado=" + getEliminado() + 
                      "}";
    }
    
    
}
