
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Evgeny_Akulenko on 4/28/2016.
 */
public class DBWorker {
    private String query;
    private Connection connection;
    private ArrayList<ArrayList<String>> table;
    private ArrayList<String> columnNames = new ArrayList<>();
    private ResultSet resultSet;
    private Statement statement;
    private int columnsQuntity;
    private ArrayList<Integer> collumnsMaxLengths;
    private String detString = " | ";


    public DBWorker(Connection connection, String query) throws SQLException {
        this.query = query;
        this.connection = connection;
        if (connection != null) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            setColumnQuantity();
            setColumnNames();
            setTable();
            setCollumnsMaxLengths();
        }
    }

    private void setColumnNames() throws SQLException {
        ResultSetMetaData data = resultSet.getMetaData();
        for (int i = 1; i <= columnsQuntity; i++) {
            columnNames.add(data.getColumnName(i));
        }
    }


    private void setTable() throws SQLException {
        table = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<String> resultQueryRows = new ArrayList<>();
            for (int i = 1; i <= columnsQuntity; i++) {
                String string;
                try {

                    string = resultSet.getString(i);
                } catch (SQLException ex) {
                    string = "BLOB DATA";
                }
                resultQueryRows.add(string);
            }
            table.add(resultQueryRows);
        }
    }


    private String addtoMaxString(String string, int max) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        while (stringBuilder.length() < max) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public void closeStatement() throws SQLException {
        this.statement.close();
    }

    public void closeResultSet() throws SQLException {
        this.resultSet.close();
    }


    private void setColumnQuantity() throws SQLException {
        ResultSetMetaData data = resultSet.getMetaData();
        this.columnsQuntity = data.getColumnCount();
    }


    private void setCollumnsMaxLengths() {
        int rowlength = table.get(0).size();
        collumnsMaxLengths = new ArrayList<>();

        for (int j = 0; j < rowlength; j++) {
            int max = 0;
            for (int i = 0; i < table.size(); i++) {
                int length;
                if (table.get(i).get(j) == null) {
                    length = 0;
                } else {
                    length = table.get(i).get(j).length();
                }
                if (length > max) {
                    max = length;
                }
            }
            if (columnNames.get(j).length() > max) {
                max = columnNames.get(j).length();
            }
            collumnsMaxLengths.add(max);
        }
    }

    public void printResultToConsol() {
        for (int i = 0; i < columnNames.size(); i++) {
            String string = columnNames.get(i);
            System.out.print(addtoMaxString(string, collumnsMaxLengths.get(i)) + detString);
        }
        int sum = 0;
        for (int size : collumnsMaxLengths) {
            sum = sum + size;
        }
        System.out.println();
        sum = sum + detString.length() * table.get(0).size();
        for (int i = 0; i < sum; i++) {
            System.out.print("-");
        }
        System.out.println();

        int rowlength = table.get(0).size();

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < rowlength; j++) {
                String string = table.get(i).get(j);
                System.out.print(addtoMaxString(string, collumnsMaxLengths.get(j)) + detString);
            }
            System.out.println();
        }
    }
}
