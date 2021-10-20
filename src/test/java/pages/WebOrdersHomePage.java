package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WebOrdersHomePage {
    public WebOrdersHomePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//li[1]/a")
    public WebElement viewAllOrders;
    @FindBy(id="ctl00_MainContent_orderGrid_ctl06_OrderSelector")
    public  WebElement selectOrderCheckBox;
    @FindBy(id="ctl00_MainContent_btnDelete")
    public WebElement deleteSelectedBox;
    @FindBy(xpath = "//tbody/tr[1]/th[2]")
    public List<WebElement> ordersListOfNames;
    @FindBy(xpath = "//li[3]/a")
    public WebElement orderModule;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr")
    public List<WebElement> numberOfRows;
    @FindBy(xpath = "//a[@href='Default.aspx']")
    public WebElement viewAllOrdersModule;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[2]")
    public WebElement firstRowName;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[3]")
    public WebElement firstRowProduct;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[4]")
    public WebElement firstRowQuantity;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[6]")
    public  WebElement firstRowStreetName;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[7]")
    public  WebElement firstRowCityName;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[8]")
    public  WebElement firstRowStateName;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[9]")
    public  WebElement firstRowZipCode;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[11]")
    public  WebElement firstRowCardNumber;
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[12]")
    public  WebElement firstRowExpireDate;
}
