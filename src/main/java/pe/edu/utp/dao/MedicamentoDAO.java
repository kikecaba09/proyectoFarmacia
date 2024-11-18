package pe.edu.utp.dao;

import pe.edu.utp.models.Medicamento;
import java.util.List;

public interface MedicamentoDAO {

    List<Medicamento> obtenerTodos();
    boolean agregarMedicamento(Medicamento medicamento);
    boolean editarMedicamento(Medicamento medicamento);
    boolean eliminarMedicamento(int idMedicamento);
    List<Medicamento> getAllMedicamentos();
    List<Medicamento> getMedicamentosConStockMinimo(int stockMinimo);
    void actualizarStock(int idMedicamento, int nuevoStock);
}
