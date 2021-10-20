package steps;

import org.junit.Assert;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCUtilsTest {
    public static void main(String[] args) throws SQLException {
        JDBCUtils.establishConnection();

        List<Map<String,Object>> dataInfo=JDBCUtils.runQuery("SELECT *FROM employees;");
      // dataInfo.get(1);
      //  System.out.println(dataInfo);
        Assert.assertEquals(dataInfo.get(1).get("first_name").toString(),"Neena");
        Assert.assertEquals(dataInfo.get(1).get("last_name").toString(),"Kochhar");

        JDBCUtils.closeConnection();
    }

}
