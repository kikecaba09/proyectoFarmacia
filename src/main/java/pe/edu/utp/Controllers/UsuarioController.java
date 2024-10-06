package pe.edu.utp.Controllers;

import pe.edu.utp.Dao.UsuarioDAO;
import pe.edu.utp.daoImp.UsuarioDAOImp;
import pe.edu.utp.models.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAOImp();
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        try {
            return usuarioDAO.obtenerTodosLosUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        try {
            return usuarioDAO.obtenerUsuarioPorId(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarRolUsuario(int idUsuario, String nuevoRol) {
        try {
            usuarioDAO.actualizarRolUsuario(idUsuario, nuevoRol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generarReportePorUsuario(int idUsuario) {
        try {
            return usuarioDAO.generarReportePorUsuario(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> generarReporteUsuarios() {
        try {
            return usuarioDAO.generarReporteUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.agregarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        usuarioDAO.eliminarUsuario(idUsuario);
    }
}
