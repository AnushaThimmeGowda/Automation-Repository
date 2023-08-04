package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateNewCampaignsPage extends WebDriverFileUtility
{
	//Declaration
	@FindBy(name = "campaignname")
	private WebElement CampaignNameEdt;
	
	@FindBy(name = "assigned_user_id")
	private WebElement AssignedToBtn;
	
	@FindBy(name = "campaigntype")
	private WebElement TypeDropDown;
	
	@FindBy(name = "campaignstatus")
	private WebElement StatusDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Initialization
	public CreateNewCampaignsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCampaignNameEdt() {
		return CampaignNameEdt;
	}

	public WebElement getAssignedToBtn() {
		return AssignedToBtn;
	}

	public WebElement getTypeDropDown() {
		return TypeDropDown;
	}

	public WebElement getStatusDropDown() {
		return StatusDropDown;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business Logic
	public void createCampaign(String CAMPAIGNNAME, String CAMPAIGNTYPE, String CAMPAIGNSTATUS)
	{
		CampaignNameEdt.sendKeys(CAMPAIGNNAME);
		dropdownHandling(TypeDropDown, CAMPAIGNTYPE);
		dropdownHandling(StatusDropDown, CAMPAIGNSTATUS);
		SaveBtn.click();
	}
	
	

}
