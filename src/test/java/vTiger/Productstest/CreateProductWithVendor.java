package vTiger.Productstest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateProductWithVendor 
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
		String PRODUCTNAME = eUtil.getDataFromExcelSheet("Products", 1, 2)+jUtil.getRandomNumber();
		String VENDORNAME = eUtil.getDataFromExcelSheet("Vendor",1, 2);
		
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
		
		//STEP 5: Navigate to Products link
		driver.findElement(By.linkText("Products")).click();
		
		//STEP 6: Click on create new product look up image
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		//STEP 7: Create product with mandatory field
		driver.findElement(By.name("productname")).sendKeys(PRODUCTNAME);
		
		//STEP 8: Adding Vendor
		driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@title='Select']")).click();
		//driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling:://img[@title='Select']")).click();
		
		//STEP 9: Switch window to child browser
		wUtil.switchToWindow(driver, "Vendors");
		
		//STEP 10: Search for the vendor
		driver.findElement(By.name("search_text")).sendKeys(PRODUCTNAME);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ VENDORNAME +"']")).click();
		
		//STEP 11: Switch control back to parent window
		wUtil.switchToWindow(driver, "Products");
		
		//STEP 12: Saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//STEP 13: Validation
		String PRODUCTHEADER = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(PRODUCTHEADER.contains(PRODUCTNAME))
		{
		  System.out.println("pass");
		  System.out.println(PRODUCTHEADER);
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
