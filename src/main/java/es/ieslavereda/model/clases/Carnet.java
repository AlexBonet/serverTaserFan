package es.ieslavereda.model.clases;

public class Carnet {
    private int idCarnet;
    private String tipo;
    private String descripcion;

    public Carnet(int idCarnet, String tipo, String descripcion) {
        this.idCarnet = idCarnet;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Carnet() {
    }

    public int getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(int idCarnet) {
        this.idCarnet = idCarnet;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Carnet{" +
                "idCarnet=" + idCarnet +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
