package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.common.TestDriverFactory;

/**
 * PledgerunnerLoginPage class is a collection of page elements and verification logic.
 *
 */
public class PledgerunnerLoginPage {

	final private static String INVALID_LOGIN_MSG = "Login Failed";
	final private static String INCORRECT_PASSWORD_LOGIN_MSG = "There was an error";
	final private static String LOGIN_PAGE_TITLE = "Login to Pledgerunner";

	private static StringBuffer verificationErrors = new StringBuffer();
	private WebDriver driver;

	@FindBy(xpath = "//input[@id='loginemail']")
	private static WebElement loginemail;

	@FindBy(xpath = "//input[@id='loginpassword']")
	private static WebElement loginpassword;

	@FindBy(xpath = "//input[@id='loginBtn']")
	private static WebElement loginbutton;

	@FindBy(css = "p.pr-alert")
	private static WebElement alertMsg;

	@FindBy(xpath = "//button[@type='button']")
	private static WebElement alerMsgbutton;

	@FindBy(xpath = "//title")
	private WebElement pageTitle;

	public PledgerunnerLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Is email address field set?
	 * 
	 * @param strLoginEmail
	 * @return boolean
	 */
	public boolean isLoginEmailAddressSet(String strLoginEmail) {

		try {
			return loginemail.getAttribute("value").equals(strLoginEmail);
		} catch (Error e) {
			verificationErrors.append(e.toString());
			return false;
		}
	}

	/**
	 * Is password field set?
	 * 
	 * @return boolean
	 */
	public boolean isLoginPasswordEmpty() {

		try {
			return loginpassword.getAttribute("value").equals("");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			return false;
		}
	}

	private void login(String strLoginEmail, String strLoginPassword) throws InterruptedException {
		loginemail.clear();
		loginemail.sendKeys(strLoginEmail);
		loginpassword.sendKeys(strLoginPassword);
		Thread.sleep(1000);
		loginbutton.click();
	}

	/**
	 * Attempt login to Pledgerunner.
	 * 
	 * @param strLoginEmail
	 * @param strLoginPassword
	 * @return pledgerunnerStartingLinePage
	 * @throws InterruptedException 
	 */
	public PledgerunnerStartingLinePage validLogin(String strLoginEmail, String strLoginPassword) throws InterruptedException {
		this.login(strLoginEmail, strLoginPassword);
		return PageFactory.initElements(driver, PledgerunnerStartingLinePage.class);
	}

	/**
	 * Attempt failed login to Pledgerunner.
	 * 
	 * @param strLoginEmail
	 * @param strLoginPassword
	 * @return pledgerunnerLoginPage
	 * @throws InterruptedException
	 */
	public boolean failedLogin(String strLoginEmail, String strLoginPassword, String failType)
			throws InterruptedException {
		this.login(strLoginEmail, strLoginPassword);
		Thread.sleep(2000);
		if (this.isPageTitleCorrect()) {
			boolean state = false;
			if (this.loginFailedErrorMessageDisplayed()) {
				if (failType == "invalid") {
					state = this.isErrorMessageInvalid();
				}
				if (failType == "incorrect") {
					state = this.isErrorMessageIncorrect();
				}
				return state;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	public void closeAlert() {
		if (alertMsg.isDisplayed()) {
			alerMsgbutton.click();
		}
	}

	/**
	 * Confirm login failed error message is displayed.
	 * 
	 * @return boolean
	 */
	public boolean loginFailedErrorMessageDisplayed() {
		if (alertMsg.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Confirm if login error message is an invalid alert message.
	 * 
	 * @return boolean
	 */
	public boolean isErrorMessageInvalid() {
		if (alertMsg.getText().contains(INVALID_LOGIN_MSG)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Confirm if login error message is an incorrect alert message.
	 * 
	 * @return boolean
	 */
	public boolean isErrorMessageIncorrect() {
		if (alertMsg.getText().contains(INCORRECT_PASSWORD_LOGIN_MSG)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Confirm if login page title is correct.
	 * 
	 * @return boolean
	 */
	public boolean isPageTitleCorrect() {
		if (driver.getTitle().contains(LOGIN_PAGE_TITLE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Confirm if login page is displayed.
	 * 
	 * @return boolean
	 */
	public boolean isPageCorrect() {
		if (loginbutton.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Navigate to Pledgerunner Login page with environment setting (URL).
	 * 
	 * @param driver
	 * @param env
	 * @return pledgerunnerLoginPage
	 */
	public static PledgerunnerLoginPage navigateTo(WebDriver driver, String env) {
		driver.get(TestDriverFactory.createTestDriver(env).getTestUrl() + "/login");
		return PageFactory.initElements(driver, PledgerunnerLoginPage.class);
	}

	/**
	 * Navigate to Pledgerunner Login page.
	 * 
	 * @param driver
	 * @return pledgerunnerLoginPage
	 */
	public static PledgerunnerLoginPage navigateTo(WebDriver driver) {
		driver.get("http://test.pledgerunner.org/login");
		return PageFactory.initElements(driver, PledgerunnerLoginPage.class);
	}

}
