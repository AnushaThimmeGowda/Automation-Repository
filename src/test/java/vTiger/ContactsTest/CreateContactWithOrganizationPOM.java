package vTiger.ContactsTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;
import vTiger.ObjectRepository.ContactsInfoPage;
import vTiger.ObjectRepository.ContactsPage;
import vTiger.ObjectRepository.CreateNewContactpage;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateContactWithOrganizationPOM {

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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		Thread.sleep(1000);
		
		//Step 5: navigating to organization
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLink();
				
		//Step 6: creating organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrglookUpImg();
				
		//Step 7: selecting chemicals
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(ORGNAME);
		Thread.sleep(2000);
				
		//Step 9: validation
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String OrgHeader=oip.getHeaderText();
		if(OrgHeader.contains(ORGNAME))
		{
		  System.out.println("Organization created");
		  System.out.println(OrgHeader);
		}
		else
		{
			System.out.println("fail");
		}
		
		//Step 9: Click on contacts link
		hp.clickContactsLink();
		
		//Step 10: Navigate to create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		Thread.sleep(1000);
		cp.clickOnCreateContactlookUpImg();
		
		//Step 11: Create contact with mandatory information
		CreateNewContactpage cncp = new CreateNewContactpage(driver);
		cncp.createContact(driver, LASTNAME, ORGNAME);
		
		//STEP 17: Validation
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String CONTACTHEADER = cip.getHeaderText();
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
		hp.logoutOfApp(driver);
		System.out.println("logged out successfull");
	
	}

}
