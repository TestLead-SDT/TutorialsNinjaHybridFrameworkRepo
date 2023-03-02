package com.tutorialsninja.qa.pageObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage 
{
	WebDriver driver;
	
	@FindBy (id = "input-firstname")
	private WebElement firstNameTextfield;
	
	@FindBy (id = "input-lastname")
	private WebElement lastNameTextfield;
	
	@FindBy (id = "input-email")
	private WebElement emailTextfield;
	
	@FindBy (id = "input-telephone")
	private WebElement telephoneTextfield;
	
	@FindBy (id = "input-password")
	private WebElement passwordTextfield;
	
	@FindBy (id = "input-confirm")
	private WebElement confirmPasswordTextfield;
	
	@FindBy (name = "agree")
	private WebElement privacyPolicyAgreeCheckbox;
	
	@FindBy (xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy (xpath = "//input[@name='newsletter'][@value=1]")
	private WebElement yesNewsLetterOption;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy (xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnameWarning;
	
	@FindBy (xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnameWarning;
	
	@FindBy (xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy (xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy (xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	//Constructor
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions Methods / Business Logics
	public void enterFirstName(String firstName)
	{
		firstNameTextfield.sendKeys(firstName);
	}
	public void enterLastName(String lastName)
	{
		lastNameTextfield.sendKeys(lastName);
	}
	public void enterEmailAddress(String emailText)
	{
		emailTextfield.sendKeys(emailText);
	}
	public void enterTelephoneNumber(String telePhoneText)
	{
		telephoneTextfield.sendKeys(telePhoneText);
	}
	public void enterPassword(String passwordText)
	{
		passwordTextfield.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String passwordText)
	{
		confirmPasswordTextfield.sendKeys(passwordText);
	}
	public void selectPrivacyPolicy()
	{
		privacyPolicyAgreeCheckbox.click();
	}
	public void selectYesNewsLetterOption()
	{
		yesNewsLetterOption.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click(); 
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailWarningText	= duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String privacypolicyWarningText = privacyPolicyWarning.getText();
		return privacypolicyWarningText;
	}
	public String retrieveFirstNameWarning()
	{
		String firstnameWarningText =firstnameWarning.getText();
		return firstnameWarningText;
	}
	public String retrieveLastnameWarning()
	{
		String lastnameWarningText = lastnameWarning.getText();
		return lastnameWarningText;
	}
	public String retrieveEmailWarning()
	{
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	public String retrieveTelephoneWarning()
	{
		String telephoneWarningmessageText = telephoneWarning.getText();
		return telephoneWarningmessageText;
	}
	public String retrievePasswordWarning()
	{
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstName,String lastName,String emailText,String telePhoneText,String passwordText) 
	{
		firstNameTextfield.sendKeys(firstName);
		lastNameTextfield.sendKeys(lastName);
		emailTextfield.sendKeys(emailText);
		telephoneTextfield.sendKeys(telePhoneText);
		passwordTextfield.sendKeys(passwordText);
		confirmPasswordTextfield.sendKeys(passwordText);
		privacyPolicyAgreeCheckbox.click();
		continueButton.click(); 
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstName,String lastName,String emailText,String telePhoneText,String passwordText) 
	{
		firstNameTextfield.sendKeys(firstName);
		lastNameTextfield.sendKeys(lastName);
		emailTextfield.sendKeys(emailText);
		telephoneTextfield.sendKeys(telePhoneText);
		passwordTextfield.sendKeys(passwordText);
		confirmPasswordTextfield.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyAgreeCheckbox.click();
		continueButton.click(); 
		return new AccountSuccessPage(driver);
	}
}
