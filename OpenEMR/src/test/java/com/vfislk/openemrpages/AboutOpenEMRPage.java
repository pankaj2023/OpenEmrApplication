package com.vfislk.openemrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class AboutOpenEMRPage {
	private String mscFrameName = "msc";
	private By headerLocator = By.tagName("h1");
	private By versionLocator = By.tagName("h4");

	private WebDriver driver;

	public AboutOpenEMRPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getHeaderText() {
		driver.switchTo().frame(mscFrameName);
		String actualHeader = driver.findElement(headerLocator).getText();
		driver.switchTo().defaultContent();

		return actualHeader;
	}

	public String getVersionNumberText() {
		driver.switchTo().frame(mscFrameName);
		String actualHeader = driver.findElement(versionLocator).getText();
		driver.switchTo().defaultContent();

		return actualHeader;
	}
}
