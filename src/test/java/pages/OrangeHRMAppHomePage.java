package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeHRMAppHomePage {
    public OrangeHRMAppHomePage (){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//*[@id=\"menu_leave_viewLeaveModule\"]/b")
    public WebElement leave;


    @FindBy(xpath = "//span[contains(text(), 'My Leave')]")
    public WebElement myLeaveButton;

    @FindBy(id="calFromDate")
    public WebElement fromInput;

    @FindBy(id="calToDate")
    public WebElement toInput;

    @FindBy(id="btnSearch")
    public WebElement searchBox;

    @FindBy(xpath = "//*[contains(text(), '2020-12-22 to 2020-12-24')]")
    public WebElement datesOutput;

}
