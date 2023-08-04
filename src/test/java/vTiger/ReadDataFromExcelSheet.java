package vTiger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelSheet {

	public static void main(String[] args) throws Throwable 
	{
		//Step 1: Load the file in java readable format
		FileInputStream fis = new FileInputStream("./src/test/resources/Testdata.xls.xlsx");
		
		//Step 2: Create a workbook for the file loaded
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3: Navigate to required sheet
		Sheet sheet = wb.getSheet("Organizations");
		
		//Step 4: Navigate to required row
		Row row = sheet.getRow(7);
		
		//Step 5: Navigate to required cell
		Cell cell = row.getCell(1);
		
		//Step 6: Capture the value inside the cell
		String data = cell.getStringCellValue();
		System.out.println(data);
		
	}

}
