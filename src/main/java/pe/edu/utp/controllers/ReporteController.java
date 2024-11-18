package pe.edu.utp.controllers;

import pe.edu.utp.dao.ReporteDAO;
import pe.edu.utp.daoImp.ReporteDAOImp;
import pe.edu.utp.models.Reporte;
import pe.edu.utp.models.Venta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReporteController {

    private ReporteDAO reporteDAO;

    public ReporteController(Connection connection) {
        this.reporteDAO = new ReporteDAOImp(connection);
    }

    public List<Venta> generarReporteMensual(String mes, String anio) {
        return reporteDAO.getVentasMensuales(mes, anio);
    }
}
