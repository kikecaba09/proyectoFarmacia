package pe.edu.utp.models;

import java.sql.Date;

public class Venta {
    private int idVenta;
    private Date fecha;
    private double total;
    private int idUsuario;

    public Venta(int idVenta, Date fecha, double total, int idUsuario) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.idUsuario = idUsuario;
    }

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                ", total=" + total +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
