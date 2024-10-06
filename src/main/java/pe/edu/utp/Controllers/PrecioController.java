package pe.edu.utp.Controllers;

import pe.edu.utp.Dao.PrecioDAO;
import pe.edu.utp.daoImp.PrecioDAOImp;
import pe.edu.utp.models.Precio;
import java.sql.SQLException;
import java.sql.Date;

public class PrecioController {
    private PrecioDAO precioDAO;

    public PrecioController() {
        this.precioDAO = new PrecioDAOImp();
    }

    // Método para obtener el precio actual de un medicamento
    public Precio obtenerPrecioActualPorMedicamento(int idMedicamento) {
        try {
            return precioDAO.obtenerPrecioActualPorMedicamento(idMedicamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para actualizar el precio de un medicamento
    public void actualizarPrecio(int idMedicamento, double nuevoPrecio, Date fechaActualizacion) {
        Precio precio = new Precio();
        precio.setIdMedicamento(idMedicamento);
        precio.setPrecio(nuevoPrecio);
        precio.setFechaActualizacion(fechaActualizacion);

        try {
            precioDAO.actualizarPrecio(precio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
