package com.tuturialsninja.qa.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utilities
{
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	
	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "abinashdas.mech"+timeStamp+"@gmail.com";
	}
	
	
	/**
	 * Static Method to receive the data from the excel file and use whenever needed
	 * @param sheetName
	 * @return
	 */
	public static Object[][] getTestDataFromExcel(String sheetName)  
	{
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\TestData\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook book = null;
		try 
		{
			FileInputStream fis = new FileInputStream(file);
			 book = new XSSFWorkbook(fis);	
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet =book.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();  //Get the total no of ROWS in the excel sheet
		int cols = sheet.getRow(0).getLastCellNum(); //Get the no of cells measn total COLUMNS
		
		Object [][] data = new Object[rows][cols];
		
		for(int i=0; i<rows; i++)
		{
			XSSFRow row =  sheet.getRow(i+1);
			
			for(int j=0; j<cols; j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType)
				{
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenShot(WebDriver driver, String testName)
	{
		//TAKING THE SCREENSHOT FOR THE FAILED TEST AND STORING IT IN THE FOLDER 'ScreenShots'
		TakesScreenshot screenShot =(TakesScreenshot)driver;
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
		try 
		{
			FileHandler.copy(srcFile, new File(destPath));
		} catch (Throwable e) 
		{
			e.printStackTrace();
		} 
		return destPath;
	}
	
	
}
