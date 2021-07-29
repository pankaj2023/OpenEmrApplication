package com.vfislk.openemrpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class PatientDashboardPage {
	private String patFrameName = "pat";
	private By patientNameLocator = By.xpath("//h2[contains(text(),'Medical')]");

	private WebDriver driver;

	public PatientDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddedPatientName() {
		driver.switchTo().frame(patFrameName);
		String actualValue = driver.findElement(patientNameLocator).getText();
		driver.switchTo().defaultContent();

		return actualValue;
	}
}
