package vTiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vTiger.GenericUtilities.WebDriverFileUtility;

public class CreateNewContactpage extends WebDriverFileUtility
{
	//Declaration
	@FindBy(name="lastname")
	private WebElement LastnameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement OrgLookUpImg;
	
	@FindBy(name="search_text")
	private WebElement OrgSearchTxt;
	
	@FindBy(name="search")
	private WebElement OrgSearchBtn;

	//Initialization
	public CreateNewContactpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getLastnameTxt() 
	{
		return LastnameTxt;
	}
	
	public WebElement getSaveBtn() 
	{
		return SaveBtn;
	}
	
	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}

	public WebElement getOrgSearchTxt() {
		return OrgSearchTxt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}

	//Business Logic
	public void createContact(String LASTNAME)
	{
		LastnameTxt.sendKeys(LASTNAME);
		SaveBtn.click();
	}
	
	public void createContact(WebDriver driver, String LASTNAME, String ORGNAME)
	{
		LastnameTxt.sendKeys(LASTNAME);
		OrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		OrgSearchTxt.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+ ORGNAME +"']")).click();
		switchToWindow(driver, "Contacts");
		SaveBtn.click();
		
	}

}
