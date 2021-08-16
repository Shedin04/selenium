package pageobject.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends AbstractPage{

    private static final String HOMEPAGE_URL = "https://football24.ua/";

    @FindBy(xpath = "//li[@id='menu-id-384']/a")
    private WebElement transfersButton;

    @FindBy(css = "#aazone\\.gamesMainZone ul.right > li > a")
    private WebElement nextResultAndMatchesButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(transfersButton));
        return this;
    }

    public HomePage clickNextResultAndMatchesButton(int countOfClicks) {
        for (int i = 0; i < countOfClicks; i++) {
            nextResultAndMatchesButton.click();
        }
        return this;
    }

    public TransfersPage clickTransferButton() {
        transfersButton.click();
        return new TransfersPage(driver);
    }
}
