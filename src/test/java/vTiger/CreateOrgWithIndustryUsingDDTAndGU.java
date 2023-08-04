package vTiger;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateOrgWithIndustryUsingDDTAndGU 
{

	private static final CharSequence PWD = null;

	public static void main(String[] args) throws Throwable 
	{
        PropertyFileUtility pUtil = new PropertyFileUtility();
        ExcelFileUtility eUtil = new ExcelFileUtility();
        WebDriverFileUtility wUtil = new WebDriverFileUtility();
        JavaUtility jUtil = new JavaUtility();
        
		WebDriver driver=null;
		
		//Step 1: Read all the necessary data
		/*Read data from property file-common data*/
		String BROWSER=pUtil.getDataFormPropertyFile("browser");
		String URL=pUtil.getDataFormPropertyFile("url");
		String USERNAME=pUtil.getDataFormPropertyFile("username");
		String PASSWORD=pUtil.getDataFormPropertyFile("password");
		
		/*Read data from excel sheet - Test data*/
		String ORGNAME = eUtil.getDataFromExcelSheet("Organizations", 4, 2)+jUtil.getRandomNumber();
		String INDUSTRY = eUtil.getDataFromExcelSheet("Organizations",4, 3);
		
		//Step 2: Launch the browser-driver is acting based on run time-run time polymorphism
		if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			System.out.println(BROWSER+"---browser launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println(BROWSER+"---browser launched");
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForTheElementsToLoad(driver);
		
		//Step 3: loading url
		driver.get(URL);
				
		//Step 4: logging into application
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
				
		Thread.sleep(1000);
		//Step 5: navigating to organization
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
				
		//Step 6: creating organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='detailedViewTextBox'])[1]")).sendKeys(ORGNAME);
				
		//Step 7: selecting chemicals in industry dropdown
		driver.findElement(By.xpath("//option[@value='Chemicals']")).click();
		//driver.findElement(By.xpath("(//td[@valign='bottom'])[2]")).click();
				
		//Step 8: saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		Thread.sleep(2000);
				
		//Step 9: validation
		String organizationvalidation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationvalidation.contains(ORGNAME))
		{
		  System.out.println("pass");
		  System.out.println(organizationvalidation);
		}
		else
		{
			System.out.println("fail");
		}
				
		//Step 10: logout of application
		WebElement logoutimage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOverActions(driver, logoutimage);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfull");
				
		//Step 11: Close the browser
		//driver.close();

	}

}
