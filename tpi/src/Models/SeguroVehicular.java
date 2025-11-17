package Models;

public class SeguroVehicular extends Base{
    //atributos de SeguroVehicular
    private String aseguradora;
    private String nroPoliza;
    
    //constructor
    public SeguroVehicular(int id, String aseguradora, String nroPoliza) {
        super(id, false);
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
    }
    
    //constructor vacio
    public SeguroVehicular() {
    }
    
    
    //gettesrs y setters
    public String getAseguradora() {
        return aseguradora;
    }
    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getNroPoliza() {
        return nroPoliza;
    }
    public void setNroPoliza(String nroPoliza) {
        this.nroPoliza = nroPoliza;
    }
    
    
    //metodo tostring

    @Override
    public String toString() {
        return "SeguroVehicular{" + 
                      "id=" + getId() + 
                      ", aseguradora=" + aseguradora + 
                      ", nroPoliza=" + nroPoliza + 
                      ", eliminado=" + getEliminado() +
                      "}";
    }
    
    
}
