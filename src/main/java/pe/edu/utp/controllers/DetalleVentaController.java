package pe.edu.utp.controllers;


import pe.edu.utp.dao.DetalleVentaDAO;
import pe.edu.utp.daoImp.DetalleVentaDAOImp;
import pe.edu.utp.models.DetalleVenta;
import java.sql.SQLException;
import java.util.List;

public class DetalleVentaController {

    private final DetalleVentaDAO detalleVentaDAO;

    public DetalleVentaController() {
        this.detalleVentaDAO = new DetalleVentaDAOImp();
    }

    public List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) {
        try {
            return detalleVentaDAO.obtenerDetallesPorVenta(idVenta);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double calcularTotalVenta(int idVenta) {
        try {
            return detalleVentaDAO.calcularTotalVenta(idVenta);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public List<DetalleVenta> obtenerDetallesPorMedicamento(int idMedicamento) {
        try {
            return detalleVentaDAO.obtenerDetallesPorMedicamento(idMedicamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
