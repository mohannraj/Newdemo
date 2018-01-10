package project.libraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sun.media.sound.InvalidFormatException;

public class Generic {
	public static int getExcelRowCount(String xlPath, String sheetName)
	{
		int row_Count;
		try
		 {	
		    FileInputStream fis = new FileInputStream(xlPath);
		    Workbook wb = WorkbookFactory.create(fis);
		    Sheet s = wb.getSheet(sheetName);
		    row_Count=s.getLastRowNum();
		 }
		catch(Exception e)
         {
        	row_Count=-1;
         }
	    return row_Count;
	}
	
  public static String getExcelCellValue(String xlPath, String sheetName, int rowNum, int colNum)
   {
	 String cellValue;
	 
	  try
	   {	
		  FileInputStream fis = new FileInputStream(xlPath);
		  Workbook wb = WorkbookFactory.create(fis);
		  Sheet s = wb.getSheet(sheetName);
		  cellValue = s.getRow(rowNum).getCell(colNum).getStringCellValue();
	   }
	  catch(Exception e)
       {
     	  cellValue="";
       }
	  return cellValue;
   }
 
  
  public static String getExcelNumericCellValue(String xlPath, String sheetName, int rowNum, int colNum)
   {
	 String s1;
	 
	  try
	   {	
		  FileInputStream fis = new FileInputStream(xlPath);
		  Workbook wb = WorkbookFactory.create(fis);
		  Sheet s = wb.getSheet(sheetName);
		  int cellValue = (int) s.getRow(rowNum).getCell(colNum).getNumericCellValue();
		   s1=String.valueOf(cellValue); 
	   }
	  catch(Exception e)
      {
    	   s1="";
      }
	    return s1;
  
   }

  public static void select(WebElement e1,String text)
   {
	try
	 {
		 Select select11 = new Select(e1);
		 select11.selectByVisibleText(text);
	 }
	catch(Exception e) { }
   }

  public static void writecelldata(String xlpath,String sheetname,int cell,String data) throws Exception
   {
     Workbook wb = WorkbookFactory.create(new FileInputStream(xlpath));
     Sheet s = wb.getSheet(sheetname);
     int rc = s.getLastRowNum();
     System.out.println(rc);
     for(int i=2;i<=rc;i++)
      {
       s.getRow(i).getCell(cell).setCellValue(data);
      }

     FileOutputStream fileOut = new FileOutputStream(xlpath);
     wb.write(fileOut);
     fileOut.close();
   }
}
 
