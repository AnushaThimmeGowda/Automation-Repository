package vTiger.ContactsTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.Test;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateContactsWithOrganization 
{
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
		String ORGNAME = eUtil.getDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.getDataFromExcelSheet("Contacts",4, 2);
		
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
		  System.out.println("Organization created");
		  System.out.println(organizationvalidation);
		}
		else
		{
			System.out.println("fail");
		}
		
		//Step 9: Click on contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 10: Navigate to create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//driver.findElement(By.xpath("//img[title='Create Contact...']")).click();
		
		//Step 11: Create contact with mandatory information
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//STEP 12: Adding organization
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		//driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='select']")).click();
		
		//STEP 13: Switch window to child browser
		wUtil.switchToWindow(driver, "Accounts");
		
		//Step 14: search for the organization
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ ORGNAME +"']")).click();
		
		//STEP 15: Switch control back to parent window
		wUtil.switchToWindow(driver, "Contacts");
		
		//STEP 16: Saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//STEP 17: Validation
		String CONTACTHEADER = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(CONTACTHEADER.contains(LASTNAME))
		{
		  System.out.println("pass");
		  System.out.println(CONTACTHEADER);
		}
		else
		{
			System.out.println("fail");
		}
		
		//STEP 18: Log out
		WebElement logoutimage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOverActions(driver, logoutimage);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfull");
	
	}

}
