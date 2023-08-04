package vTiger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {

	public static void main(String[] args) throws Throwable 
	{
		//Step 1: Load the file in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Testdata.xls.xlsx");
		
		//Step 2: create a workbook for the file loaded
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3: Create sheet
		Sheet sheet = wb.createSheet("Leads");
		
		//Step 4: Create row
		Row row = sheet.createRow(6);
		
		//Step 5: Create cell
		Cell cell = row.createCell(1);
		
		//Step 6: Set the value to the cell
		cell.setCellValue("JSpiders");
		
		//Step 7: Open the file in java write format
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\Testdata.xls.xlsx");
		
		//Step 8: call the write mode
		wb.write(fos);
		System.out.println("Data added");
		
		//Step 9: Close the workbook
		wb.close();
		System.out.println("Workbook closed");
	}

}
