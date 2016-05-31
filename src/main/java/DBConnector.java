import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Evgeny_Akulenko on 4/28/2016.
 */
public class DBConnector {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "SOUTHWIND";
    private static final String PASSWORD = "SOUTHWIND";
    private Connection connection;

    public DBConnector() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to jdbc:oracle:thin:@localhost:1521:XE is open");
        } catch (SQLException ex) {
            System.out.println("Not connected!");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to " + URL + " is closed.");
            }
        } catch (SQLException ex) {

        }
    }

    public Connection getConnection() {
        return connection;
    }
}
