package pe.edu.utp.controllers;

import pe.edu.utp.dao.CategoriaDAO;
import pe.edu.utp.daoImp.CategoriaDAOImp;
import pe.edu.utp.models.Categoria;
import java.sql.SQLException;
import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        this.categoriaDAO = new CategoriaDAOImp();
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        try {
            return categoriaDAO.obtenerTodasLasCategorias();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Categoria obtenerCategoriaPorId(int idCategoria) {
        try {
            return categoriaDAO.obtenerCategoriaPorId(idCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categoria> obtenerCategoriasPorNombre(String nombre) {
        try {
            return categoriaDAO.obtenerCategoriasPorNombre(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void agregarCategoria(Categoria categoria) throws SQLException {
        categoriaDAO.agregarCategoria(categoria);
    }

    public void actualizarCategoria(Categoria categoria) throws SQLException {
        categoriaDAO.actualizarCategoria(categoria);
    }

    public void eliminarCategoria(int idCategoria) throws SQLException {
        categoriaDAO.eliminarCategoria(idCategoria);
    }
}
