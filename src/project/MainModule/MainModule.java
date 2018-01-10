package project.MainModule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import project.libraries.Generic;

public class MainModule {
	WebDriver driver; 
	
	@Test
	public void preTest() throws Exception
	{
	    String xlPath="./Exceldata/search.xlsx";
	    String sheetName="Sheet1";
		
	    System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");	
		driver = new ChromeDriver();
		driver.get("file:///c:/Users/MohanRaj/Desktop/search.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(xlPath));
		Sheet s = wb.getSheet(sheetName);
		int rc = s.getLastRowNum();
		
		int count = Generic.getExcelRowCount(xlPath, sheetName);
		for(int i=2; i<=count;i++)
		 {
     		String data = Generic.getExcelCellValue(xlPath, sheetName, i, 14);
			System.out.println(data);
			WebElement e1 = driver.findElement(By.id("Search By"));
            Generic.select(e1, data);
            
            String data2 = Generic.getExcelNumericCellValue(xlPath, sheetName, i, 15);
			System.out.println(data2);
			WebElement e12 = driver.findElement(By.id("Search By Values"));
            Generic.select(e12, data2); 
            
            String data3 = Generic.getExcelCellValue(xlPath, sheetName, i, 16);
			System.out.println(data3);
			WebElement e13 = driver.findElement(By.id("Primary Service Type"));
            Generic.select(e13, data3);
            
            String data4 = Generic.getExcelCellValue(xlPath, sheetName, i, 17);
			System.out.println(data4);
			WebElement e14 = driver.findElement(By.id("Primary Service Status"));
            Generic.select(e14, data4);
            
            String data5 = Generic.getExcelCellValue(xlPath, sheetName, i, 18);
			System.out.println(data5);
			WebElement e15 = driver.findElement(By.id("Secondary Service"));
            Generic.select(e15, data5);
            
            String data6 = Generic.getExcelCellValue(xlPath, sheetName, i, 19);
			System.out.println(data6);
			WebElement e16 = driver.findElement(By.id("Secondary Service Status"));
            Generic.select(e16, data6);
            
            String data7 = Generic.getExcelCellValue(xlPath, sheetName, i, 21);
			System.out.println(data7);
			WebElement e17 = driver.findElement(By.id("Data Range"));
            Generic.select(e17, data7);

            String data8 = Generic.getExcelNumericCellValue(xlPath, sheetName, i, 23);
			System.out.println(data8);;
			WebElement e18 = driver.findElement(By.id("Data Range"));
            Generic.select(e18, data8);
            
            driver.findElement(By.id("abc")).click();
            
            String text = driver.findElement(By.id("abcd")).getText();
            int intval=Integer.parseInt(text);
            System.out.println(intval);
            s.getRow(i).getCell(26).setCellValue(intval);
            FileOutputStream fileOut = new FileOutputStream(xlPath);
            wb.write(fileOut);
            fileOut.close();
          // break;
		}
	 }	
	
	@Test(priority=1)
	public void ValidateResults() throws Exception
	 {
		String xlPath="./Exceldata/search.xlsx";
		String sheetName = "Sheet1";
		SoftAssert sa = new SoftAssert();
		
		int rC =12;
		for(int i=2;i<rC;i++)
		 {
		  try 
		   {
			 String s1=Generic.getExcelNumericCellValue(xlPath, sheetName, i, 26);
			 String s2=Generic.getExcelNumericCellValue(xlPath, sheetName, i, 27);
			 int aval=Integer.parseInt(s1);
			 int eval=Integer.parseInt(s2);	 
		     if(aval==eval)
		      {
		        Reporter.log(aval+" ="+eval+"  --> Pass",true);
		      }
		     else
		      {
		     	Reporter.log(aval+"="+eval+"  --> Fail",true);
		      }
		   }
		  catch(Exception e) 
		   {
			 e.printStackTrace();
		   }
		} 
	 }

	@AfterTest
	public void postTest()
	{
		driver.close();
	}
}
		
