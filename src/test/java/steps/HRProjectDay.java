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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HRProjectDay {
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrHomePage = new HRAppHomePage();
    List<String> uiId = new ArrayList<>();
    List<String> uiFirstname = new ArrayList<>();

    @Given("user navigates to HR Application")
    public void user_navigates_to_HR_Application() {

        driver.get(ConfigReader.getProperty("HRAppURL"));

    }

    @When("user logs in with username “Mindtek” and password “MindtekStudent”")
    public void user_logs_in_with_username_Mindtek_and_password_MindtekStudent() {
        hrAppLoginPage.username.sendKeys("Mindtek");
        hrAppLoginPage.password.sendKeys("MindtekStudent");
        hrAppLoginPage.loginButton.click();

    }

    @When("user search for employee with employee id {int}")
    public void user_search_for_employee_with_employee_id(Integer employeeId) throws SQLException {

        String firstName;
        // String lastName;
        for (int i = 0; i < hrHomePage.employeesId.size(); i++) {
            if (hrHomePage.employeesId.get(i).getText().equals(employeeId)) {
                uiId.add(hrHomePage.employeesId.get(i).getText());
               // uiFirstname.add(hrHomePage.firstNames.get(i).getText());
            }

        }
    }

    @Then("user validates that employee data in UI matches with Database data")
    public void user_validates_that_employee_data_in_UI_matches_with_Database_data() throws
            SQLException {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> data = JDBCUtils.runQuery("SELECT * FROM employees where employee_id IN(100,200,206);");
        Assert.assertEquals(uiId.get(0), data.get(0).get("employee_id").toString());
       // Assert.assertEquals(uiFirstname.get(0), data.get(0).get("first_name").toString());
       // Assert.assertEquals(uiId.get(1), data.get(1).get("employee_id").toString());
       // Assert.assertEquals(uiId.get(2), data.get(2).get("employee_id").toString());
        //Assert.assertEquals(data.get(0).get("employee_id").toString(),"202");
        // String expectedResult=hrHomePage.employeeId.getText();
        // Assert.assertEquals(expectedResult ,data.get(0).get("employee_id").toString());

        //  JDBCUtils.closeConnection();
    }


}