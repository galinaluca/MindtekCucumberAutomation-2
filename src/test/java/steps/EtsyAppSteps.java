package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyHomePage;
import pages.EtsySEarchResultsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {
    WebDriver driver = Driver.getDriver();
    EtsyHomePage etsyHomePage = new EtsyHomePage();
    EtsySEarchResultsPage etsySEarchResultsPage = new EtsySEarchResultsPage();

    @Given("user navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));

    }

    @When("user searches for {string}")
    public void user_searches_for(String item) {
        etsyHomePage.searchBox.sendKeys(item + Keys.ENTER);
    }

    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) {
        etsySEarchResultsPage.allFiltersButton.click();
        etsySEarchResultsPage.customPriceRadioButton.click();
        etsySEarchResultsPage.lowPriceInputBox.sendKeys(price + "");
        etsySEarchResultsPage.applyButton.click();

    }

    @Then("user verifies that item prices are over {int}")
    public void user_verifies_that_item_prices_are_over(Integer price) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> prices = etsySEarchResultsPage.prices;
        for (WebElement element : prices) {
            System.out.println(element.getText());
            //"32,402.00"->"32402.00"
            String priceStr = element.getText().replace(",", "");
            //"32402.00"-> 32402.00
            double doublePrice = Double.parseDouble(priceStr);
            // System.out.println(doublePrice);
            Assert.assertTrue(doublePrice >= price);
        }

    }

    @Then("user validates search result items contains keyword {string}")
    public void user_validates_search_result_items_contains_keyword(String item) {
        List<WebElement> itemName = etsySEarchResultsPage.itemNames;

        for (WebElement element : itemName) {
            System.out.println(element.getText());
            Assert.assertTrue("Item does not contain carpet keyword" + element.getText(),
                    element.getText().toLowerCase().contains(item) ||
                            element.getText().toLowerCase().contains("rug"));
        }
    }


    @Then("user validates title is {string}")
    public void userValidatesTitleIs(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {
        if (section.equalsIgnoreCase("Jewelery and Accessories")) {
            etsyHomePage.jeweleryAndAccessories.click();

        } else if (section.equalsIgnoreCase("Clothing & Shoes")) {
            etsyHomePage.clothingAndShoes.click();

        } else if (section.equalsIgnoreCase("Home & Living")) {
            etsyHomePage.homeAndLiving.click();

        } else if (section.equalsIgnoreCase("Wedding & Party")) {
            etsyHomePage.weddingAndParty.click();

        } else if (section.equalsIgnoreCase("Toys & Entertainment")) {
            etsyHomePage.toysAndEntertainment.click();

        } else if (section.equalsIgnoreCase("Art & Collectibles")) {
            etsyHomePage.artAndCollectible.click();
        }
    }
}