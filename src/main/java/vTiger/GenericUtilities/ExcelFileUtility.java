package vTiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *This class consists of generic methods related to Excel file
 * @author Computer
 *
 */

public class ExcelFileUtility 

/**
 * This method will read the data from excel sheet
 * 
 */
{
	public String getDataFromExcelSheet(String sheetName, int rownum, int cellnum) throws Throwable 
	{
		//FileInputStream fisE = new FileInputStream(".\\src\\test\\resources\\Testdata.xls.xlsx");
		FileInputStream fisE = new FileInputStream(IConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fisE);
		String data = wb.getSheet(sheetName).getRow(rownum).getCell(cellnum).getStringCellValue();
		return data;
	}
	
/**
 * This method will write data into the excel sheet
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @param data
 * @throws Throwable
 */
	
	public void getWriteDataToExcelSheet(String sheetName, int rowNum, int cellNum, String data) throws Throwable
	{
		FileInputStream fisEW = new FileInputStream(IConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fisEW);
		wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(data);
		
		FileOutputStream fosE = new FileOutputStream(IConstants.excelFilePath);
		wb.write(fosE);
		wb.close();
	}

	public Object[][] readMultipleData(String sheetName) throws Throwable 
	{
		FileInputStream fisER = new FileInputStream(IConstants.excelFilePath);
		Workbook wb = WorkbookFactory.create(fisER);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[lastRow][lastCell];
		
		for(int i=0; i<lastRow; i++)
		{
			for(int j=0; j<lastCell; j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		
		return data;
		
	}
}
