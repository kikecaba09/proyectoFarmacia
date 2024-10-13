package pe.edu.utp.daoImp;

import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO {

    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM usuario";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM usuario WHERE id_usuario = ?";
    private static final String UPDATE_USER_ROLE_SQL = "UPDATE usuario SET rol = ? WHERE id_usuario = ?";
    private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, apellido, edad, email, usuario, contrasena, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USUARIO_SQL = "UPDATE usuario SET nombre = ?, apellido = ?, edad = ?, email = ?, usuario = ?, contrasena = ?, rol = ? WHERE idUsuario = ?";
    private static final String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE idUsuario = ?";

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener todos los usuarios", e);
        }
        return usuarios;
    }

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        Usuario usuario = null;
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario por ID", e);
        }
        return usuario;
    }

    @Override
    public void actualizarRolUsuario(int idUsuario, String nuevoRol) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE_SQL)) {
            preparedStatement.setString(1, nuevoRol);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el rol del usuario", e);
        }
    }

    @Override
    public List<Usuario> generarReporteUsuarios() throws SQLException {
        return obtenerTodosLosUsuarios(); // Puedes modificar esto para adaptarlo a tu reporte
    }

    @Override
    public String generarReportePorUsuario(int idUsuario) throws SQLException {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new SQLException("Usuario no encontrado");
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de Usuario:\n");
        reporte.append("ID: ").append(usuario.getIdUsuario()).append("\n");
        reporte.append("Nombre: ").append(usuario.getNombre()).append("\n");
        reporte.append("Apellido: ").append(usuario.getApellido()).append("\n");
        reporte.append("Edad: ").append(usuario.getEdad()).append("\n");
        reporte.append("Email: ").append(usuario.getEmail()).append("\n");
        reporte.append("Usuario: ").append(usuario.getUsuario()).append("\n");
        reporte.append("Rol: ").append(usuario.getRol()).append("\n");

        return reporte.toString();
    }

    @Override
    public void agregarUsuario(Usuario usuario) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setInt(3, usuario.getEdad());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getUsuario());
            preparedStatement.setString(6, usuario.getContrasena());
            preparedStatement.setString(7, usuario.getRol());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setInt(3, usuario.getEdad());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getUsuario());
            preparedStatement.setString(6, usuario.getContrasena());
            preparedStatement.setString(7, usuario.getRol());
            preparedStatement.setInt(8, usuario.getIdUsuario());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminarUsuario(int idUsuario) throws SQLException {
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(String usuario, String contrasena) {
        return null;
    }
}
