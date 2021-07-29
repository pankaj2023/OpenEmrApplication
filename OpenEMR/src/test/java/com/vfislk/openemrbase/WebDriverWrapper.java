package com.vfislk.openemrbase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.utilities.PropertiesUtils;

public class WebDriverWrapper {

	protected WebDriver driver;

	@BeforeMethod
	@Parameters({ "browsername", "url" })
	public void setUp(@Optional("ch") String browser,
			@Optional("https://demo.openemr.io/a/openemr/index.php") String url) {
		
		if(PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "priority").equals("yes"))
		{
			browser=PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "browser");
			url=PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "url");
		}
		
		
		launchBrowser(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
	}
	
	public void launchBrowser(String browser)
	{
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
	}
	
	public void takeScreenshot() throws IOException
	{
		String fileName="screenshot_"+LocalDateTime.now().toString().replace(":", "-")+".png";
		TakesScreenshot ts=(TakesScreenshot) driver;	
		File file =ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("src/test/resources/screenshots/"+fileName));
	}
	
	@AfterMethod
	public void tearDown() throws IOException {
	takeScreenshot();	
	driver.quit();
	}

}
