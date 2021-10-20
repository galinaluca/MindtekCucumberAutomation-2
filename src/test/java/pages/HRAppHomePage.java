package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HRAppHomePage {
    public HRAppHomePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//a[@href='/employee/-1']")
    public WebElement employeeCreationButton;

    @FindBy(xpath ="//td[contains(text(), '101')]" )
    public  WebElement employeeId;

    @FindBy(xpath = " //tbody//tr/td[1]")
    public List<WebElement> employeesId;

    @FindBy(xpath = " //tbody//tr/td[2]")
    public List<WebElement> firstNames;

    @FindBy(xpath = " //tbody//tr/td[3]")
    public  List<WebElement> lastNames;

    @FindBy(id ="department")
    public WebElement departmentsDropdown;

    @FindBy(xpath = "//th")
    public List<WebElement> columns;
    @FindBy(xpath = "//td")
    public List<WebElement> tableData;
}
