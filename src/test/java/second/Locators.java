package second;

import first.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Locators {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.cactusvpn.com/");

        WebElement cancelNotification = new WebDriverWait(driver, 5).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onesignal-slidedown-cancel-button']")));
        cancelNotification.click();

//        WebElement supportButton = driver.findElement(By.id("menu-item-447"));
        WebElement supportButton = waitForElementLocatedBy(driver, By.xpath("//li[@class='menu-item menu-item-type-post_type menu-item-object-page menu-item-12348']/a")); //изначально находим li с id, а потом в нем a
        supportButton.click();

        WebElement searchFormSupport = waitForElementLocatedBy(driver, By.id("s"));
        searchFormSupport.sendKeys("база");
        WebElement searchButton = waitForElementLocatedBy(driver, By.cssSelector("#searchsubmit"));
        searchButton.click();

        WebElement openvpnLink = waitForElementLocatedBy(driver, By.cssSelector("div:nth-child(4) > h2 > a"));
        openvpnLink.click();

        List<WebElement> countofLi = driver.findElements(By.cssSelector("div.panel-body > ol > li"));
        System.out.println("Count of li on the Open VPN page: " + countofLi.size());

        Thread.sleep(5000);
        driver.quit();

    }
    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
