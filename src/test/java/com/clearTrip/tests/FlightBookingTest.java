package com.clearTrip.tests;

import com.clearTrip.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.FlightsPage;

public class FlightBookingTest extends BaseTest {

    BookingPage bookingPage;
    FlightsPage flightsPage;


    @BeforeClass
    public void setup(){
        bookingPage = new BookingPage(driver,wait);
    }

    @Test(priority = 1)
    public void loginPageTitleTest() {
     flightsPage =  bookingPage.selectDepartureCity("Bengaluru")
             .selectDestinationCity("Delhi")
             .selectDepartureDate(7)
             .clickOnSearchFlights();
//     Assert.assertTrue(flightsPage.waitForResults());
    }

    @Test(priority = 2)
    public void chooseTheFlight() throws InterruptedException {
        flightsPage.sortByCheapest()
                .clickBookNow();
    }
}
