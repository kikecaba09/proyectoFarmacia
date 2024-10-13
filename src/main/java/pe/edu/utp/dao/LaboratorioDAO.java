package pe.edu.utp.dao;

import pe.edu.utp.models.Laboratorio;

import java.sql.SQLException;
import java.util.List;

public interface LaboratorioDAO {
    List<Laboratorio> obtenerTodosLosLaboratorios() throws SQLException;
    Laboratorio obtenerLaboratorioPorId(int idLaboratorio) throws SQLException;
    void actualizarDatosLaboratorio(int idLaboratorio, String nombre, String direccion, String telefono) throws SQLException;
    String generarReportePorLaboratorio(int idLaboratorio) throws SQLException;
    void agregarLaboratorio(Laboratorio laboratorio) throws SQLException;
    void eliminarLaboratorio(int idLaboratorio) throws SQLException;// Método para reporte por laboratorio
}
