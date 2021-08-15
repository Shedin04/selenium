package first;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class PastebinTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium drivers\\bin\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);

        /*driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);*/

        driver.get("https://pastebin.com");
        new WebDriverWait(driver, 10).until(CustomConditions.expectedCondition());


        WebElement textform = waitForElementLocatedBy(driver, By.id("postform-text")); // поиск с ожиданием (Explicit Waits)
        textform.sendKeys("Hello Word!");
        WebElement butSubmit = driver.findElement(By.xpath("//*[@id=\"w0\"]/div[5]/div[1]/div[8]/button"));
        butSubmit.click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)) //Fluent wait (ждем 15 сек для элемента, пока не появится)
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting search result list was exceeded!");

        List<WebElement> publicpastes = wait.until(new Function<WebDriver, List<WebElement>>() { //Юзаем wait
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li"));
            }
        });

        /*List<WebElement> publicpastes =  new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li")));*/
        System.out.println("Count of pastes: " + publicpastes.size());

        Thread.sleep(5000);
        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
