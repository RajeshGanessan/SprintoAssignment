package com.clearTrip.base;


import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {
	protected WebDriver driver;

	protected WebDriverWait wait;
	protected Properties prop;
	DriverFactory driverFactory;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initProp();
		
			if(browserName!=null) {
				prop.setProperty("browser", browserName);

			}
		
		
		driver = driverFactory.initDriver(prop);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		softAssert = new SoftAssert();
	}
	
	
	@AfterTest(enabled = false)
	public void tearDown() {
		if(driver!=null)
			driver.quit();
	}
	

}
