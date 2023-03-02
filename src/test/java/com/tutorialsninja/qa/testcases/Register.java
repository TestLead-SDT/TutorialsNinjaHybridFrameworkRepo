package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.BaseClass.BaseClass;
import com.tutorialsninja.qa.pageObjectRepo.AccountSuccessPage;
import com.tutorialsninja.qa.pageObjectRepo.HomePage;
import com.tutorialsninja.qa.pageObjectRepo.RegisterPage;
import com.tuturialsninja.qa.Utils.Utilities;

public class Register extends BaseClass
{
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public Register() throws Throwable 
	{
		super();
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(pro.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage =homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
 	
	@Test(priority = 1)
	public void verifyRegisteringAccountusingMandtoryFields()
	{
		accountSuccessPage =registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("phoneNum"), pro.getProperty("validPassword"));
		//Validation
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreated"),"Account Success page is not created");
		
	}
	
	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		accountSuccessPage=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("phoneNum"), pro.getProperty("validPassword"));
		//Validation
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreated"),"Account Success page is not created");
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), pro.getProperty("validEmail"), dataProp.getProperty("phoneNum"), pro.getProperty("validPassword"));
		//Validation
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Message is not displayed!");
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning page is not displayed!");
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),"FirstName warning message is not displayed!");
		Assert.assertEquals(registerPage.retrieveLastnameWarning(), dataProp.getProperty("lastNameWarning"),"LastName Warning message is not displayed!");
		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("invalidEmailWarning"),"Email Warning is not displayed!");
		Assert.assertEquals(registerPage.retrieveTelephoneWarning(), dataProp.getProperty("telephoneWarning"),"Telephone warning messgae is not displayed!");
		Assert.assertEquals(registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"),"Password warning message is not displayed!");
		
	}
}
