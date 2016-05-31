import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Evgeny_Akulenko on 4/28/2016.
 */
public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        DriverRegistrator.registerDriver();
        String query = "SELECT * FROM ORDER_DETAILS";
        DBConnector connector = new DBConnector();
        DBWorker dbWorker = null;
        try {
            logger.info("INFO: Query: {}", query);
            dbWorker = new DBWorker(connector.getConnection(), query);
            logger.info("INFO: Set DBWorker: {}", dbWorker);
            dbWorker.printResultToConsol();
        } catch (SQLException ex) {
            logger.error("Error: {}", ex.getStackTrace());
        } catch (Exception ex) {
            logger.error("Error: {}", ex.getStackTrace());
        } finally {
            logger.warn("Warn: closing connection:");
            try {
                if (dbWorker != null) {
                    dbWorker.closeResultSet();
                    dbWorker.closeStatement();
                }
            } catch (SQLException ex) {
                logger.error("Error: {}", ex.getStackTrace());

            } catch (Exception ex) {
                logger.error("Error: {}", ex.getStackTrace());
            }
            connector.closeConnection();
        }
    }
}
