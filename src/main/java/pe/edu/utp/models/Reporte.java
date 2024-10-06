package pe.edu.utp.models;

import java.sql.Date;

public class Reporte {

    private int idReporte;
    private String tipoReporte;
    private Date fechaGeneracion;
    private int idUsuario;

    public Reporte(int idReporte, String tipoReporte, Date fechaGeneracion, int idUsuario) {
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.idUsuario = idUsuario;
    }

    public Reporte() {
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "idReporte=" + idReporte +
                ", tipoReporte='" + tipoReporte + '\'' +
                ", fechaGeneracion=" + fechaGeneracion +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
