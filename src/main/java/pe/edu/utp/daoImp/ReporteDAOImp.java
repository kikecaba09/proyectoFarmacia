package pe.edu.utp.daoImp;

import pe.edu.utp.dao.ReporteDAO;
import pe.edu.utp.models.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAOImp implements ReporteDAO {

    private Connection connection;

    public ReporteDAOImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Venta> getVentasMensuales(String mes, String anio) {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM venta WHERE MONTH(fecha) = ? AND YEAR(fecha) = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, mes);
            pstmt.setString(2, anio);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setIdUsuario(rs.getInt("idUsuario"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
