package pe.edu.utp.dao;

import pe.edu.utp.models.Precio;
import java.sql.SQLException;

public interface PrecioDAO {

    Precio obtenerPrecioActualPorMedicamento(int idMedicamento) throws SQLException;  // Obtener el precio actual de un medicamento
    void actualizarPrecio(Precio precio) throws SQLException;  // Actualizar el precio de un medicamento
}
