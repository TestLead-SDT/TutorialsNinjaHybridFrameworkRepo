package com.tutorialsninja.qa.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tuturialsninja.qa.Utils.Utilities;

public class BaseClass 
{
	WebDriver driver ;
	public Properties pro ;
	public Properties dataProp;
	
	//CONSTRUCTOR TO LOAD IN EVERY CLASS 
	public BaseClass() throws Throwable
	{
		pro= new Properties();
		dataProp = new Properties();
		
		File proFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\Config\\config.properties");
		File dataPropFile  = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\TestData\\testdata.properties");
		
		try 
		{
			FileInputStream datafis = new FileInputStream(dataPropFile);
			dataProp.load(datafis);
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
		
		try
		{
		FileInputStream fis = new FileInputStream(proFile);
		pro.load(fis);
		}
		catch (Throwable e) 
		{
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName)
	{
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) 
		{
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("safari")) 
		{
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(pro.getProperty("url"));
		
		return driver;
	}
}
