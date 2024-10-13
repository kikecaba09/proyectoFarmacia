package pe.edu.utp.daoImp;

import pe.edu.utp.dao.CategoriaDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImp implements CategoriaDAO {

    private static final String SELECT_ALL_CATEGORIAS_SQL = "SELECT * FROM categoria";
    private static final String SELECT_CATEGORIA_BY_ID_SQL = "SELECT * FROM categoria WHERE id_categoria = ?";
    private static final String SELECT_CATEGORIAS_BY_NOMBRE_SQL = "SELECT * FROM categoria WHERE nombre LIKE ?";
    private static final String INSERT_CATEGORIA_SQL = "INSERT INTO categoria (nombre) VALUES (?)";
    private static final String UPDATE_CATEGORIA_SQL = "UPDATE categoria SET nombre = ? WHERE idCategoria = ?";
    private static final String DELETE_CATEGORIA_SQL = "DELETE FROM categoria WHERE idCategoria = ?";

    @Override
    public List<Categoria> obtenerTodasLasCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIAS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todas las categorías", e);
        }
        return categorias;
    }

    @Override
    public Categoria obtenerCategoriaPorId(int idCategoria) throws SQLException {
        Categoria categoria = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_BY_ID_SQL)) {
            preparedStatement.setInt(1, idCategoria);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la categoría por ID", e);
        }
        return categoria;
    }

    @Override
    public List<Categoria> obtenerCategoriasPorNombre(String nombre) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIAS_BY_NOMBRE_SQL)) {
            preparedStatement.setString(1, "%" + nombre + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener categorías por nombre", e);
        }
        return categorias;
    }

    @Override
    public void agregarCategoria(Categoria categoria) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORIA_SQL)) {
            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void actualizarCategoria(Categoria categoria) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORIA_SQL)) {
            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.setInt(2, categoria.getIdCategoria());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarCategoria(int idCategoria) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORIA_SQL)) {
            preparedStatement.setInt(1, idCategoria);
            preparedStatement.executeUpdate();
        }
    }
}
