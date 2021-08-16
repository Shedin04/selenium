package pageobject.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    private static final String HOMEPAGE_URL = "https://football24.ua/";
    private WebDriver driver;

    @FindBy(xpath = "//li[@id='menu-id-384']/a")
    private WebElement transfersButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openStartedPage(){
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(transfersButton));
        return this;
    }

    public TransfersPage clickTransferButton() {
        transfersButton.click();
        return new TransfersPage(driver);
    }
}
