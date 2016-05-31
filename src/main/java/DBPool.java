import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Evgeny_Akulenko on 5/4/2016.
 */
public class DBPool {
    public static void main(String[] args) {
        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
        try {
            pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            pds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            pds.setUser("SOUTHWIND");
            pds.setPassword("SOUTHWIND");
            pds.setInitialPoolSize(5);
            Connection conn = pds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
