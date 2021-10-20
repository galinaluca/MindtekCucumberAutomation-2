package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.MyStoreAppHomePage;
import pages.MyStoreAppLoginPage;
import pages.MyStoreAppWishlistPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class MyStoreAppWishlistFunctionality {
    WebDriver driver= Driver.getDriver();
    MyStoreAppLoginPage myStoreAppLoginPage=new MyStoreAppLoginPage();
    MyStoreAppHomePage myStoreAppHomePage=new MyStoreAppHomePage();
    MyStoreAppWishlistPage myStoreAppWishlistPage=new MyStoreAppWishlistPage();
    @Given("user navigates to MyStore Application")
    public void user_navigates_to_MyStore_Application() {
        driver.get(ConfigReader.getProperty("MyStoreAppURL"));
    }

    @When("user clicks on sign in button")
    public void user_clicks_on_sign_in_button() {
    myStoreAppLoginPage.loginButton.click();
    }

    @When("user provides logs in with Email address {string} and password {string}")
    public void user_provides_logs_in_with_Email_address_and_password(String email, String password) {
      myStoreAppLoginPage.emailAddress.sendKeys(email);
      myStoreAppLoginPage.password.sendKeys(password);
      myStoreAppLoginPage.signInButton.click();
    }

    @When("user clicks on MyWishlist icon")
    public void user_clicks_on_MyWishlist_icon() throws InterruptedException {
        Thread.sleep(3000);
        myStoreAppHomePage.myWishlistButton.click();
    }

    @When("user creates a wishlist name Dresses and clicks save button")
    public void user_creates_a_wishlist_name_Dresses_and_clicks_save_button() throws InterruptedException {
        Thread.sleep(3000);
      myStoreAppWishlistPage.nameIcon.sendKeys("Dresses"+Keys.ENTER);
    myStoreAppWishlistPage.saveButton.click();
    }

    @Then("user validate that wishlist is created")
    public void user_validate_that_wishlist_is_created() {
   String actualMessage=myStoreAppWishlistPage.newWishlist.getText();
    if(actualMessage.contains("Dresses")) {
        System.out.println(actualMessage);
    }
        String expectedMessage = "Dresses";

    Assert.assertTrue(expectedMessage.contains(actualMessage));
    }



}
