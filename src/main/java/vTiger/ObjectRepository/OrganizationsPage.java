package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	//Declaration
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement CreateOrgLookUpImg;
	
	//Initialization
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateOrgLookUpImg() 
	{
		return CreateOrgLookUpImg;
	}
	
	//Business Libraray
	/**
	 * This method will click on create organization look up image
	 */
	public void clickOnCreateOrglookUpImg() 
	{
		CreateOrgLookUpImg.click();
	}
	
	
	

}
