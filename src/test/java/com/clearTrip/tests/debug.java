package com.clearTrip.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class debug {

    WebDriver driver;
    private By cheapestPrice = By.xpath("//div[contains(@class,'sortTabHeadList ')]//div/p[text()='Cheapest']//following-sibling::p");
    private By cheapestFilter = By.xpath("//div[contains(@class,'sortTabHeadList ')]//div/p[text()='Cheapest']");
    private By popUpClose = By.xpath("//button[text()='OKAY, GOT IT!']");


    @Test
    public void test() throws InterruptedException {

         driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/flight/search?itinerary=CJB-DEL-06/03/2024&tripType=O&paxType=A-1_C-0_I-0&intl=false&cabinClass=E&ccde=IN&lang=eng");
        Thread.sleep(30000);
        closePopUp();
        getCheapestPrice();

    }

    private void closePopUp(){
        driver.findElement(popUpClose).click();
    }

    private void getCheapestPrice(){
        driver.findElement(cheapestFilter).click();
        String price = driver.findElement(cheapestPrice).getText();
        String finalPrice = price.split(" ")[1].replace(",","");
        System.out.println("test" + finalPrice);
    }
}
