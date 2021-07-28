package com.vfislk.openemrbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class WebDriverWrapper {

	protected WebDriver driver;

	@BeforeMethod
	@Parameters({ "browsername", "url" })
	public void setUp(@Optional("ch") String browser,
			@Optional("https://demo.openemr.io/a/openemr/index.php") String url) {
		switch (browser.toLowerCase()) {
		case "ff":
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.openemr.io/a/openemr/index.php");
		
	}
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
}
