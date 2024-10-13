package pe.edu.utp.controllers;

import pe.edu.utp.dao.ReporteDAO;
import pe.edu.utp.daoImp.ReporteDAOImp;
import pe.edu.utp.models.Reporte;
import java.sql.SQLException;
import java.util.List;

public class ReporteController {
    private ReporteDAO reporteDAO;

    public ReporteController() {
        this.reporteDAO = new ReporteDAOImp();
    }

    // Método para generar el reporte de ventas
    public List<Reporte> generarReporteVentas() {
        try {
            return reporteDAO.generarReporteVentas();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para generar el reporte de inventario
    public List<Reporte> generarReporteInventario() {
        try {
            return reporteDAO.generarReporteInventario();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para generar el reporte de alertas
    public List<Reporte> generarReporteAlertas() {
        try {
            return reporteDAO.generarReporteAlertas();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
