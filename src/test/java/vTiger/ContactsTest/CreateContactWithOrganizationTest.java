package vTiger.ContactsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.GenericUtilities.BaseClass;
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
@Listeners(vTiger.GenericUtilities.ListenerImplementationClass.class)
public class CreateContactWithOrganizationTest extends BaseClass 
{
       
        @Test
        public void createContactWithOrganization() throws Throwable
        {
		
		
		/*Read data from excel sheet - Test data*/
		String ORGNAME = eUtil.getDataFromExcelSheet("Contacts", 4, 3)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.getDataFromExcelSheet("Contacts",4, 2);
		
		//Step 5: navigating to organization
		HomePage hp = new HomePage(driver);
		hp.clickOrganizationLink();
				
		//Step 6: creating organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrglookUpImg();
		Reporter.log("Org look up image clicked");
				
		//Step 7: selecting chemicals
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(ORGNAME);
		Thread.sleep(2000);
				
		//Step 9: validation
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String OrgHeader=oip.getHeaderText();
	    Assert.assertTrue(OrgHeader.contains(ORGNAME));
	    System.out.println(OrgHeader);
		/*if(OrgHeader.contains(ORGNAME))
		{
		  System.out.println("Organization created");
		  System.out.println(OrgHeader);
		}
		else
		{
			System.out.println("fail");
		}*/
		
		//Step 9: Click on contacts link
		hp.clickContactsLink();
		
		//Step 10: Navigate to create contact look up image
		//Assert.fail();
		ContactsPage cp = new ContactsPage(driver);
		Thread.sleep(1000);
		cp.clickOnCreateContactlookUpImg();
		Reporter.log("contact look up image clicked");
		
		//Step 11: Create contact with mandatory information
		CreateNewContactpage cncp = new CreateNewContactpage(driver);
		cncp.createContact(driver, LASTNAME, ORGNAME);
		
		//STEP 17: Validation
		//Assert.fail();
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
		
	}

      @Test
      public void sampleTest()
      {
    	  //Assert.fail();
    	  System.out.println("Sample");
      }
}
