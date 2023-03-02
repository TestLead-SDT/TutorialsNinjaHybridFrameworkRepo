package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.BaseClass.BaseClass;
import com.tutorialsninja.qa.pageObjectRepo.HomePage;
import com.tutorialsninja.qa.pageObjectRepo.SearchPage;

//Update commit added more details
public class Search extends BaseClass
{
	HomePage homePage;
	SearchPage searchPage;
	
	public Search() throws Throwable 
	{
		super();
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(pro.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		searchPage = homePage.searchForaProduct(dataProp.getProperty("validProductName"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product HP is not displayed in the search results!");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		//dataProp.getProperty("NoProductMatchesSearchResult")
		searchPage = homePage.searchForaProduct(dataProp.getProperty("inValidProductName"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), "abcd","No product message in search result is not displayed!");
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutanyProduct()
	{
		searchPage=homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("NoProductMatchesSearchResult"),"No product message in search result is not displayed!");
	}
	
	
}
