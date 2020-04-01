import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String HOST;
    private final String USERNAME;
    private final String PASSWORD;

    public DatabaseConnectionManager() {
        String host = "";
        String username = "";
        String password = "";

        try {
            Properties poop = new Properties();
            String propFileName = "application.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            poop.load(inputStream);
            host = poop.getProperty("host");
            username = poop.getProperty("username");
            password = poop.getProperty("password");
        } catch (Exception ignored){
        }

        this.HOST = host;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    }
}
