package es.ieslavereda.model.clases;

import java.sql.Date;
import java.sql.Timestamp;

public class Cliente {
    private int idCliente;
    private String dni;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cp;
    private String email;
    private Date fechaNac;
    private int idCarnet;
    private byte foto;
    private Timestamp changeDts;
    private String changeBy;

    public Cliente(int idCliente, String dni, String nombre, String apellidos, String domicilio, String cp, String email, Date fechaNac, int idCarnet, byte foto, Timestamp changeDts, String changeBy) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cp = cp;
        this.email = email;
        this.fechaNac = fechaNac;
        this.idCarnet = idCarnet;
        this.foto = foto;
        this.changeDts = changeDts;
        this.changeBy = changeBy;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cp='" + cp + '\'' +
                ", email='" + email + '\'' +
                ", fechaNac=" + fechaNac +
                ", idCarnet=" + idCarnet +
                ", foto=" + foto +
                ", changeDts=" + changeDts +
                ", changeBy='" + changeBy + '\'' +
                '}';
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(int idCarnet) {
        this.idCarnet = idCarnet;
    }

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }

    public Timestamp getChangeDts() {
        return changeDts;
    }

    public void setChangeDts(Timestamp changeDts) {
        this.changeDts = changeDts;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }
}
