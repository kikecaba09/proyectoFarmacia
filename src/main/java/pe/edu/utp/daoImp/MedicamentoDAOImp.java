package pe.edu.utp.daoImp;

import pe.edu.utp.dao.MedicamentoDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImp implements MedicamentoDAO {

    private Connection connection;

    public MedicamentoDAOImp() {
        try {
            this.connection = ConexionBD.getConnection(); // Obtener la conexión desde la clase ConexionBD
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medicamento> obtenerTodos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM medicamento";  // Adjust as per your DB
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idMedicamento = rs.getInt("idMedicamento");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precioActual = rs.getDouble("precio_actual");
                int stock = rs.getInt("stock");

                Medicamento medicamento = new Medicamento(idMedicamento, nombre, descripcion, precioActual, stock);
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }



    @Override
    public boolean agregarMedicamento(Medicamento medicamento) {
        String sql = "INSERT INTO medicamento (nombre, descripcion, idCategoria, idLaboratorio, fecha_ingreso, fecha_caducidad, precio_actual, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getDescripcion());
            ps.setInt(3, medicamento.getIdCategoria());
            ps.setInt(4, medicamento.getIdLaboratorio());
            ps.setDate(5, medicamento.getFechaIngreso());
            ps.setDate(6, medicamento.getFechaCaducidad());
            ps.setDouble(7, medicamento.getPrecioActual());
            ps.setInt(8, medicamento.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean editarMedicamento(Medicamento medicamento) {
        String sql = "UPDATE medicamento SET nombre = ?, descripcion = ?, idCategoria = ?, idLaboratorio = ?, fecha_ingreso = ?, fecha_caducidad = ?, precio_actual = ?, stock = ? WHERE idMedicamento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, medicamento.getNombre());
            ps.setString(2, medicamento.getDescripcion());
            ps.setInt(3, medicamento.getIdCategoria());
            ps.setInt(4, medicamento.getIdLaboratorio());
            ps.setDate(5, medicamento.getFechaIngreso());
            ps.setDate(6, medicamento.getFechaCaducidad());
            ps.setDouble(7, medicamento.getPrecioActual());
            ps.setInt(8, medicamento.getStock());
            ps.setInt(9, medicamento.getIdMedicamento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarMedicamento(int idMedicamento) {
        String sql = "DELETE FROM medicamento WHERE idMedicamento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idMedicamento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Medicamento> getAllMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        String query = "SELECT * FROM medicamento";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("idMedicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setIdCategoria(rs.getInt("idCategoria"));
                medicamento.setIdLaboratorio(rs.getInt("idLaboratorio"));
                medicamento.setFechaIngreso(rs.getDate("fecha_ingreso"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamento.setPrecioActual(rs.getDouble("precio_actual"));
                medicamento.setStock(rs.getInt("stock"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    @Override
    public List<Medicamento> getMedicamentosConStockMinimo(int stockMinimo) {
        List<Medicamento> medicamentos = new ArrayList<>();
        String query = "SELECT * FROM medicamento WHERE stock <= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, stockMinimo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(rs.getInt("idMedicamento"));
                medicamento.setNombre(rs.getString("nombre"));
                medicamento.setDescripcion(rs.getString("descripcion"));
                medicamento.setIdCategoria(rs.getInt("idCategoria"));
                medicamento.setIdLaboratorio(rs.getInt("idLaboratorio"));
                medicamento.setFechaIngreso(rs.getDate("fecha_ingreso"));
                medicamento.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicamento.setPrecioActual(rs.getDouble("precio_actual"));
                medicamento.setStock(rs.getInt("stock"));
                medicamentos.add(medicamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    @Override
    public void actualizarStock(int idMedicamento, int nuevoStock) {
        String query = "UPDATE medicamento SET stock = ? WHERE idMedicamento = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, idMedicamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión después de todas las operaciones (si es necesario)
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
