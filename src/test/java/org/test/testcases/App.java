package org.test.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.config.BrowserConfiguration;
import org.test.helper.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;


/**
 * Hello world!
 *
 */
public class App extends TestBase
{

    Logger log = LoggerFactory.getLogger(App.class);
    Browser browser = new Browser();

    @Test
    public void testIMDb(){
        System.out.println("Inside test");
        WebDriver driver = browser.getBrowser(super.getConfig()); 
        log.info("Got chrome browser...");
        driver.manage().window().maximize();

        driver.get("http://www.imdb.de");

        WebElement input = driver.findElement(By.name("q"));

        highlightElement(input, driver);
        input.clear();
        input.sendKeys("Julie & Julia");
        input.submit();

        WebElement title = driver.findElement(By.xpath("//*[@id='main']/div/div[2]/table/tbody/tr[1]/td[2]/a"));
        highlightElement(title, driver);
        title.click();

        log.info("TITLE : " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("IMDb"));

        driver.quit();
    }

    /**
     * method highlights the element which is being selected.
     * 
     * @param element
     */
    public void highlightElement(WebElement element, WebDriver driver) {

        for (int i = 0; i < 2; i++) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: yellow; border: 3px solid yellow;");
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");

        }

    }

}
