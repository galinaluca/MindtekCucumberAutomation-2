package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MyStoreAppWishlistPage {
    public MyStoreAppWishlistPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//input[@id='name']")
    public WebElement nameIcon;
    @FindBy(xpath = "//*[@id=\"submitWishlist\"]/span")
    public WebElement saveButton;
    @FindBy(xpath = "//table[@class='table table-bordered']")
    public WebElement newWishlist;

}
