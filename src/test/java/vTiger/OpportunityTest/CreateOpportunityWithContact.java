package vTiger.OpportunityTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateOpportunityWithContact 
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
		String OPPORTUNITYNAME = eUtil.getDataFromExcelSheet("Opportunity", 1, 2)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.getDataFromExcelSheet("Contacts",1, 2);
		
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
		/*Create Contact*/
		//Step 5: Click on contacts link
		driver.findElement(By.linkText("Contacts")).click();
				
		//Step 6: Navigate to create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//driver.findElement(By.xpath("//img[title='Create Contact...']")).click();
				
		//Step 7: Create contact with mandatory information
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//STEP 8: Save the contact
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*Create Opportunities*/
				
		//Step 9: Navigate to Opportunity
		driver.findElement(By.linkText("Opportunities")).click();
		
		//STEP 10: Click on create new opportunity look up image
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		
		//STEP 11: Create opportunity with mandatory fields
		driver.findElement(By.name("potentialname")).sendKeys(OPPORTUNITYNAME);
		
		//STEP 12: Selecting contact from related dropdown
		driver.findElement(By.name("related_to_type")).click();
		driver.findElement(By.xpath("//option[@value='Contacts']")).click();
		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img[@title='Select']")).click();
		
		//STEP 13: Switch window to child browser
		wUtil.switchToWindow(driver, "Contacts");
				
		//Step 14: search for the contact
		driver.findElement(By.name("search_text")).sendKeys(LASTNAME);
				
		driver.findElement(By.name("search")).click();
				
		driver.findElement(By.xpath("//a[text()='"+ LASTNAME +"']")).click();
				
		//STEP 15: Switch control back to parent window
		wUtil.switchToWindow(driver, "Potentials");
				
		//STEP 16: Saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//STEP 17: Validation
		String OPPORTUNITYHEADER = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(OPPORTUNITYHEADER.contains(OPPORTUNITYNAME))
			{
			  System.out.println("pass");
			  System.out.println(OPPORTUNITYHEADER);
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
