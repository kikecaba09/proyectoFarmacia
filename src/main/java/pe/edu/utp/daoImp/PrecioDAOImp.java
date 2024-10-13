package pe.edu.utp.daoImp;

import pe.edu.utp.dao.PrecioDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Precio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrecioDAOImp implements PrecioDAO {

    // Consulta SQL para obtener el precio actual de un medicamento
    private static final String SELECT_PRECIO_ACTUAL_SQL = "SELECT * FROM precio WHERE id_medicamento = ? ORDER BY fecha_actualizacion DESC LIMIT 1";

    // Consulta SQL para actualizar el precio de un medicamento
    private static final String UPDATE_PRECIO_SQL = "UPDATE precio SET precio = ?, fecha_actualizacion = ? WHERE id_medicamento = ?";

    @Override
    public Precio obtenerPrecioActualPorMedicamento(int idMedicamento) throws SQLException {
        Precio precio = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRECIO_ACTUAL_SQL)) {
            preparedStatement.setInt(1, idMedicamento);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                precio = new Precio();
                precio.setIdPrecio(rs.getInt("id_precio"));
                precio.setPrecio(rs.getDouble("precio"));
                precio.setFechaActualizacion(rs.getDate("fecha_actualizacion"));
                precio.setIdMedicamento(rs.getInt("id_medicamento"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el precio actual del medicamento", e);
        }
        return precio;
    }

    @Override
    public void actualizarPrecio(Precio precio) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRECIO_SQL)) {
            preparedStatement.setDouble(1, precio.getPrecio());
            preparedStatement.setDate(2, precio.getFechaActualizacion());
            preparedStatement.setInt(3, precio.getIdMedicamento());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el precio del medicamento", e);
        }
    }
}
