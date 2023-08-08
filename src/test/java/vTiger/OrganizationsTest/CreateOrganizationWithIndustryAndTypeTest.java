package vTiger.OrganizationsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericUtilities.BaseClass;
import vTiger.GenericUtilities.ExcelFileUtility;
import vTiger.GenericUtilities.JavaUtility;
import vTiger.GenericUtilities.PropertyFileUtility;
import vTiger.GenericUtilities.WebDriverFileUtility;
import vTiger.ObjectRepository.CreateNewOrganizationPage;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;
import vTiger.ObjectRepository.OrganizationInfoPage;
import vTiger.ObjectRepository.OrganizationsPage;

public class CreateOrganizationWithIndustryAndTypeTest extends BaseClass
{
    @Test(groups = "RegressionSuite")
    public void createOrganizationWithIndustryAnstype() throws Throwable
    {
	/*Read data from excel sheet - Test data*/
	String ORGNAME = eUtil.getDataFromExcelSheet("Organizations", 10, 2)+jUtil.getRandomNumber();
	String ORGINDUSTRY = eUtil.getDataFromExcelSheet("Organizations",10, 3);
	String ORGTYPE = eUtil.getDataFromExcelSheet("Organizations",10, 4);
	
	
	//Step 5: navigating to organization
	HomePage hp = new HomePage(driver);
	hp.clickOrganizationLink();
	
	//Step 6: click on create organization look up image
	OrganizationsPage op = new OrganizationsPage(driver);
	op.clickOnCreateOrglookUpImg();
			
	//Step 7: creating organization
	CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
	cnop.createOrganization(ORGNAME, ORGINDUSTRY, ORGTYPE);
			
	//Step 8: validation
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
	
}
    @Test
    public void sampletest()
    {
    	System.out.println("Sample test");
    	System.out.println("Hello");
    }

}

   

