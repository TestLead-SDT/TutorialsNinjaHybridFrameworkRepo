package com.tutorialsninja.qa.pageObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailTextfield;
	
	@FindBy (id = "input-password")
	private WebElement passwordTextfield;
	
	@FindBy (xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//ACTIONS METHODS OR BUSINESS LOGIC
	
	  public void enterEmailAddress(String emailTexxt) 
	  {
	  emailTextfield.sendKeys(emailTexxt);
	  } 
	  public void enterPassword(String passWord) 
	  {
		  passwordTextfield.sendKeys(passWord); 
	  } 
	  public AccountPage clickOnLoginButton() 
	  {
		  loginButton.click();
	      return new AccountPage(driver);
	  }
	
	public AccountPage login(String emailTexxt, String passWord)
	{
		emailTextfield.sendKeys(emailTexxt);
		passwordTextfield.sendKeys(passWord);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessage()
	{
		String warningText =emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	
}
