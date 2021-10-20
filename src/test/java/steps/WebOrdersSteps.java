package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.WebOrderOrdersPage;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {
     WebDriver driver= Driver.getDriver();
     WebOrdersLoginPage webOrdersLoginPage=new WebOrdersLoginPage();
     WebOrdersHomePage webOrdersHomePage=new WebOrdersHomePage();
     WebOrderOrdersPage webOrderOrdersPage=new WebOrderOrdersPage();
     int numberOfRows;
    List<Map<String,Object>> data;
    @Given("user navigates to weborders application")
    public void user_navigates_to_weborders_application() {
        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string} and clicks on login")
    public void user_provides_username_and_password_and_clicks_on_login(String username, String password) {
    webOrdersLoginPage.username.sendKeys(username);
    webOrdersLoginPage.password.sendKeys(password);
    webOrdersLoginPage.loginButton.click();
    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
    String actualTitle=driver.getTitle();
    String expectedTitle="Web Orders";
    Assert.assertEquals(expectedTitle,actualTitle);
    driver.quit();

    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
    String actualErrorMessage=webOrdersLoginPage.errorMessage.getText();
    Assert.assertEquals(errorMessage,actualErrorMessage);
    driver.quit();
    }

    @When("user selects any order from View All Orders")
    public void user_selects_any_order_from_View_All_Orders() throws InterruptedException {
        Thread.sleep(3000);
        webOrdersHomePage.viewAllOrders.click();
        webOrdersHomePage.selectOrderCheckBox.click();

    }

    @When("user deletes selected order")
    public void user_deletes_selected_order() throws InterruptedException {
        Thread.sleep(3000);
        webOrdersHomePage.deleteSelectedBox.click();
    }

    @Then("user validates that order is deleted")
    public void user_validates_that_order_is_deleted() {

    }


    @And("user clicks on Order module")
    public void userClicksOnOrderModule() {
        webOrdersHomePage.orderModule.click();
    }

    @And("user selects {string} product with {int} quantity")
    public void userSelectsProductWithQuantity(String product, int quantity) {
        BrowserUtils.selectByValue(webOrderOrdersPage.productDropdown,product);
       webOrderOrdersPage.quantityBox.sendKeys(Keys.BACK_SPACE);
       webOrderOrdersPage.quantityBox.sendKeys(quantity+""+Keys.ENTER);

    }

    @Then("user validates total is calculated correctly for quantity {int}")
    public void userValidatesTotalIsCalculatedCorrectlyForQuantity(int quantity)  {
        String pricePerUnit=webOrderOrdersPage.pricePerUnit.getAttribute("value");
        System.out.println("Price per unit is: "+pricePerUnit);

        String discountAmount=webOrderOrdersPage.discountBox.getAttribute("value");
        int discountAmountInt=Integer.parseInt(discountAmount);
        int expectedTotal;
        if(discountAmountInt==0){
           expectedTotal=quantity* Integer.parseInt(pricePerUnit);

        }else{
            expectedTotal=quantity*Integer.parseInt(pricePerUnit);
            //1000 -> 8%--->1000-1000*8/100
            expectedTotal=expectedTotal-expectedTotal*discountAmountInt/100;
        }
        String actualTotalStr=webOrderOrdersPage.total.getAttribute("value");
        int actualTotal=Integer.parseInt(actualTotalStr);
        Assert.assertEquals(expectedTotal,actualTotal);

    }
    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        // convert dataTable to list of maps
        data= dataTable.asMaps(String.class,Object.class);
       // System.out.println(data.get(0).get("name"));
       // System.out.println(data.get(2).get("quantity"));
       // System.out.println(data.get(3).get("expire date"));
       // System.out.println(data.get(1).get("order"));
        BrowserUtils.selectByValue(webOrderOrdersPage.productDropdown,data.get(0).get("order").toString());
        webOrderOrdersPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrderOrdersPage.quantityBox.sendKeys(data.get(0).get("quantity").toString());
        webOrderOrdersPage.name.sendKeys(data.get(0).get("name").toString());
        webOrderOrdersPage.street.sendKeys(data.get(0).get("address").toString());
        webOrderOrdersPage.city.sendKeys(data.get(0).get("city").toString());
        webOrderOrdersPage.state.sendKeys(data.get(0).get("state").toString());
        webOrderOrdersPage.zip.sendKeys(data.get(0).get("zip").toString());
        webOrderOrdersPage.visaCheckBox.click();
        webOrderOrdersPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        webOrderOrdersPage.expireDate.sendKeys(data.get(0).get("expire date").toString());
        webOrderOrdersPage.processButton.click();
    }

        @Then("user validates success message {string}")
        public void user_validates_success_message(String expectedMessage) {
            String actualSuccessMessage=webOrderOrdersPage.successMessage.getText();
            Assert.assertEquals(expectedMessage,actualSuccessMessage);
        }


    @Then("user validates order added to List Of Orders")
    public void user_validates_order_added_to_List_Of_Orders() {
        webOrdersHomePage.viewAllOrdersModule.click();
        int numberOfRowsAfterOrderCreation=webOrdersHomePage.numberOfRows.size();
        Assert.assertEquals(numberOfRowsAfterOrderCreation-numberOfRows,1);
        String actualName=webOrdersHomePage.firstRowName.getText();
        Assert.assertEquals(data.get(0).get("name").toString(),actualName);
        // do the validation for the rest of the data
        String actualProduct=webOrdersHomePage.firstRowProduct.getText();
        Assert.assertEquals(data.get(0).get("order").toString(),actualProduct);
        String actualQuantity=webOrdersHomePage.firstRowQuantity.getText();
        Assert.assertEquals(data.get(0).get("quantity").toString(),actualQuantity);
        String  actualAddress=webOrdersHomePage.firstRowStreetName.getText();
        Assert.assertEquals(data.get(0).get("address").toString(),actualAddress);
        String actualCityName=webOrdersHomePage.firstRowCityName.getText();
        Assert.assertEquals(data.get(0).get("city").toString(),actualCityName);
        String actualStateName=webOrdersHomePage.firstRowStateName.getText();
        Assert.assertEquals(data.get(0).get("state").toString(),actualStateName);
        String actualZipCode=webOrdersHomePage.firstRowZipCode.getText();
        Assert.assertEquals(data.get(0).get("zip").toString(),actualZipCode);
        String actualCardType=webOrdersHomePage.firstRowCardNumber.getText();
        Assert.assertEquals(data.get(0).get("cc").toString(),actualCardType);
        String actualExpireDate=webOrdersHomePage.firstRowExpireDate.getText();
        Assert.assertEquals(data.get(0).get("expire date").toString(),actualExpireDate);




    }


    @And("user counts number of orders in table")
    public void userCountsNumberOfOrdersInTable() {
         numberOfRows=webOrdersHomePage.numberOfRows.size();

    }
}
