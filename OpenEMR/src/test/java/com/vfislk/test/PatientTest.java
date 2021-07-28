package com.vfislk.test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.openemrpages.SearchOrAddPatientPage;
public class PatientTest extends WebDriverWrapper {

	@Test
	public void addPatientTest()
	{
		//LoginPage
		LoginPage login = new LoginPage(driver);
		login.enterUsername("admin");
		login.enterPassword("pass");
		login.selectLanguageByText("English (Indian)");
		login.clickOnLogin();
		
		
		//DashboardPage
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.mousehoverOnPatientClient();
		driver.findElement(By.xpath("//div[text()='Patients']")).click();
		
		//PatientFinderPage
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'dynamic_finder')]")));	
		driver.findElement(By.xpath("//button[normalize-space()='Add New Patient']")).click();	
		driver.switchTo().defaultContent();
		
		//SearchOrAddPatientPage
		SearchOrAddPatientPage search=new SearchOrAddPatientPage(driver);
		search.switchToPatFrame();
		
		driver.findElement(By.id("form_fname")).sendKeys("Sat");
		driver.findElement(By.id("form_lname")).sendKeys("Dinakaran");	
		driver.findElement(By.id("form_DOB")).sendKeys("2021-07-20");		
		Select selectGender=new Select(driver.findElement(By.id("form_sex")));
		selectGender.selectByVisibleText("Male");
		driver.findElement(By.id("create")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		driver.switchTo().defaultContent();
		

		String actualAlertText=search.handleAlertAndGetText();

		
		//check for presence of element
		if(driver.findElements(By.xpath("//div[@data-dismiss='modal']")).size()>0)
		{
			driver.findElement(By.xpath("//div[@data-dismiss='modal']")).click();
		}
		
		
		//PatientDashboardPage
		driver.switchTo().frame("pat");
		String actualValue = driver.findElement(By.xpath("//h2[contains(text(),'Medical')]")).getText();
		System.out.println(actualValue);
		driver.switchTo().defaultContent();
		
		//should be in this test method only
		
		//assertion on alert
		Assert.assertTrue(actualAlertText.contains("Tobacco"));
		//assertion on patient name
		Assert.assertEquals(actualValue, "Medical Record Dashboard Sat Dinakaran"); 
		
	}
	
}