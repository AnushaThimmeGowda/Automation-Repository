package vTiger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationWithIndustryUsingDDT {

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver=null;
		
		Random r= new Random();
		int random = r.nextInt(1000);
		
		//Step 1: Read all the necessary data
		/*Read data from property file-common data*/
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String Browser = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USN = pObj.getProperty("username");
		String PWD = pObj.getProperty("password");
		
		/*Read data from excel sheet - Test data*/
		FileInputStream fisE = new FileInputStream(".\\src\\test\\resources\\Testdata.xls.xlsx");
		Workbook wb = WorkbookFactory.create(fisE);
		Sheet sheet = wb.getSheet("organizations");
		String orgName = sheet.getRow(4).getCell(2).getStringCellValue()+random;
		String industry = sheet.getRow(4).getCell(3).getStringCellValue();
		
		//Step 2: Launch the browser-driver is acting based on run time-run time polymorphism
		if(Browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			System.out.println(Browser+"---browser launched");
		}
		else if(Browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println(Browser+"---browser launched");
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		//Step 3: loading url
				driver.get(URL);
				
				//Step 4: logging into application
				driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USN);
				driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PWD);
				driver.findElement(By.xpath("//input[@id='submitButton']")).click();
				
				Thread.sleep(1000);
				//Step 5: navigating to organization
				driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
				
				//Step 6: creating organization
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//input[@class='detailedViewTextBox'])[1]")).sendKeys(orgName);
				
				//Step 7: selecting chemicals in industry dropdown
				driver.findElement(By.xpath("//option[@value='Chemicals']")).click();
				//driver.findElement(By.xpath("(//td[@valign='bottom'])[2]")).click();
				
				//Step 8: saving
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				Thread.sleep(2000);
				
				//Step 9: validation
				String organizationvalidation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(organizationvalidation.contains(orgName))
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
				Actions act = new Actions(driver);
				act.moveToElement(logoutimage).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("logged out successfull");
				
				//Step 11: Close the browser
				driver.close();
				
	}
	
	

}
