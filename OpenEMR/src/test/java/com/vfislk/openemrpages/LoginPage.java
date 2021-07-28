package com.vfislk.openemrpages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

	private By usernameLocator = By.id("authUser");
	private By passwordLocator = By.cssSelector("#clearPass");
	private By languageLocator=By.name("languageChoice");
	private By loginLocator=By.xpath("//button[@type='submit']");
	private By errorLocator=By.xpath("//div[contains(text(),'Invalid')]");
	private By ackLicCertLocator=By.partialLinkText("Acknowledgments");
	private By appDescriptionLocator=By.xpath("//p[contains(text(),'most')]");
	
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}

	public void enterUsername(String username) {
		this.driver.findElement(this.usernameLocator).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public void selectLanguageByText(String languageText)
	{
		Select selectLanguage=new Select(driver.findElement(languageLocator));
		selectLanguage.selectByVisibleText(languageText);
	}
	
	public void clickOnLogin()
	{
		driver.findElement(loginLocator).click();
	}
	
	public String getInvalidErrorMessage()
	{
		return driver.findElement(errorLocator).getText().trim();
	}
	
	public void clickOnAcknowledgmentsLicensingAndCertification()
	{
		driver.findElement(ackLicCertLocator).click();
	}
	
	public String getApplicationDescription()
	{
		return driver.findElement(appDescriptionLocator).getText().trim();
	}

}
