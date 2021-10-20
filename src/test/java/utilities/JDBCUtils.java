package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    /* STATIC METHODS
    .establishConnection();
    .runQuery(String query); -> returns list of maps
    .countRows(String query);
    .closeDatabase();
     */
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


  //  public static void establishConnection() throws SQLException {
        public static void establishConnection(){
            try {
                connection = DriverManager.getConnection(
                        ConfigReader.getProperty("DBUrl"),
                        ConfigReader.getProperty("DBUsername"),
                        ConfigReader.getProperty("DBPassword"));

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch ( SQLException e) {
                System.out.println("Could not establish database connection");
                System.out.println(e.getMessage());
            }


      //  connection = DriverManager.getConnection(ConfigReader.getProperty("DBUrl"),
       //         ConfigReader.getProperty("DBUsername"),
         //       ConfigReader.getProperty("DBPassword"));

    }

    public static List<Map<String, Object>> runQuery(String query) throws SQLException {
        ;
        List<Map<String, Object>> table = new ArrayList<>();

        resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();// capture row with every rotation
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(resultSetMetaData.getColumnName(i)));
                // row.put(Keys,Value()));
            }
            table.add(row);
        }
        return table;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
    }







}