package icanwintask.test;

import icanwintask.page.PastebinMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PastebinTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true) // перед каждым методом открываем новый браузер
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    @Test(description = "Next results and matches button")
    public void clickNextResultAndMatchesButton() {
        String text = "Hello from WebDriver";
        new PastebinMainPage(driver).openPage().clickSpamButton().addTextInForm(text)
            .choose10MinExpiration()
                .pasteNameOrTitleForm("helloweb")
                    .createNewPasteButton().clickSpamButton();
        Assert.assertEquals((driver.findElement(By.className("de1"))).getText(), text, "Text is incorrect");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
        driver=null;
    }
}
