package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRMAppHomePage;
import pages.OrangeHRMAppLoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class OrangeHRMAppMyLeaveModule {
    WebDriver driver= Driver.getDriver();
    OrangeHRMAppLoginPage orangeHRMAppLoginPage=new OrangeHRMAppLoginPage();
    OrangeHRMAppHomePage orangeHRMAppHomePage= new OrangeHRMAppHomePage();

    @Given("user navigates to OrangeHRM application")
    public void user_navigates_to_OrangeHRM_application() {
     driver.get(ConfigReader.getProperty("OrangeHRMURL"));

    }

    @When("user provides username {string} and password {string} and clicks login")
    public void user_provides_username_and_password_and_clicks_login(String username, String password) {
     orangeHRMAppLoginPage.username.sendKeys("Admin");
     orangeHRMAppLoginPage.password.sendKeys("admin123");
     orangeHRMAppLoginPage.loginButton.click();

    }

    @When("user click on My Leave module")
    public void user_click_on_My_Leave_module()  {
        orangeHRMAppHomePage.myLeaveButton.click();
    }

    @When("user provides dates {string} and {string} and click on search button")
    public void user_provides_dates_and_and_click_on_search_button(String dateFrom, String dateTo) {
       // orangeHRMAppHomePage.dataPicker.click();
        orangeHRMAppHomePage.fromInput.clear();
        orangeHRMAppHomePage.fromInput.sendKeys("2020-12-22"+Keys.ENTER);
        orangeHRMAppHomePage.toInput.clear();
        orangeHRMAppHomePage.toInput.sendKeys("2020-12-24"+Keys.ENTER);
        orangeHRMAppHomePage.searchBox.click();

    }

    @Then("user validates that leave with this date exists")
    public void user_validates_that_leave_with_this_date_exists() {
        String expectedMessage=orangeHRMAppHomePage.datesOutput.getText();
      //  System.out.println(expectedMessage);
        String actualMessage="2020-12-22 to 2020-12-24";
        Assert.assertEquals(expectedMessage,actualMessage);

    }

}
