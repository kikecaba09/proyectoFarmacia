package pe.edu.utp.models;

public class DetalleVenta {

    private int idDetalleVenta;
    private int cantidad;
    private double precioUnitario;
    private int idVenta;
    private int idMedicamento;

    public DetalleVenta(int idDetalleVenta, int cantidad, double precioUnitario, int idVenta,
                        int idMedicamento) {
        this.idDetalleVenta = idDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idVenta = idVenta;
        this.idMedicamento = idMedicamento;
    }

    public DetalleVenta() {
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @Override
    public String toString() {
        return "detalleVenta{" +
                "idDetalleVenta=" + idDetalleVenta +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", idVenta=" + idVenta +
                ", idMedicamento=" + idMedicamento +
                '}';
    }
}
