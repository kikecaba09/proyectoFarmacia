package pe.edu.utp.ejecucion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        }
    }
}

