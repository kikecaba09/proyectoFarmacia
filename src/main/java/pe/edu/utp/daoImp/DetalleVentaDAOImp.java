package pe.edu.utp.daoImp;

import pe.edu.utp.dao.DetalleVentaDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.DetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAOImp implements DetalleVentaDAO {

    private static final String SELECT_DETALLES_POR_VENTA_SQL = "SELECT * FROM detalleVenta WHERE id_venta = ?";
    private static final String SELECT_DETALLES_POR_MEDICAMENTO_SQL = "SELECT * FROM detalleVenta WHERE id_medicamento = ?";
    private static final String CALCULAR_TOTAL_VENTA_SQL = "SELECT SUM(cantidad * precioUnitario) AS total FROM detalleVenta WHERE id_venta = ?";

    // Obtener detalles de venta por ID de venta
    @Override
    public List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) throws SQLException {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DETALLES_POR_VENTA_SQL)) {
            preparedStatement.setInt(1, idVenta);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdMedicamento(rs.getInt("id_medicamento"));
                detallesVenta.add(detalle);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los detalles de la venta", e);
        }
        return detallesVenta;
    }

    // Obtener detalles de venta por ID de medicamento
    @Override
    public List<DetalleVenta> obtenerDetallesPorMedicamento(int idMedicamento) throws SQLException {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DETALLES_POR_MEDICAMENTO_SQL)) {
            preparedStatement.setInt(1, idMedicamento);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdMedicamento(rs.getInt("id_medicamento"));
                detallesVenta.add(detalle);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los detalles por medicamento", e);
        }
        return detallesVenta;
    }

    // Calcular total de venta por ID de venta
    @Override
    public double calcularTotalVenta(int idVenta) throws SQLException {
        double total = 0.0;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CALCULAR_TOTAL_VENTA_SQL)) {
            preparedStatement.setInt(1, idVenta);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al calcular el total de la venta", e);
        }
        return total;
    }
}
