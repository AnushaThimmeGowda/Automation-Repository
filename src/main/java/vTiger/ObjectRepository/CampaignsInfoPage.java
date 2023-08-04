package vTiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsInfoPage 
{
	//Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement CampaignHeaderTxt;
	
	//Initialization
	public CampaignsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCampaignHeaderTxt() {
		return CampaignHeaderTxt;
	}
	
	//Business Library
	public String getCampaignHeaderText()
	{
		return CampaignHeaderTxt.getText();
	}
	
	

}
