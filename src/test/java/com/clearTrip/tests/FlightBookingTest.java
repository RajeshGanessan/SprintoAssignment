package com.clearTrip.tests;

import com.clearTrip.base.BaseTest;
import constants.AppConstants;
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
     flightsPage =  bookingPage.selectDepartureCity(AppConstants.DEPATURE_CITY)
             .selectDestinationCity(AppConstants.DESTINATION_CITY)
             .selectDepartureDate(AppConstants.NO_OF_DAYS_FROM_TODAY)
             .clickOnSearchFlights();
     Assert.assertTrue(flightsPage.isLockPricesShown());
    }

    @Test(priority = 2)
    public void chooseTheFlight() {
       boolean isInPaymentsPage =  flightsPage.sortByCheapest()
                .clickBookNow();
        Assert.assertTrue(isInPaymentsPage);
    }
}
