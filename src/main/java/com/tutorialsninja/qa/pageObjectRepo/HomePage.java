package com.tutorialsninja.qa.pageObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	
	//Constructor with the PAGEFACTORY to initialize the WebElements to avoid the StaleElementReferenceException
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//OBJECTS
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement  myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy (linkText = "Register")
	private WebElement selectRegisterOption;
	
	@FindBy (name = "search")
	private WebElement searchBoxField;
	
	@FindBy (xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	//ACTIONS / BUSSINESS LOGIC
	
	
	  public void clickOnMyAccount() { myAccountDropMenu.click(); }
	  
	  public LoginPage selectLoginOption() { loginOption.click(); return new
	  LoginPage(driver); }
	 
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropMenu.click();
		selectRegisterOption.click();
		return new RegisterPage(driver);
	}
	
	
	public RegisterPage selectRegisterOption()
	{
		selectRegisterOption.click();
		return new RegisterPage(driver);
	}
	public void enterProductIntoSearchBoxField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchForaProduct(String productText)
	{
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
		
		
	}
	
}
