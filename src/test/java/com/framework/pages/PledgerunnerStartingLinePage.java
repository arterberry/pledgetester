package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.common.TestDriverFactory;

/**
 * Pledgerunner Starting Line class
 *
 */
public class PledgerunnerStartingLinePage {

	private WebDriver driver;
	
	@FindBy(xpath = "//div[@id='content']/section[2]/div/div[2]/div/div")
	private WebElement startEvent;

	@FindBy(xpath = "//header[@id='header']/div/div[2]/ul/li/a/span[2]")
	private WebElement headMenu;

	@FindBy(xpath = "//header[@id='header']/div/div[2]/ul/li/ul/li[7]/a")
	private WebElement logout;

	public PledgerunnerStartingLinePage(WebDriver driver) {
		this.setDriver(driver);
	}

	public static PledgerunnerStartingLinePage navigateTo(WebDriver driver) {
		driver.get(TestDriverFactory.createTestDriver().getTestUrl() + "/start");
		return PageFactory.initElements(driver, PledgerunnerStartingLinePage.class);
	}

	public void logout() {
		headMenu.click();
		logout.click();
	}

	/**
	 * Confirm if Starting Line page is displayed.
	 * 
	 * @return boolean
	 */
	public boolean isPageCorrect() {		
		if (startEvent.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
