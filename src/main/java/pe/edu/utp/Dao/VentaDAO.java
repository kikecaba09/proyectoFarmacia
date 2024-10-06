package pe.edu.utp.Dao;

import pe.edu.utp.models.Venta;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface VentaDAO {
    List<Venta> obtenerTodasLasVentas() throws SQLException;
    Venta obtenerVentaPorId(int idVenta) throws SQLException;
    double calcularTotalVentasPorUsuario(int idUsuario) throws SQLException; // Método para calcular total de ventas por usuario
    List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin) throws SQLException; // Método para obtener ventas en un rango de fechas
}
