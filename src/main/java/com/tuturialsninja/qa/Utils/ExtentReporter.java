package com.tuturialsninja.qa.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter
{
	static public ExtentReports generateExtentReport()  
	{
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReportFile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("TutorialsNinja Test Automation Results");
		sparkreporter.config().setDocumentTitle("TN Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		
		extentReport.attachReporter(sparkreporter);
		
		Properties pro = new Properties();
		File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\Config\\config.properties");
		
		try 
		{
			FileInputStream fis = new FileInputStream(configFile);
			pro.load(fis);
		} catch (Throwable e) 
		{
			e.getStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", pro.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", pro.getProperty("browserName"));
		extentReport.setSystemInfo("Email", pro.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", pro.getProperty("validPassword"));
		
		//Environment Setup
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", "QA ABINASH DAS");
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}
