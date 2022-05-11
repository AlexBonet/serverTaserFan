package es.ieslavereda.model.clases;

import java.sql.Date;
import java.sql.Timestamp;

public class Factura {
    private int idFactura;
    private Date fecha;
    private float importeBase;
    private float importeIva;
    private int idCliente;
    private Timestamp changeDts;
    private int changeBy;

    public Factura(int idFactura, Date fecha, float importeBase, float importeIva, int idCliente, Timestamp changeDts, int changeBy) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.importeBase = importeBase;
        this.importeIva = importeIva;
        this.idCliente = idCliente;
        this.changeDts = changeDts;
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", fecha=" + fecha +
                ", importeBase=" + importeBase +
                ", importeIva=" + importeIva +
                ", idCliente=" + idCliente +
                ", changeDts=" + changeDts +
                ", changeBy=" + changeBy +
                '}';
    }

    public Factura(){

    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporteBase() {
        return importeBase;
    }

    public void setImporteBase(float importeBase) {
        this.importeBase = importeBase;
    }

    public float getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(float importeIva) {
        this.importeIva = importeIva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getChangeDts() {
        return changeDts;
    }

    public void setChangeDts(Timestamp changeDts) {
        this.changeDts = changeDts;
    }

    public int getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(int changeBy) {
        this.changeBy = changeBy;
    }
}
