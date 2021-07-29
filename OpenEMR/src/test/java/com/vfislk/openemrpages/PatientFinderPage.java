package com.vfislk.openemrpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class PatientFinderPage {
	private By finFrameLocator = By.xpath("//iframe[contains(@src,'dynamic_finder')]");
	private By addNewPatientLocator = By.xpath("//button[normalize-space()='Add New Patient']");

	private WebDriver driver;

	public PatientFinderPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnAddPatient() {
		driver.switchTo().frame(driver.findElement(finFrameLocator));
		driver.findElement(addNewPatientLocator).click();
		driver.switchTo().defaultContent();
	}
}
