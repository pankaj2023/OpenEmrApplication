package com.vfislk.openemrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	private By calendarLocator = By.xpath("//span[text()='Calendar']");
	private By patientClientLocator = By.xpath("//div[text()='Patient/Client']");

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForPresenceOfCalendarText() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(calendarLocator)).click();
	}

	public String getCurrentTitle() {
		return driver.getTitle().trim();
	}

	public void mousehoverOnPatientClient() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(patientClientLocator)).perform();
	}
}
