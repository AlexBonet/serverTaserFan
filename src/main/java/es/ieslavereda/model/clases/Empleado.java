package es.ieslavereda.model.clases;

import oracle.sql.RAW;

import java.sql.Date;
import java.sql.Timestamp;

public class Empleado {
    private int idCliente;
    private String dni;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String cp;
    private String email;
    private Date fechaNac;
    private String cargo;
    private RAW password;
    private Timestamp changeDts;
    private String changeBy;

    public Empleado(int idCliente, String dni, String nombre, String apellidos, String domicilio, String cp, String email, Date fechaNac, String cargo, RAW password, Timestamp changeDts, String changeBy) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cp = cp;
        this.email = email;
        this.fechaNac = fechaNac;
        this.cargo = cargo;
        this.password = password;
        this.changeDts = changeDts;
        this.changeBy = changeBy;
    }

    public Empleado(int idCliente, String dni, String nombre, String apellidos, String domicilio, String cp, String email, Date fechaNac, String cargo) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cp = cp;
        this.email = email;
        this.fechaNac = fechaNac;
        this.cargo = cargo;
    }

    public Empleado() {
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cp='" + cp + '\'' +
                ", email='" + email + '\'' +
                ", fechaNac=" + fechaNac +
                ", cargo='" + cargo + '\'' +
                ", password=" + password +
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public RAW getPassword() {
        return password;
    }

    public void setPassword(RAW password) {
        this.password = password;
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
