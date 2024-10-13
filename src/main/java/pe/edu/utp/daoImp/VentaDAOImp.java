package pe.edu.utp.daoImp;

import pe.edu.utp.dao.VentaDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImp implements VentaDAO {
    private static final String SELECT_ALL_VENTAS_SQL = "SELECT * FROM venta";
    private static final String SELECT_VENTA_BY_ID_SQL = "SELECT * FROM venta WHERE id_venta = ?";
    private static final String SELECT_TOTAL_VENTAS_POR_USUARIO_SQL = "SELECT SUM(total) FROM venta WHERE id_usuario = ?";
    private static final String SELECT_VENTAS_POR_FECHA_SQL = "SELECT * FROM venta WHERE fecha BETWEEN ? AND ?";

    @Override
    public List<Venta> obtenerTodasLasVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VENTAS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setIdUsuario(rs.getInt("id_usuario"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todas las ventas", e);
        }
        return ventas;
    }

    @Override
    public Venta obtenerVentaPorId(int idVenta) throws SQLException {
        Venta venta = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VENTA_BY_ID_SQL)) {
            preparedStatement.setInt(1, idVenta);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setIdUsuario(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la venta por ID", e);
        }
        return venta;
    }

    @Override
    public double calcularTotalVentasPorUsuario(int idUsuario) throws SQLException {
        double total = 0;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL_VENTAS_POR_USUARIO_SQL)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1); // Total de ventas
            }
        } catch (SQLException e) {
            throw new SQLException("Error al calcular el total de ventas por usuario", e);
        }
        return total;
    }

    @Override
    public List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VENTAS_POR_FECHA_SQL)) {
            preparedStatement.setDate(1, fechaInicio);
            preparedStatement.setDate(2, fechaFin);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setIdUsuario(rs.getInt("id_usuario"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener ventas por fecha", e);
        }
        return ventas;
    }
}
