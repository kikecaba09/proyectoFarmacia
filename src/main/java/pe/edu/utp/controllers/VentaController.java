package pe.edu.utp.controllers;

import pe.edu.utp.dao.VentaDAO;
import pe.edu.utp.daoImp.VentaDAOImp;
import pe.edu.utp.models.Venta;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAOImp();
    }

    public List<Venta> obtenerTodasLasVentas() {
        try {
            return ventaDAO.obtenerTodasLasVentas();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Venta obtenerVentaPorId(int idVenta) {
        try {
            return ventaDAO.obtenerVentaPorId(idVenta);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double calcularTotalVentasPorUsuario(int idUsuario) {
        try {
            return ventaDAO.calcularTotalVentasPorUsuario(idUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Venta> obtenerVentasPorFecha(Date fechaInicio, Date fechaFin) {
        try {
            return ventaDAO.obtenerVentasPorFecha(fechaInicio, fechaFin);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
