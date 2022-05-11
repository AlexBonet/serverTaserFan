package es.ieslavereda.model.clases;

import es.ieslavereda.model.clases.Vehiculo;

import java.sql.Timestamp;
import java.sql.Date;

public class Patinete extends Vehiculo {
    private int numRuedas;
    private int tamanyo;

    public Patinete(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaAdq, String estado, int idCarnet, Timestamp changeDts, String changeBy, int numRuedas, int tamanyo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public Patinete(int numRuedas, int tamanyo) {
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    @Override
    public String toString() {
        return "Patinete{" +
                "numRuedas=" + numRuedas +
                ", tamanyo=" + tamanyo +
                '}';
    }
}
