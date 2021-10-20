package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppLoginPage;
import pages.HRCreateEmployeePage;
import pages.HRAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class HRAppSteps {
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrHomePage = new HRAppHomePage();
    HRCreateEmployeePage hrCreateEmployeePage = new HRCreateEmployeePage();
    int numberOfEmployee;
    Map<String, Object> data;

    @Given("user navigates to Orange HRM application")
    public void user_navigates_to_Orange_HRM_application() {
        driver.get(ConfigReader.getProperty("HRAppURL"));
    }

    @When("user logs in with username {string} password {string}")
    public void user_logs_in_with_username_password(String username, String password)  {

        hrAppLoginPage.username.sendKeys(username);
        hrAppLoginPage.password.sendKeys(password);
        hrAppLoginPage.loginButton.click();
    }

    @When("user clicks on Create New Employee button")
    public void user_clicks_on_Create_New_Employee_button() throws InterruptedException {
        Thread.sleep(5000);
        numberOfEmployee = driver.findElements(By.xpath("//tbody")).size();
        hrHomePage.employeeCreationButton.click();


    }

    @When("user creates employee with data")
    public void user_creates_employee_with_data(DataTable dataTable)  {
        // numberOfEmployee=driver.findElements(By.xpath("//tbody")).size();
        //Converting data table to Map
        data = dataTable.asMap(String.class, Object.class);
        if(data.containsKey("First name"))
            hrCreateEmployeePage.firstName.sendKeys(data.get("First name").toString());
        if(data.containsKey("Last name"))
        hrCreateEmployeePage.lastName.sendKeys(data.get("Last name").toString());

        BrowserUtils.selectByText(hrCreateEmployeePage.DepartmentDropdown, data.get("Department").toString());
        BrowserUtils.selectByText(hrCreateEmployeePage.jobTitleDropdown, data.get("Job title").toString());
        hrCreateEmployeePage.salaryBox.clear();
        hrCreateEmployeePage.salaryBox.sendKeys(data.get("Salary").toString());
        hrCreateEmployeePage.saveButton.click();
    }

    @Then("user validates that employee is in list of employee")
    public void user_validates_that_employee_is_in_list_of_employee() throws InterruptedException {
        Thread.sleep(5000);
        driver.navigate().refresh();
        numberOfEmployee++;
        //xpath  //tbody[136]//td
        List<WebElement> newEmployeeRow = driver.findElements(By.xpath("//tbody[" + numberOfEmployee + "]//td"));
        // 1-> Employee ID
        //2->firstName
        //3-> lastName
        //4-> department
        Assert.assertEquals(data.get("First name").toString(), newEmployeeRow.get(1).getText());
        Assert.assertEquals(data.get("Last name").toString(), newEmployeeRow.get(2).getText());
        Assert.assertEquals(data.get("Department").toString(), newEmployeeRow.get(3).getText());
    }


    @Then("user validates error message in HR App {string}")
    public void userValidatesErrorMessageInHRApp(String errorMessage) {

    }
}