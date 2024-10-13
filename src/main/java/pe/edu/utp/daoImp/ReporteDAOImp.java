package pe.edu.utp.daoImp;

import pe.edu.utp.dao.ReporteDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Reporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAOImp implements ReporteDAO {

    // Consulta SQL para generar el reporte de ventas
    private static final String SELECT_REPORTE_VENTAS_SQL = "SELECT * FROM reporte WHERE tipo_reporte = 'Ventas'";

    // Consulta SQL para generar el reporte de inventario
    private static final String SELECT_REPORTE_INVENTARIO_SQL = "SELECT * FROM reporte WHERE tipo_reporte = 'Inventario'";

    // Consulta SQL para generar el reporte de alertas
    private static final String SELECT_REPORTE_ALERTAS_SQL = "SELECT * FROM reporte WHERE tipo_reporte = 'Alertas'";

    @Override
    public List<Reporte> generarReporteVentas() throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REPORTE_VENTAS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdReporte(rs.getInt("id_reporte"));
                reporte.setTipoReporte(rs.getString("tipo_reporte"));
                reporte.setFechaGeneracion(rs.getDate("fecha_generacion"));
                reporte.setIdUsuario(rs.getInt("id_usuario"));
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al generar el reporte de ventas", e);
        }
        return reportes;
    }

    @Override
    public List<Reporte> generarReporteInventario() throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REPORTE_INVENTARIO_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdReporte(rs.getInt("id_reporte"));
                reporte.setTipoReporte(rs.getString("tipo_reporte"));
                reporte.setFechaGeneracion(rs.getDate("fecha_generacion"));
                reporte.setIdUsuario(rs.getInt("id_usuario"));
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al generar el reporte de inventario", e);
        }
        return reportes;
    }

    @Override
    public List<Reporte> generarReporteAlertas() throws SQLException {
        List<Reporte> reportes = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REPORTE_ALERTAS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdReporte(rs.getInt("id_reporte"));
                reporte.setTipoReporte(rs.getString("tipo_reporte"));
                reporte.setFechaGeneracion(rs.getDate("fecha_generacion"));
                reporte.setIdUsuario(rs.getInt("id_usuario"));
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al generar el reporte de alertas", e);
        }
        return reportes;
    }
}
