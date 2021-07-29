package com.vfislk.openemrpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchOrAddPatientPage {

	private String patFrameName = "pat";
	private By modalFrameLocator=By.xpath("//input[@value='Confirm Create New Patient']");
	private By confirmCreateNewPatientLocator=By.xpath("//input[@value='Confirm Create New Patient']");
	private By closeLocator=By.xpath("//div[@data-dismiss='modal']");
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
//		System.out.println(actualAlertText);

		driver.switchTo().alert().accept();

		return actualAlertText;
	}
	
	
	public void clickOnConfirmCreateNewPatient()
	{
		driver.switchTo().frame(driver.findElement(modalFrameLocator));
		driver.findElement(confirmCreateNewPatientLocator).click();
		driver.switchTo().defaultContent();
	}
	
	public void clickOnHBDClose()
	{
		if(driver.findElements(closeLocator).size()>0)
		{
			driver.findElement(closeLocator).click();
		}
	}

}
