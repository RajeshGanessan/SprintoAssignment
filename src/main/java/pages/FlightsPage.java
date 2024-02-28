package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class FlightsPage {

    WebDriver driver;
    WebDriverWait wait;

    FluentWait<WebDriver> driverFluentWait;

    public FlightsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.driverFluentWait = new FluentWait<>(driver);
    }

    private By popUpClose = By.xpath("//button[text()='OKAY, GOT IT!']");

    private By filters = By.xpath("//div[@class='filtersOuter']/p[text()='Popular Filters']");

    private By cheapestFilter = By.xpath("//div[contains(@class,'sortTabHeadList ')]//div/p[text()='Cheapest']");

    private By flightBookNow = By.xpath("(//div[@class='clusterContent']//div[contains(@class,'listingCard')])[position()=1]");
    private By bookConfirm = By.xpath("(//button[text()='BOOK NOW'])[1]");

    private By completeBooking = By.xpath("//h2[text()='Complete your booking']");


    private void closePopUp() {
        driver.findElement(popUpClose).click();
    }


    public FlightsPage sortByCheapest()  {
        driverFluentWait.withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(10)).ignoring(TimeoutException.class).ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(popUpClose));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpClose));
        closePopUp();
        if (waitForResults()) {
            driver.findElement(cheapestFilter).click();
        }
        return this;
    }

    public boolean clickBookNow() {
        wait.until(ExpectedConditions.elementToBeClickable(flightBookNow));
        driver.findElement(flightBookNow).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookConfirm)).click();
        String mainTab  = driver.getWindowHandle();
        boolean isSwitched = switchToNextTab(driver.getWindowHandles(), mainTab);
        if(isSwitched){
            return driver.findElement(completeBooking).isDisplayed();
        }
        return false;
    }

    public boolean waitForResults() {
         wait.until(ExpectedConditions.visibilityOfElementLocated(filters));
         return driver.findElement(filters).isDisplayed();
    }


    private boolean switchToNextTab(Set<String> windowlist,String mainTab) {

        for(String window : windowlist){
            if(!window.equals(mainTab)) {
                driver.switchTo().window(window);
                return true;
            }
        }
        return false;
    }
}
