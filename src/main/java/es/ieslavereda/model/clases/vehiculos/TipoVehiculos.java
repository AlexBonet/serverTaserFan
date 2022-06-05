package es.ieslavereda.model.clases.vehiculos;
public enum TipoVehiculos {
    COCHE("COCHE"), MOTOS("MOTOS"), BICIS("BICIS"), PATIN("PATIN");

    private String txt;

    TipoVehiculos(String str){
        this.txt = str;
    }

    @Override
    public String toString() {
        return txt ;
    }
}
