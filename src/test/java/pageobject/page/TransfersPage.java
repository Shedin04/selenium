package pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransfersPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='aazone.transfersZone']/div[@class='small-12 grid-container transfers-card']")
    private List<WebElement> countOfTransfers;

    @FindBy(xpath = "//div[@id='countryId_chosen']//input")
    private WebElement countrySearchForm;

    @FindBy(xpath = "//ul[@class='chosen-results']/li")
    private List<WebElement> listOfCountriesResults;

    protected TransfersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public int countOfTransfersOnThePage(){
        System.out.println("There is " + countOfTransfers.size() + " transfers on the page");
        return countOfTransfers.size();
    }

    public List filterByCountryTransfersPage(String countryName){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(countrySearchForm));
        countrySearchForm.sendKeys(countryName);
        return listOfCountriesResults;
    }
}
