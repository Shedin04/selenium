package icanwintask.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinMainPage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    private static final String HOMEPAGE_URL = "https://pastebin.com/";

    @FindBy(id = "postform-text")
    private WebElement postform;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationList;

    @FindBy(xpath = "//li[@class='select2-results__option' and text()='10 Minutes']")
    private WebElement pasteExpiration10Min;

    @FindBy(id = "postform-name")
    private WebElement pasteNameTitleForm;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createNewPasteButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS);
        return this;
    }

    public PastebinMainPage addTextInForm(String text){
        postform.sendKeys(text);
        return this;
    }

    public PastebinMainPage choose10MinExpiration(){
        pasteExpirationList.click();
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(pasteExpirationList));
        pasteExpiration10Min.click();
        return this;
    }

    public PastebinMainPage pasteNameOrTitleForm(String pasteText){
        pasteNameTitleForm.sendKeys(pasteText);
        return this;
    }

    public PastebinMainPage createNewPasteButton(){
        createNewPasteButton.click();
        return this;
    }
}
