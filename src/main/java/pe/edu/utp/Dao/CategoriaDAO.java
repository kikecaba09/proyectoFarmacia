package pe.edu.utp.Dao;

import pe.edu.utp.models.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO {
    List<Categoria> obtenerTodasLasCategorias() throws SQLException;
    Categoria obtenerCategoriaPorId(int idCategoria) throws SQLException;
    List<Categoria> obtenerCategoriasPorNombre(String nombre) throws SQLException;
    void agregarCategoria(Categoria categoria) throws SQLException;
    void actualizarCategoria(Categoria categoria) throws SQLException;
    void eliminarCategoria(int idCategoria) throws SQLException;
}
