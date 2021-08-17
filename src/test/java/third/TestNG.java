package third;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestNG {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true) // перед каждым методом открываем новый браузер
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    @Test(description = "Count of transfers on the Transfers page")
    public void transfersPageHasResults(){
        driver.get("https://football24.ua/");
/*
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit Wait (Неявный): максимум 10 секунд ожидания для каждого поиска
*/
        WebElement transfersButton = waitForElementLocatedBy(driver, By.xpath("//li[@id='menu-id-384']/a"));
        transfersButton.click();
        List<WebElement> countOfTransfers = driver.findElements(By.xpath("//div[@id='aazone.transfersZone']/div[@class='small-12 grid-container transfers-card']"));
        Assert.assertEquals(countOfTransfers.size(),10,"Found no 10 elements!");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver=null;
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) { // Explicit Wait (Явный): на один конкретный поиск элемента
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}