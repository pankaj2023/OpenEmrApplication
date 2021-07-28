package com.vfislk.openemrpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchOrAddPatientPage {

	private String patFrameName = "pat";
	private WebDriver driver;

	public SearchOrAddPatientPage(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToPatFrame() {
		driver.switchTo().frame(patFrameName);
	}

	public String handleAlertAndGetText() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.alertIsPresent());

		String actualAlertText = driver.switchTo().alert().getText();
		System.out.println(actualAlertText);

		driver.switchTo().alert().accept();

		return actualAlertText;
	}
}
