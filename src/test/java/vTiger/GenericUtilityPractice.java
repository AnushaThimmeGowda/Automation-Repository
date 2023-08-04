package vTiger;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws Throwable 
	{
		JavaUtility jutil = new JavaUtility();
		int value = jutil.getRandomNumber();
		System.out.println(value);
		
		System.out.println(jutil.getSystemDate());
		
		System.out.println(jutil.getSystemDateInFormat());
		
		PropertyFileUtility pfu = new PropertyFileUtility();
		String data = pfu.getDataFormPropertyFile("password");
		System.out.println(data);
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		String readValue = eutil.getDataFromExcelSheet("Organizations", 1, 2);
		System.out.println(readValue);
		
		eutil.getWriteDataToExcelSheet("Source", 9, 0, "Internet");
		System.out.println("Data added");
		
	}

}
