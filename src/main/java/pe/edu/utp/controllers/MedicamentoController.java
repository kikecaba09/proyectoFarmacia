package pe.edu.utp.controllers;

import pe.edu.utp.dao.MedicamentoDAO;
import pe.edu.utp.daoImp.MedicamentoDAOImp;
import pe.edu.utp.models.Medicamento;
import java.sql.SQLException;
import java.util.List;

public class MedicamentoController {
    private MedicamentoDAO medicamentoDAO;

    public MedicamentoController() {
        this.medicamentoDAO = new MedicamentoDAOImp();
    }

    public List<Medicamento> obtenerMedicamentosPorCategoria(int idCategoria) {
        try {
            return medicamentoDAO.obtenerMedicamentosPorCategoria(idCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Medicamento obtenerMedicamentoPorId(int idMedicamento) {
        try {
            return medicamentoDAO.obtenerMedicamentoPorId(idMedicamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarPrecioMedicamento(int idMedicamento, double nuevoPrecio) {
        try {
            medicamentoDAO.actualizarPrecioMedicamento(idMedicamento, nuevoPrecio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medicamento> obtenerMedicamentosProximosAVencer() {
        try {
            return medicamentoDAO.obtenerMedicamentosProximosAVencer();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Medicamento> obtenerMedicamentosPorLaboratorio(int idLaboratorio) { // Nuevo método
        try {
            return medicamentoDAO.obtenerMedicamentosPorLaboratorio(idLaboratorio);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void agregarMedicamento(Medicamento medicamento) throws SQLException {
        medicamentoDAO.agregarMedicamento(medicamento);
    }

    public void actualizarMedicamento(Medicamento medicamento) throws SQLException {
        medicamentoDAO.actualizarMedicamento(medicamento);
    }

    public void eliminarMedicamento(int idMedicamento) throws SQLException {
        medicamentoDAO.eliminarMedicamento(idMedicamento);
    }
}
