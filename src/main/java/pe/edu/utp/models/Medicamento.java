package pe.edu.utp.models;

import java.sql.Date;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private Date fechaIngreso;
    private Date fechaCaducidad;
    private double precioActual;
    private int stock;
    private int idCategoria;
    private int idLaboratorio;

    public Medicamento(int idMedicamento, String nombre, String descripcion, Date fechaIngreso,
                       Date fechaCaducidad, double precioActual, int stock, int idCategoria,
                       int idLaboratorio) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.fechaCaducidad = fechaCaducidad;
        this.precioActual = precioActual;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.idLaboratorio = idLaboratorio;
    }

    public Medicamento() {
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMedicamento=" + idMedicamento +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaCaducidad=" + fechaCaducidad +
                ", precioActual=" + precioActual +
                ", stock=" + stock +
                ", idCategoria=" + idCategoria +
                ", idLaboratorio=" + idLaboratorio +
                '}';
    }
}
