package pe.edu.utp.models;

public class Laboratorio {

    private int idLaboratorio;
    private String nombre;
    private String direccion;
    private String telefono;

    public Laboratorio(int idLaboratorio, String nombre, String direccion, String telefono) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Laboratorio() {
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "idLaboratorio=" + idLaboratorio +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
