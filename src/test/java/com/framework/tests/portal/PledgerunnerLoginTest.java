package com.framework.tests.portal;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.common.Base;
import com.framework.common.SystemsUtil;
import com.framework.pages.PledgerunnerLoginPage;
import com.framework.pages.PledgerunnerStartingLinePage;

/**
 * PledgerunnerLoginTest class is the actual Test Case.
 *
 */
public class PledgerunnerLoginTest extends Base {

	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest(alwaysRun = true)
	public void reportEnvironmentIssue() {
		SystemsUtil systemTest = new SystemsUtil();
		try {
			systemTest.verifySystemReadiness(80, "http://test.pledgerunner.org");
		} catch (Exception e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		this.driver.quit(); 
	}

	/*
	 * DataProviders can be either external or bundled. For the purpose 
	 * of this sample, the test data is separated to demonstrate 
	 * implementation.
	 * 
	 */

	@DataProvider(name = "invalid.datasets")
	public Object[][] invalidcredentials() {
		return new Object[][] { { "dummy@pledgerunner.org", "test" } };
	}

	@DataProvider(name = "incorrect.datasets")
	public Object[][] incorrectcredentials() {
		return new Object[][] { { "test@pledgerunner.org", "" } };
	}

	@DataProvider(name = "valid.datasets")
	public Object[][] validcredentials() {
		return new Object[][] { { "test@pledgerunner.org", "pledgerunner" } };
	}

	/*
	 * Three simple test cases are provided in order of execution: 
	 * 1. Invalid login. 
	 * 2. Incorrect login. 
	 * 3. Valid login.
	 * 
	 * These tests can also be consolidated into one method, but for 
	 * the purpose of this sample, they are split to demonstrate 
	 * individual cases.
	 */

	@Test(groups = { "smoke" }, dataProvider = "invalid.datasets")
	public void loginAsInvalidUser(String emailaddress, String password) throws InterruptedException {
		PledgerunnerLoginPage loginPage = PledgerunnerLoginPage.navigateTo(driver);
		Assert.assertTrue(loginPage.failedLogin(emailaddress, password, "invalid"));
		loginPage.closeAlert();
	}
	
	@Test(groups = { "smoke" }, dataProvider = "incorrect.datasets")
	public void loginAsIncorrectUser(String emailaddress, String password) throws InterruptedException {
		PledgerunnerLoginPage loginPage = PledgerunnerLoginPage.navigateTo(driver);
		Assert.assertTrue(loginPage.failedLogin(emailaddress, password, "incorrect"));
		loginPage.closeAlert();
	}
	
	@Test(groups = { "smoke" }, dataProvider = "valid.datasets")
	public void loginAsValidUser(String emailaddress, String password) throws InterruptedException {
		PledgerunnerLoginPage loginPage = PledgerunnerLoginPage.navigateTo(driver);
		PledgerunnerStartingLinePage startinglinePage = loginPage.validLogin(emailaddress, password);
		Assert.assertTrue(startinglinePage.isPageCorrect());
		startinglinePage.logout();
	}
	
}
