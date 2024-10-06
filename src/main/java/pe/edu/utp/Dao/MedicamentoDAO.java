package pe.edu.utp.Dao;

import pe.edu.utp.models.Medicamento;
import java.sql.SQLException;
import java.util.List;

public interface MedicamentoDAO {

    Medicamento obtenerMedicamentoPorId(int idMedicamento) throws SQLException;
    void actualizarPrecioMedicamento(int idMedicamento, double nuevoPrecio) throws SQLException;
    List<Medicamento> obtenerMedicamentosProximosAVencer() throws SQLException;
    List<Medicamento> obtenerMedicamentosPorCategoria(int idCategoria) throws SQLException;
    List<Medicamento> obtenerMedicamentosPorLaboratorio(int idLaboratorio) throws SQLException;
    void agregarMedicamento(Medicamento medicamento) throws SQLException;
    void actualizarMedicamento(Medicamento medicamento) throws SQLException;
    void eliminarMedicamento(int idMedicamento) throws SQLException;
}
