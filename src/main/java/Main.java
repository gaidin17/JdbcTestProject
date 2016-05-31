import java.sql.SQLException;

/**
 * Created by Evgeny_Akulenko on 4/28/2016.
 */
public class Main {
    public static void main(String[] args) {
        DriverRegistrator.registerDriver();
        String query = "SELECT * FROM ORDER_DETAILS";
        DBConnector connector = new DBConnector();
        DBWorker dbWorker = null;
        try {
            dbWorker = new DBWorker(connector.getConnection(), query);
            dbWorker.printResultToConsol();

        } catch (SQLException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (dbWorker != null) {
                    dbWorker.closeResultSet();
                    dbWorker.closeStatement();
                }
            } catch (SQLException ex) {

            } catch (Exception ex) {

            }
            connector.closeConnection();
        }
    }
}
