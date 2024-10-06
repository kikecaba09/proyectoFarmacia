package pe.edu.utp.Dao;

import pe.edu.utp.models.Alerta;
import java.sql.SQLException;
import java.util.List;

public interface AlertaDAO {

    List<Alerta> obtenerAlertasPorMedicamento(int idMedicamento) throws SQLException; // Obtener alertas por medicamento
    List<Alerta> generarReporteAlertas() throws SQLException; // Generar reporte de alertas
}
