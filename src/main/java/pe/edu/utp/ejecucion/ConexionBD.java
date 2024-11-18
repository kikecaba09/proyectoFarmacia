package pe.edu.utp.ejecucion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.util.Properties;

public class ConexionBD {

    private static final String PROPERTIES_FILE = "/config.properties";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = ConexionBD.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Properties file not found: " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error loading properties file", e);
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);

            // Verificar si la conexión es válida
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("La conexión a la base de datos falló.");
            }

            // Realizar una consulta simple de prueba
            try (Statement stmt = connection.createStatement()) {
                stmt.executeQuery("SELECT 1"); // Consulta de prueba
                System.out.println("Consulta de prueba exitosa.");
            } catch (SQLException e) {
                System.out.println("Error al realizar la consulta de prueba: " + e.getMessage());
            }

            return connection;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        }
    }
}
