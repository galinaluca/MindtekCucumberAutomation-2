package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HRAppLoginPage;
import pages.HRAppHomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HRAppTestSteps {
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage=new HRAppLoginPage();
    HRAppHomePage hrHomePage=new HRAppHomePage();

    @Given("new employee created")
    public void new_employee_created() {
       // System.out.println("New employee Neena Kochhar");
        driver.get(ConfigReader.getProperty("HRAppURL"));

        hrAppLoginPage.username.sendKeys("Mindtek");
        hrAppLoginPage.password.sendKeys("MindtekStudent");
        hrAppLoginPage.loginButton.click();

    }

    @When("user accesses the database")
    public void user_accesses_the_database() {
        JDBCUtils.establishConnection();
    }

    @Then("user validates new employee")
    public void user_validates_new_employee() throws SQLException {

        List<Map<String,Object>> data =JDBCUtils.runQuery("SELECT * FROM employees WHERE employee_id=101;");
       // Assert.assertEquals(data.get(0).get("employee_id").toString(),"101");
        String expectedResult=hrHomePage.employeeId.getText();
        Assert.assertEquals(expectedResult ,data.get(0).get("employee_id").toString());

        JDBCUtils.closeConnection();
    }



}
