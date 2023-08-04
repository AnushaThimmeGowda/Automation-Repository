package vTiger.GenericUtilities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

//description
/**
 * This class consists of all generic methods related to java
 * @author Computer
 *
 */
public class JavaUtility 
/**
 * This method will generate a random number for every execution
 * @return random number
 */
{
	public int getRandomNumber()
	{
		Random r = new Random();
		int ranNo = r.nextInt(1000);
		return ranNo;
	}

/**
 * This method will generate a date
 * @return current date
 */
	
	public String getSystemDate()
	{
				Date d=new Date();
				String date = d.toString();
				return date;
	}
	
	/**
	 * This method generate the system date in specific format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		Date d=new Date();
		String[] date = d.toString().split(" ");
		String currentdate = date[2];
		String month = date[1];
		String year = date[5];
		String time = date[3].replace(":", "-");
		String dateInFormat = currentdate+"_"+month+"_"+year+"_"+time;
		return dateInFormat;
	}

}
