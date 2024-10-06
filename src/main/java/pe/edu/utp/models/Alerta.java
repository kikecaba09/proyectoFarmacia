package pe.edu.utp.models;

import java.sql.Date;

public class Alerta {

    private int idAlerta;
    private int idMedicamento;
    private Date fechaAlerta;
    private String descripcion;

    public Alerta(int idAlerta, int idMedicamento, Date fechaAlerta, String descripcion) {
        this.idAlerta = idAlerta;
        this.idMedicamento = idMedicamento;
        this.fechaAlerta = fechaAlerta;
        this.descripcion = descripcion;
    }

    public Alerta() {
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "idAlerta=" + idAlerta +
                ", idMedicamento=" + idMedicamento +
                ", fechaAlerta=" + fechaAlerta +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
