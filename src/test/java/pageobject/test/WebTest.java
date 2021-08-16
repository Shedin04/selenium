package pageobject.test;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.page.HomePage;

import java.util.List;

public class WebTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true) // перед каждым методом открываем новый браузер
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    @Test(description = "Count of transfers on the Transfers page")
    public void transfersPageCountOfTransfers(){
        int actualCountOfTransfers = new HomePage(driver).openStartedPage().clickTransferButton().
                countOfTransfersOnThePage();
        Assert.assertEquals(actualCountOfTransfers,10, "Found no 10 elements!");
    }

    @Test(description = "Use filter by country on the Transfers page")
    public void transfersPageFilterByCountry(){
        String expected = "Україна";
        List<WebElement> actualCountOfTransfers = new HomePage(driver).openStartedPage().
                clickTransferButton().
                filterByCountryTransfersPage(expected);
        Assert.assertEquals(actualCountOfTransfers.get(0).getText(),expected,"Found no expected text!");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver=null;
    }
}
