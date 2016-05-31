/**
 * Created by Evgeny_Akulenko on 5/5/2016.
 */
public class DriverRegistrator {
    public static void registerDriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }
}
