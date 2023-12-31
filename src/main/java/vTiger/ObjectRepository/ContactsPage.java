package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage 
{
    //Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	//)@FindBy(xpath="//img[@alt='Create Contact...]")
	private WebElement CreateContactLookUpImg;
	
	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateContactLookUpImg() 
	{
		return CreateContactLookUpImg;
	}
	
	//Business logic
	/**
	 * This method will click on create contact look up image
	 */
	public void clickOnCreateContactlookUpImg() 
	{
		CreateContactLookUpImg.click();
	}
	

	
	
}
