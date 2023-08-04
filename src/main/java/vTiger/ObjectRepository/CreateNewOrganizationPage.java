package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateNewOrganizationPage extends WebDriverFileUtility
{
	//Declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement IndustryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement TypeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}

	public WebElement getTypeDropDown() {
		return TypeDropDown;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business Logic
	/**
	 * This method will create new organization with mandatory field
	 * @param ORGNAME
	 */
	public void createOrganization(String ORGNAME)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	
	/**
	 * This method will create new organization with industry dropdown
	 * @param ORGNAME
	 * @param INDUSTRY
	 */
	public void createOrganization(String ORGNAME, String ORGINDUSTRY)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		dropdownHandling(IndustryDropDown, ORGINDUSTRY);
		SaveBtn.click();
	}
	
	public void createOrganization(String ORGNAME, String ORGINDUSTRY, String ORGTYPE)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		dropdownHandling(IndustryDropDown, ORGINDUSTRY);
		dropdownHandling(TypeDropDown, ORGTYPE);
		SaveBtn.click();
	}
	

}
