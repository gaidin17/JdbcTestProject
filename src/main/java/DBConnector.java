import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Created by Evgeny_Akulenko on 4/28/2016.
 */
public class DBConnector {

    private final Logger logger = LoggerFactory.getLogger(DBConnector.class);

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "SOUTHWIND";
    private static final String PASSWORD = "SOUTHWIND";
    private Connection connection;

    public DBConnector() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("INFO: Connection to {} is open", connection);
        } catch (SQLException ex) {
            logger.error("ERROR: No connection to {} ", URL);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("INFO: Connection to {} is closed", URL);
            }
        } catch (SQLException ex) {
            logger.error("ERROR: {}", ex.getErrorCode());
        }
    }

    public Connection getConnection() {
        logger.warn("WARN: getting connection");
        return connection;
    }
}
