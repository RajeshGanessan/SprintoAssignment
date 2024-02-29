package driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class DriverFactory {
	WebDriver driver;
	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");

		System.out.println("browser name is : " + browserName);



		switch (browserName.toLowerCase()) {

		case "chrome":
				tlDriver.set(new ChromeDriver());
			break;

		case "firefox":
				tlDriver.set(new FirefoxDriver());

			break;
		case "edge":

				tlDriver.set(new EdgeDriver());

			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Plz pass the right browser...." + browserName);
			break;
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}



	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();
		try {

		ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}


}
