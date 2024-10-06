package pe.edu.utp.models;

import java.sql.Date;

public class Precio {

    private int idPrecio;
    private double precio;
    private Date fechaActualizacion;
    private int idMedicamento;

    public Precio(int idPrecio, double precio, Date fechaActualizacion, int idMedicamento) {
        this.idPrecio = idPrecio;
        this.precio = precio;
        this.fechaActualizacion = fechaActualizacion;
        this.idMedicamento = idMedicamento;
    }

    public Precio() {
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @Override
    public String toString() {
        return "Precio{" +
                "idPrecio=" + idPrecio +
                ", precio=" + precio +
                ", fechaActualizacion=" + fechaActualizacion +
                ", idMedicamento=" + idMedicamento +
                '}';
    }
}
