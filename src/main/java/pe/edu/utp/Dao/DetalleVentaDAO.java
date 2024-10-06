package pe.edu.utp.Dao;

import pe.edu.utp.models.DetalleVenta;
import java.sql.SQLException;
import java.util.List;

public interface DetalleVentaDAO {
    List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) throws SQLException;
    double calcularTotalVenta(int idVenta) throws SQLException;
    List<DetalleVenta> obtenerDetallesPorMedicamento(int idMedicamento) throws SQLException;
}
