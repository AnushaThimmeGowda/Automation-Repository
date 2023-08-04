package vTiger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesPractice {

	public static void main(String[] args) throws Throwable 
	{
		//step 1: Load the property file into java
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//step 2: Create object for the properties file
		Properties pObj= new Properties();
		
		//step 3: load the document into properties class 
		pObj.load(fis);
		
		//step 4: provide the key and get the values
		String browser = pObj.getProperty("browser");
		String usn = pObj.getProperty("username");
		String pwd = pObj.getProperty("password");
		String url = pObj.getProperty("url");
		System.out.println(browser);
		System.out.println(url);
		System.out.println(usn);
		System.out.println(pwd);
		Object key2 = pObj.setProperty("browser1", "edgebrowser");

	}

}
