package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.BaseClass.BaseClass;
import com.tutorialsninja.qa.pageObjectRepo.AccountPage;
import com.tutorialsninja.qa.pageObjectRepo.HomePage;
import com.tutorialsninja.qa.pageObjectRepo.LoginPage;
import com.tuturialsninja.qa.Utils.Utilities;

public class Login extends BaseClass
{
	LoginPage loginPage;
	
	public Login() throws Throwable  
	{
		super();
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(pro.getProperty("browserName"));
		HomePage home = new HomePage(driver);
		loginPage = home.navigateToLoginPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	/**
	 * Receiving the data from the @DataProvider and passing it through the parameters below
	 * @param emailId
	 * @param passWord
	 */
	@Test(priority = 1 , dataProvider = "validCredentialsSupplierLogin")
	public void verifyLoginWithValidCredentials(String emailId, String passWord)
	{
		AccountPage account =loginPage.login(emailId, passWord);
		Assert.assertTrue(account.getDisplayStatusOfEditYourInfomationOption(),"Edit your account information option is not displayed");
	}
	
	/**
	 * Getting the data from the excel sheet and supplying it through the @DataProvider 
	 * @return
	 * @author Abinash das
	 */
	@DataProvider(name="validCredentialsSupplierLogin")
	public Object[][] supplyTestData()
	{
		Object [][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailNoMatchWarningMessage")), "Expected message is not displayed");
	}
	
	@Test(priority = 3)
	public void verifyLoginwithInvalidEmailandValidPassword()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), pro.getProperty("validPassword"));
		assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailNoMatchWarningMessage")), "Expected message is not displayed");
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailandInvalidPassword()
	{
		loginPage.login(pro.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailNoMatchWarningMessage")), "Expected message is not displayed");
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButton();
		assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailNoMatchWarningMessage")), "Expected message is not displayed");
	}
}
