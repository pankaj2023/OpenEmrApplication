package com.vfislk.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestLogin {

	private WebDriver driver;

	@BeforeMethod
	@Parameters({"browsername"})
	public void setUp(@Optional("ch") String browser) {
		
		switch (browser.toLowerCase()) {
		case "ff":
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");

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

	@Test
	public void invalidCredentialTest() {
		driver.findElement(By.id("authUser")).sendKeys("admin123");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLanguage = new Select(driver.findElement(By.name("languageChoice")));
		selectLanguage.selectByVisibleText("English (Indian)");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String actualValue = driver.findElement(By.xpath("//div[contains(text(),'Invalid')]")).getText().trim();

		Assert.assertEquals(actualValue, "Invalid username or password");
	}

	@Test(description = "Valid Credential Test")
	public void validCredentialTest() {
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLanguage = new Select(driver.findElement(By.name("languageChoice")));
		selectLanguage.selectByVisibleText("English (Indian)");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Calendar']")));

		String actualValue = driver.getTitle();
		Assert.assertEquals(actualValue, "patient OS");

	}

}
