package vTiger.CampaignsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;
import vTiger.ObjectRepository.CampaignsInfoPage;
import vTiger.ObjectRepository.CampaignsPage;
import vTiger.ObjectRepository.CreateNewCampaignsPage;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateCampaignWithTypeAndStatusPOM 
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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
				
		//Step 5: navigating to organization
		HomePage hp = new HomePage(driver);
		hp.clickMoreLink();
		hp.clickCampaignsLink();
		
		//Step 6: click on create campaign look up image
		CampaignsPage cp = new CampaignsPage(driver);
		cp.clickOnCreateCampaignlookUpImg();
		
		//Step 7: creating organization
		CreateNewCampaignsPage cncp = new CreateNewCampaignsPage(driver);
		cncp.createCampaign(CAMPAIGNNAME, CAMPAIGNTYPE, CAMPAIGNSTATUS);
		
		//Step 8: validation
	    CampaignsInfoPage cip = new CampaignsInfoPage(driver);
	    String CampaignHeader=cip.getCampaignHeaderText();
		if(CampaignHeader.contains(CAMPAIGNNAME))
		{
		  System.out.println("Organization created");
		  System.out.println(CampaignHeader);
		}
		else
		{
			System.out.println("fail");
		}
		
		//STEP 9: Log out
		hp.logoutOfApp(driver);
		System.out.println("logged out successfull");

	}

}
