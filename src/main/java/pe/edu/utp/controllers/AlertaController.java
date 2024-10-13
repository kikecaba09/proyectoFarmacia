package pe.edu.utp.controllers;

import pe.edu.utp.dao.AlertaDAO;
import pe.edu.utp.daoImp.AlertaDAOImp;
import pe.edu.utp.models.Alerta;
import java.sql.SQLException;
import java.util.List;

public class AlertaController {
    private AlertaDAO alertaDAO;

    public AlertaController() {
        this.alertaDAO = new AlertaDAOImp();
    }

    // Método para obtener alertas de un medicamento específico
    public List<Alerta> obtenerAlertasPorMedicamento(int idMedicamento) {
        try {
            return alertaDAO.obtenerAlertasPorMedicamento(idMedicamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para generar el reporte de alertas
    public List<Alerta> generarReporteAlertas() {
        try {
            return alertaDAO.generarReporteAlertas();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
