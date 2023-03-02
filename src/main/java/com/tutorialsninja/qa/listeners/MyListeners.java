package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tuturialsninja.qa.Utils.ExtentReporter;
import com.tuturialsninja.qa.Utils.Utilities;

public class MyListeners implements ITestListener
{
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) 
	{
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result)
	{
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" started Executing");
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.log(Status.PASS, testName+" got successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("ScreenShot Taken... ");
		
	WebDriver driver=null;
	try {
		driver = (WebDriver)	result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Capturing Screenshot method()
	String destPath = Utilities.captureScreenShot(driver, testName);
		
		//ADDING THE FAILED TEST SCREENSHOT TO THE EXTENTREPORT
		extentTest.addScreenCaptureFromPath(destPath);
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got failed...");
	}
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got Skipped...");
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html\\";
		File extentReportfile = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportfile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
