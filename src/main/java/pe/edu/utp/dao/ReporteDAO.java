package pe.edu.utp.dao;

import pe.edu.utp.models.Reporte;
import pe.edu.utp.models.Venta;
import java.sql.SQLException;
import java.util.List;

public interface ReporteDAO {

    List<Venta> getVentasMensuales(String mes, String anio);
}
