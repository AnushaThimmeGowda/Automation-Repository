package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage 
{
	//Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement ContactHeaderTxt;
	
	//Initialization
    public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getContactHeaderTxt()
	{
		return ContactHeaderTxt;
	}

	//Business Logic
	/**
	 * This method will capture the header text and return it to caller
	 * @return
	 */
	public String getHeaderText()
	{
		return ContactHeaderTxt.getText();
	}	

}
