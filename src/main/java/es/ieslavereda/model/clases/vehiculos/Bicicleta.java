package es.ieslavereda.model.clases.vehiculos;

import java.sql.Timestamp;
import java.sql.Date;

public class Bicicleta extends Vehiculo {
    private String tipo;

    public Bicicleta(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                     Date fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, TipoVehiculos tipoVehiculo, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy, tipoVehiculo);
        this.tipo = tipo;
    }

    public Bicicleta(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                     Date fechaAdq, String estado, String idCarnet, TipoVehiculos tipoVehiculo, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, tipoVehiculo);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
