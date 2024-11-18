package pe.edu.utp.controllers;

import pe.edu.utp.dao.MedicamentoDAO;
import pe.edu.utp.daoImp.MedicamentoDAOImp;
import pe.edu.utp.models.Medicamento;
import java.sql.Connection;
import java.util.List;

public class MedicamentoController {

    private MedicamentoDAO medicamentoDAO;

    public MedicamentoController(Connection connection) {
        this.medicamentoDAO = new MedicamentoDAOImp();
    }

    public List<Medicamento> obtenerTodosLosMedicamentos() {
        List<Medicamento> medicamentos = medicamentoDAO.obtenerTodos();
        System.out.println("Medicamentos from DAO: " + medicamentos.size());
        return medicamentos;
    }


    public boolean agregarMedicamento(Medicamento medicamento) {
        return medicamentoDAO.agregarMedicamento(medicamento);
    }

    public boolean editarMedicamento(Medicamento medicamento) {
        return medicamentoDAO.editarMedicamento(medicamento);
    }

    public boolean eliminarMedicamento(int idMedicamento) {
        return medicamentoDAO.eliminarMedicamento(idMedicamento);
    }

    public List<Medicamento> obtenerInventarioCompleto() {
        return medicamentoDAO.getAllMedicamentos();
    }

    public List<Medicamento> verificarStockMinimo(int stockMinimo) {
        return medicamentoDAO.getMedicamentosConStockMinimo(stockMinimo);
    }

    public void actualizarStock(int idMedicamento, int nuevoStock) {
        medicamentoDAO.actualizarStock(idMedicamento, nuevoStock);
    }
}
