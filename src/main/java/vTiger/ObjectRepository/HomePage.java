package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverFileUtility;

public class HomePage extends WebDriverFileUtility
{
	//Declaration
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;
	
	@FindBy(linkText="Opportunities")
	private WebElement OpportunitiesLink;
	
	@FindBy(linkText = "More")
	private WebElement MoreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement CampaignsLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;
	
	//Initialisation
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrganizationsLink() {
		return OrganizationsLink;
	}

	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}
	
	public WebElement getMoreLink() {
		return MoreLink;
	}

	public WebElement getCampaignsLink() {
		return CampaignsLink;
	}

	public WebElement getAdministratorImg() {
		return AdministratorImg;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}
	
	//Business Libraray
	/**
	 * This method will click on organization link
	 */
	public void clickOrganizationLink() 
	{
		OrganizationsLink.click();
	}
	
	/**
	 * This method will click on contacts link
	 */
	public void clickContactsLink()
	{
		ContactsLink.click();
	}
	
	/**
	 * This method will click on more link
	 */
	public void clickMoreLink()
	{
		MoreLink.click();
	}
	
	/**
	 * This method will click on campaigns link
	 */
	public void clickCampaignsLink()
	{
		CampaignsLink.click();
	}
	
	/**
	 * This method will logout of the application
	 * @param driver
	 * @throws Throwable
	 */
	public void logoutOfApp(WebDriver driver) throws Throwable
	{
		Thread.sleep(1000);
		mouseOverActions(driver, AdministratorImg);
		Thread.sleep(1000);
		SignOutLink.click();	
	}
	

	
	

}
