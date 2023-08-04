package vTiger.CampaignsTest;

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

public class CreateCampaignWithTypeAndStatus 
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
		String CAMPAIGNNAME = eUtil.getDataFromExcelSheet("Campaigns", 1, 2)+jUtil.getRandomNumber();
		String CAMPAIGNTYPE = eUtil.getDataFromExcelSheet("Campaigns",1, 3);
		String CAMPAIGNSTATUS = eUtil.getDataFromExcelSheet("Campaigns",1, 4);
		String PRODUCTNAME = eUtil.getDataFromExcelSheet("Products", 1, 2);
		
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
		
		//STEP 5: Navigate to campaigns link
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.linkText("Campaigns")).click();
		
		//STEP 6: Click on create new campaign look up image
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		//STEP 7: Create campaign with mandatory field
		driver.findElement(By.name("campaignname")).sendKeys(CAMPAIGNNAME);
		driver.findElement(By.name("assigned_user_id")).click();
		
		//STEP 8: Select Campaign type dropdown
		driver.findElement(By.xpath("//option[@value='Webinar']")).click();
		
		//STEP 9: Select Campaign status dropdown
		driver.findElement(By.xpath("//option[@value='Active']")).click();
		
		//STEP 10: Choose a product
		driver.findElement(By.xpath("//input[@name='product_name']/following-sibling::img[@title='Select']")).click();
		
		//STEP 11: Switch window to child browser
		wUtil.switchToWindow(driver, "Products");
				
		//STEP 12: Search for the product
		driver.findElement(By.name("search_text")).sendKeys(CAMPAIGNNAME);
				
		driver.findElement(By.name("search")).click();
				
		driver.findElement(By.xpath("//a[text()='"+ PRODUCTNAME +"']")).click();
				
		//STEP 13: Switch control back to parent window
		wUtil.switchToWindow(driver, "Campaigns");
				
		//STEP 14: Saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//STEP 15: Validation
		String CAMPAIGNHEADER = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(CAMPAIGNHEADER.contains(CAMPAIGNNAME))
			{
			  System.out.println("pass");
			  System.out.println(CAMPAIGNHEADER);
		    }
		else
			{
				System.out.println("fail");
			}
				
		//STEP 16: Log out
		WebElement logoutimage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOverActions(driver, logoutimage);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfull");
	}

}
