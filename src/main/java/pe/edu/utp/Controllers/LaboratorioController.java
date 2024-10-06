package pe.edu.utp.Controllers;

import pe.edu.utp.Dao.LaboratorioDAO;
import pe.edu.utp.daoImp.LaboratorioDAOImp;
import pe.edu.utp.models.Laboratorio;
import java.sql.SQLException;
import java.util.List;

public class LaboratorioController {
    private LaboratorioDAO laboratorioDAO;

    public LaboratorioController() {
        this.laboratorioDAO = new LaboratorioDAOImp();
    }

    public List<Laboratorio> obtenerTodosLosLaboratorios() {
        try {
            return laboratorioDAO.obtenerTodosLosLaboratorios();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Laboratorio obtenerLaboratorioPorId(int idLaboratorio) {
        try {
            return laboratorioDAO.obtenerLaboratorioPorId(idLaboratorio);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarDatosLaboratorio(int idLaboratorio, String nombre, String direccion, String telefono) {
        try {
            laboratorioDAO.actualizarDatosLaboratorio(idLaboratorio, nombre, direccion, telefono);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generarReportePorLaboratorio(int idLaboratorio) {
        try {
            return laboratorioDAO.generarReportePorLaboratorio(idLaboratorio);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void agregarLaboratorio(Laboratorio laboratorio) throws SQLException {
        laboratorioDAO.agregarLaboratorio(laboratorio);
    }

    public void eliminarLaboratorio(int idLaboratorio) throws SQLException {
        laboratorioDAO.eliminarLaboratorio(idLaboratorio);
    }
}
