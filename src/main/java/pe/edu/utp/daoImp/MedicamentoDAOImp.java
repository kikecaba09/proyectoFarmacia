package pe.edu.utp.daoImp;

import pe.edu.utp.dao.MedicamentoDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImp implements MedicamentoDAO {

    private static final String SELECT_MEDICAMENTOS_BY_CATEGORIA_SQL = "SELECT * FROM medicamento WHERE id_categoria = ?";
    private static final String SELECT_MEDICAMENTO_BY_ID_SQL = "SELECT * FROM medicamento WHERE id_medicamento = ?";
    private static final String UPDATE_PRECIO_SQL = "UPDATE medicamento SET precio_actual = ? WHERE id_medicamento = ?";
    private static final String SELECT_MEDICAMENTOS_PROXIMOS_A_VENCER_SQL = "SELECT * FROM medicamento WHERE fecha_caducidad < NOW() + INTERVAL 30 DAY";
    private static final String SELECT_MEDICAMENTOS_BY_LABORATORIO_SQL = "SELECT * FROM medicamento WHERE id_laboratorio = ?";
    private static final String INSERT_MEDICAMENTO_SQL = "INSERT INTO medicamento (nombre, descripcion, idCategoria, idLaboratorio, fecha_ingreso, fecha_caducidad, precio_actual, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_MEDICAMENTO_SQL = "UPDATE medicamento SET nombre = ?, descripcion = ?, idCategoria = ?, idLaboratorio = ?, fecha_ingreso = ?, fecha_caducidad = ?, precio_actual = ?, stock = ? WHERE idMedicamento = ?";
    private static final String DELETE_MEDICAMENTO_SQL = "DELETE FROM medicamento WHERE idMedicamento = ?";

    @Override
    public List<Medicamento> obtenerMedicamentosPorCategoria(int idCategoria) throws SQLException {
        List<Medicamento> medicamentos = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICAMENTOS_BY_CATEGORIA_SQL)) {
            preparedStatement.setInt(1, idCategoria);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("id_medicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setFechaIngreso(rs.getDate("fecha_ingreso"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamento.setPrecioActual(rs.getDouble("precio_actual"));
                medicamento.setStock(rs.getInt("stock"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener medicamentos por categoría", e);
        }
        return medicamentos;
    }

    @Override
    public List<Medicamento> obtenerMedicamentosPorLaboratorio(int idLaboratorio) throws SQLException {
        List<Medicamento> medicamentos = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICAMENTOS_BY_LABORATORIO_SQL)) {
            preparedStatement.setInt(1, idLaboratorio);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("id_medicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setFechaIngreso(rs.getDate("fecha_ingreso"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamento.setPrecioActual(rs.getDouble("precio_actual"));
                medicamento.setStock(rs.getInt("stock"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener medicamentos por laboratorio", e);
        }
        return medicamentos;
    }

    @Override
    public void agregarMedicamento(Medicamento medicamento) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICAMENTO_SQL)) {
            preparedStatement.setString(1, medicamento.getNombre());
            preparedStatement.setString(2, medicamento.getDescripcion());
            preparedStatement.setInt(3, medicamento.getIdCategoria());
            preparedStatement.setInt(4, medicamento.getIdLaboratorio());
            preparedStatement.setDate(5, medicamento.getFechaIngreso());
            preparedStatement.setDate(6, medicamento.getFechaCaducidad());
            preparedStatement.setDouble(7, medicamento.getPrecioActual());
            preparedStatement.setInt(8, medicamento.getStock());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void actualizarMedicamento(Medicamento medicamento) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEDICAMENTO_SQL)) {
            preparedStatement.setString(1, medicamento.getNombre());
            preparedStatement.setString(2, medicamento.getDescripcion());
            preparedStatement.setInt(3, medicamento.getIdCategoria());
            preparedStatement.setInt(4, medicamento.getIdLaboratorio());
            preparedStatement.setDate(5, medicamento.getFechaIngreso());
            preparedStatement.setDate(6, medicamento.getFechaCaducidad());
            preparedStatement.setDouble(7, medicamento.getPrecioActual());
            preparedStatement.setInt(8, medicamento.getStock());
            preparedStatement.setInt(9, medicamento.getIdMedicamento());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarMedicamento(int idMedicamento) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICAMENTO_SQL)) {
            preparedStatement.setInt(1, idMedicamento);
            preparedStatement.executeUpdate();
        }
    }


    @Override
    public Medicamento obtenerMedicamentoPorId(int idMedicamento) throws SQLException {
        Medicamento medicamento = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICAMENTO_BY_ID_SQL)) {
            preparedStatement.setInt(1, idMedicamento);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("id_medicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setFechaIngreso(rs.getDate("fecha_ingreso"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamento.setPrecioActual(rs.getDouble("precio_actual"));
                medicamento.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener medicamento por ID", e);
        }
        return medicamento;
    }

    @Override
    public void actualizarPrecioMedicamento(int idMedicamento, double nuevoPrecio) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRECIO_SQL)) {
            preparedStatement.setDouble(1, nuevoPrecio);
            preparedStatement.setInt(2, idMedicamento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el precio del medicamento", e);
        }
    }

    @Override
    public List<Medicamento> obtenerMedicamentosProximosAVencer() throws SQLException {
        List<Medicamento> medicamentos = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICAMENTOS_PROXIMOS_A_VENCER_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("id_medicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener medicamentos próximos a vencer", e);
        }
        return medicamentos;
    }
}
