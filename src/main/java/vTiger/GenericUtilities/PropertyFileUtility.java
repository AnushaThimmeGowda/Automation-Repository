package vTiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * This class consists of generic methods related to property file
 * @author Computer
 *
 */

public class PropertyFileUtility 
{
	
/**
 * This method reads data from property file based on given key
 * @param key
 * @return value
 * @throws Throwable
 */
	public String getDataFormPropertyFile(String key) throws Throwable
	{
		//FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		FileInputStream fis = new FileInputStream(IConstants.propertyFilePath);
		Properties p = new Properties();
		p.load(fis);
		String value=p.getProperty(key);
		return value;
	}

}
