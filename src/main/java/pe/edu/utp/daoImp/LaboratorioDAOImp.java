package pe.edu.utp.daoImp;

import pe.edu.utp.dao.LaboratorioDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Laboratorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDAOImp implements LaboratorioDAO {

    private static final String SELECT_ALL_LABORATORIOS_SQL = "SELECT * FROM laboratorio";
    private static final String SELECT_LABORATORIO_BY_ID_SQL = "SELECT * FROM laboratorio WHERE id_laboratorio = ?";
    private static final String UPDATE_LABORATORIO_SQL = "UPDATE laboratorio SET nombre = ?, direccion = ?, telefono = ? WHERE id_laboratorio = ?";
    private static final String INSERT_LABORATORIO_SQL = "INSERT INTO laboratorio (nombre, direccion, telefono) VALUES (?, ?, ?)";
    private static final String DELETE_LABORATORIO_SQL = "DELETE FROM laboratorio WHERE idLaboratorio = ?";

    @Override
    public List<Laboratorio> obtenerTodosLosLaboratorios() throws SQLException {
        List<Laboratorio> laboratorios = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LABORATORIOS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre(rs.getString("nombre"));
                laboratorio.setDireccion(rs.getString("direccion"));
                laboratorio.setTelefono(rs.getString("telefono"));
                laboratorios.add(laboratorio);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todos los laboratorios", e);
        }
        return laboratorios;
    }

    @Override
    public Laboratorio obtenerLaboratorioPorId(int idLaboratorio) throws SQLException {
        Laboratorio laboratorio = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LABORATORIO_BY_ID_SQL)) {
            preparedStatement.setInt(1, idLaboratorio);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                laboratorio = new Laboratorio();
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre(rs.getString("nombre"));
                laboratorio.setDireccion(rs.getString("direccion"));
                laboratorio.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el laboratorio por ID", e);
        }
        return laboratorio;
    }

    @Override
    public void actualizarDatosLaboratorio(int idLaboratorio, String nombre, String direccion, String telefono) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LABORATORIO_SQL)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, direccion);
            preparedStatement.setString(3, telefono);
            preparedStatement.setInt(4, idLaboratorio);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar los datos del laboratorio", e);
        }
    }

    @Override
    public String generarReportePorLaboratorio(int idLaboratorio) throws SQLException {
        Laboratorio laboratorio = obtenerLaboratorioPorId(idLaboratorio);
        if (laboratorio == null) {
            throw new SQLException("Laboratorio no encontrado");
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Laboratorio:\n");
        reporte.append("ID: ").append(laboratorio.getIdLaboratorio()).append("\n");
        reporte.append("Nombre: ").append(laboratorio.getNombre()).append("\n");
        reporte.append("Dirección: ").append(laboratorio.getDireccion()).append("\n");
        reporte.append("Teléfono: ").append(laboratorio.getTelefono()).append("\n");

        return reporte.toString();
    }

    @Override
    public void agregarLaboratorio(Laboratorio laboratorio) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LABORATORIO_SQL)) {
            preparedStatement.setString(1, laboratorio.getNombre());
            preparedStatement.setString(2, laboratorio.getDireccion());
            preparedStatement.setString(3, laboratorio.getTelefono());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarLaboratorio(int idLaboratorio) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LABORATORIO_SQL)) {
            preparedStatement.setInt(1, idLaboratorio);
            preparedStatement.executeUpdate();
        }
    }
}
