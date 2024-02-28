package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class BookingPage {

    WebDriver driver;
    WebDriverWait wait;
    SimpleDateFormat simpleDateFormat;
    JavascriptExecutor jse;

    public BookingPage(WebDriver driver, WebDriverWait wait) {

        this.driver = driver;
        this.wait = wait;
    }

    private By fromCity = By.xpath("//input[@id='fromCity']");
    private By toCity = By.xpath("//input[@id='toCity']");
    private By cityList =  By.xpath("//ul[@role='listbox']");

    private By dateField = By.xpath("//label[@for='departure']");
    private By searchFlights = By.xpath("//a[text()='Search']");

    String selectDate = "//div[@class='DayPicker-Day' and contains(@aria-label,'%s')]";
    String selectCity = "//ul[@role='listbox']/li//p[contains(text(),'%s')]";


    public BookingPage selectDepartureCity(String departure) {
        driver.findElement(fromCity).sendKeys(departure);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityList));
        driver.findElement(By.xpath(String.format(selectCity,departure))).click();
        return this;
    }

    public BookingPage selectDestinationCity(String destination) {
        driver.findElement(toCity).sendKeys(destination);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityList));
        driver.findElement(By.xpath(String.format(selectCity,destination))).click();
        return this;
    }

    public BookingPage selectDepartureDate(int daysFromToday){
        String dateToPick = getDepartureDate(daysFromToday);
        wait.until(ExpectedConditions.elementToBeClickable(dateField));
        jsClick(dateField);
        driver.findElement(By.xpath(String.format(selectDate,dateToPick))).click();
        return this;
    }

    public FlightsPage clickOnSearchFlights(){
        wait.until(ExpectedConditions.elementToBeClickable(dateField));
        driver.findElement(searchFlights).click();
        return new FlightsPage(driver,wait);
    }
    private String getDepartureDate(int daysFromToday) {
        simpleDateFormat = new SimpleDateFormat("MMM dd yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,daysFromToday);
        return simpleDateFormat.format(cal.getTime());
    }

    private void jsClick(By by){
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(by));
    }



}
