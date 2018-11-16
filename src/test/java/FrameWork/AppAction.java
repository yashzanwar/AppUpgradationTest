package FrameWork;
/**
 * created by yash.zanwar on 14/11/18
 */

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class AppAction {

    protected AndroidDriver driver;
    static int DefaultTime = 60;


    protected void EnterValue(Locator locator, String value) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if (locator.getElement() == null) {
            driver.findElement(locator.getBy()).sendKeys(value);
        } else {
            locator.getElement().findElement(locator.getBy()).sendKeys(value);
        }
        Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'", true);
    }

    protected void click(Locator locator) {

        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        if (locator.getElement() == null) {
            webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            driver.findElement(locator.getBy()).click();
        } else {
            System.out.println(locator.getBy());
            System.out.println(locator.getElement());
            WebElement element = locator.getElement().findElement(locator.getBy());
            webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
            element.click();
        }
        Reporter.log("Clicked On " + locator.getName() + "", true);
    }

    protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
        driver.manage().timeouts().implicitlyWait(Timeout,TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            } else {
                System.out.println(locator.getBy());
                System.out.println(locator.getElement());
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            return true;
        }catch(Exception e){
            return false;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    public void waitUntilElementDisappear(Locator locator,int Timeout) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

}
