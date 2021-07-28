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

import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.utilities.DataProviderUtils;



public class TestLogin extends WebDriverWrapper {


	@Test
	public void invalidCredentialTest() {
		LoginPage login = new LoginPage(driver);
		login.enterUsername("admin123");
		login.enterPassword("pass");
		login.selectLanguageByText("Dutch");
		login.clickOnLogin();

		Assert.assertEquals(login.getInvalidErrorMessage(), "Invalid username or password");
	}
	
	@Test(dataProviderClass = DataProviderUtils.class,dataProvider = "validCredentialData",description = "Valid Credential Test")
	public void validCredentialTest(String username,String password,String language,String expectedValue) {
	
		LoginPage login = new LoginPage(driver);
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.waitForPresenceOfCalendarText();

		Assert.assertEquals(dashboard.getCurrentTitle(), expectedValue);
	}

}
