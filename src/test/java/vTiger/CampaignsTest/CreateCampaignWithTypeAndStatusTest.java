package vTiger.CampaignsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import vTiger.GenericUtilities.BaseClass;
import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;
import vTiger.ObjectRepository.CampaignsInfoPage;
import vTiger.ObjectRepository.CampaignsPage;
import vTiger.ObjectRepository.CreateNewCampaignsPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;

public class CreateCampaignWithTypeAndStatusTest extends BaseClass
{
    @Test
	public void createCampaignWithTypeAndStatus() throws Throwable
	{
		/*Read data from excel sheet - Test data*/
		String CAMPAIGNNAME = eUtil.getDataFromExcelSheet("Campaigns", 1, 2)+jUtil.getRandomNumber();
		String CAMPAIGNTYPE = eUtil.getDataFromExcelSheet("Campaigns",1, 3);
		String CAMPAIGNSTATUS = eUtil.getDataFromExcelSheet("Campaigns",1, 4);
		String PRODUCTNAME = eUtil.getDataFromExcelSheet("Products", 1, 2);
		
		
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
		
	}

}
