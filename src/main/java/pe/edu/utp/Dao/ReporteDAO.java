package pe.edu.utp.Dao;

import pe.edu.utp.models.Reporte;
import java.sql.SQLException;
import java.util.List;

public interface ReporteDAO {

    List<Reporte> generarReporteVentas() throws SQLException;  // Generar reporte de ventas
    List<Reporte> generarReporteInventario() throws SQLException;  // Generar reporte de inventario
    List<Reporte> generarReporteAlertas() throws SQLException;  // Generar reporte de alertas
}
