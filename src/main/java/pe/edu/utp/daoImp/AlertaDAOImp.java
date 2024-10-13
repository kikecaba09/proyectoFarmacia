package pe.edu.utp.daoImp;

import pe.edu.utp.dao.AlertaDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Alerta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlertaDAOImp implements AlertaDAO {
    private static final String SELECT_ALERTAS_POR_MEDICAMENTO_SQL = "SELECT * FROM alerta WHERE id_medicamento = ?";
    private static final String SELECT_TODAS_ALERTAS_SQL = "SELECT * FROM alerta"; // Para el reporte

    @Override
    public List<Alerta> obtenerAlertasPorMedicamento(int idMedicamento) throws SQLException {
        List<Alerta> alertas = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALERTAS_POR_MEDICAMENTO_SQL)) {
            preparedStatement.setInt(1, idMedicamento);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Alerta alerta = new Alerta();
                alerta.setIdAlerta(rs.getInt("id_alerta"));
                alerta.setIdMedicamento(rs.getInt("id_medicamento"));
                alerta.setFechaAlerta(rs.getDate("fecha_alerta"));
                alerta.setDescripcion(rs.getString("descripcion"));
                alertas.add(alerta);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener alertas por medicamento", e);
        }
        return alertas;
    }

    @Override
    public List<Alerta> generarReporteAlertas() throws SQLException { // Implementación para el reporte
        List<Alerta> alertas = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODAS_ALERTAS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Alerta alerta = new Alerta();
                alerta.setIdAlerta(rs.getInt("id_alerta"));
                alerta.setIdMedicamento(rs.getInt("id_medicamento"));
                alerta.setFechaAlerta(rs.getDate("fecha_alerta"));
                alerta.setDescripcion(rs.getString("descripcion"));
                alertas.add(alerta);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al generar el reporte de alertas", e);
        }
        return alertas;
    }
}
