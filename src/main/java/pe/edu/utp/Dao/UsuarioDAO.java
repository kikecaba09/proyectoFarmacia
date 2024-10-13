package pe.edu.utp.Dao;

import pe.edu.utp.models.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    List<Usuario> obtenerTodosLosUsuarios() throws SQLException;
    Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException;
    void actualizarRolUsuario(int idUsuario, String nuevoRol) throws SQLException;
    List<Usuario> generarReporteUsuarios() throws SQLException;
    String generarReportePorUsuario(int idUsuario) throws SQLException;
    void agregarUsuario(Usuario usuario) throws SQLException;
    void actualizarUsuario(Usuario usuario) throws SQLException;
    void eliminarUsuario(int idUsuario) throws SQLException;

    Usuario obtenerUsuarioPorCredenciales(String usuario, String contrasena);
}
