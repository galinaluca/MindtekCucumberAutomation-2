package utilities;

import java.sql.*;
import java.util.*;

public class Psql {
    public static void main(String[] args) throws SQLException {

        String url ="jdbc:postgresql://localhost/HR";
        String username ="postgres";
        String password ="palalogaL73";
        String query="SELECT * FROM employees  LIMIT 3;";
        List<Map <String,Object>> firstData=new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        ResultSetMetaData resultSetMetaData;

        connection= DriverManager.getConnection(url,username, password);

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        resultSet=statement.executeQuery(query);

       // resultSet.next();

        resultSetMetaData=resultSet.getMetaData();
      //  System.out.println(resultSetMetaData.getColumnCount());
        //System.out.println(resultSetMetaData.getColumnName(1));
      //  System.out.println(resultSetMetaData.getTableName(1));

        while(resultSet.next()){
            Map<String,Object> row=new HashMap<>();// capture row with every rotation
            for(int i =1; i<=resultSetMetaData.getColumnCount();i++){
                row.put(resultSetMetaData.getColumnName(i),resultSet.getObject(resultSetMetaData.getColumnName(i)));
                       // row.put(Keys,Value()));
            }
            firstData.add(row);
        }

        System.out.println(firstData);//this just returns the list of Maps
        System.out.println(firstData.get(0));// this returns a Map at first index
        System.out.println(firstData.get(0).get("first_name"));//this is the value of the Map key provided
        System.out.println(firstData.get(0).get("employee_id"));
        connection.close();






    }
}