package vTiger.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all reusable methods related to web driver actions
 * @author Computer
 *
 */

public class WebDriverFileUtility 
{
	
/**
 * This method will maximize the window
 * @param driver
 */
	public void maximizeWindow(WebDriver driver)  //updated driver reference
	{
		driver.manage().window().maximize();	
	}
	
/**
 * This method will minimize the window
 * @param driver
 */
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
/**
 * This method will wait for all the findElement and findElements operations to be performed
 * @param driver
 */
	
	public void waitForTheElementsToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
/**
 * This method will wait until the specified element is visible in DOM
 * @param driver
 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
/**
 * This method will handle drop down using index
 * @param element
 * @param index
 */
	
	public void dropdownHandling(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
/**
 * This method will handle drop down using value
 * @param element
 * @param value
 */
	public void dropdownHandling(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
/**
 * This method will handle drop down using visible text
 * @param text
 * @param element
 */
	public void dropdownHandling(String text,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
/**
 * This method will perform mouse over actions on a target element
 * @param driver
 * @param element
 */
	
	public void mouseOverActions(WebDriver driver, WebElement element) 
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
/**
 * This method will double click anywhere on the webpage
 * @param driver
 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
/**
 * This method will double click on a web element
 * @param element
 * @param driver
 */
	public void doubleClickAction(WebElement element, WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
/**
 * This method will do right click on particular web element
 * @param driver
 * @param element
 */
	public void rightClickAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

/**
 * This method will do right click on anywhere on web page
 * @param driver
 */
	public void rightClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick();
	}
	
/**
 * This method will perform drag and drop action
 * @param driver
 * @param source
 * @param target
 */
	public void dragAndDropAction(WebDriver driver, WebElement source, WebElement target)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}
	
/**
 * This method will do click and hold action an anywhere on web page 
 * @param driver
 */
	public void clickAndHoldAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.clickAndHold().perform();
	}
	
/**
 * This method will do click and hold action an particular element
 * @param target
 * @param driver
 */
	public void clickAndHoldAction(WebElement target, WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.clickAndHold(target).perform();
	}
	
/**
 * This method is used to move the cursor anywhere on webpage based on offset values
 * @param driver
 * @param xOffset
 * @param yOffset
 */
	public void moveAcrossWebPage(WebDriver driver, int xOffset, int yOffset)
	{
		Actions act = new Actions(driver);
		act.moveByOffset(xOffset, yOffset).perform();
	}
	
/**
 * This method will scroll vertically for 500 pixcels
 * @param driver
 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(o, 500)","");
	}
	
/**
 * This method will scroll vertically until the element reference
 * @param driver
 * @param element
 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		int y=element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+");", element);
		//js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
/**
 * This method will accept the alert popup
 * @param driver
 */
	public void acceptAler(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
/**
 * This method will cancel the confirmation popup
 * @param driver
 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
/**
 * This method wll enter the text in prompt popup
 * @param driver
 * @param text
 */
	public void sendTextToAlert(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
/**
 * This method will capture the alert message and return to the caller
 * @param driver
 * @return
 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
/**
 * This method will handle frame based on index
 * @param driver
 * @param index
 */
	public void handleFrames(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
/**
 * This method will handle the frame based on value of name or ID attribute
 * @param driver
 * @param nameOrID
 */
	public void handleFrames(WebDriver driver, String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	
/**
 * This method will handle frame based on webelement
 * @param driver
 * @param element
 */
	public void handleFrames(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
/**
 * This method will help to switch the control to immediate parent
 * @param driver
 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}

/**
 * This method will help to switch the control to current page
 * @param driver
 */
	public void switchToDefaultContent(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}

/**
 * This method will switch the selenium control from parent to child or child to parent based on partial window title
 * @param driver
 * @param partialWinID
 */
	public void switchToWindow(WebDriver driver, String partialWinTitle)
	{
		//Step 1: Capture all the window IDs
		Set<String> allWinID = driver.getWindowHandles();
		
		//Step 2: Iterate through individual IDs
		for(String WinID:allWinID)
		{
			//Step 3: Switch to each ID and capture the title
			String currentTitle = driver.switchTo().window(WinID).getTitle();
			
			//Step 4: Compare the title with required reference
			if(currentTitle.contains(partialWinTitle))
			{
				break;
			}
	    }
		
	}
	
/**
 * This method will take the screenshot and return absolute path of it
 * @param driver
 * @param screenshotName
 * @return path
 * @throws Throwable
 */
	public String takeScreenshot(WebDriver driver, String screenshotName) throws Throwable
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\Screenshot\\"+screenshotName+".png");
		Files.copy(src, dest);//This class is from commons IO tool
		return dest.getAbsolutePath();//Attach the screenshot to extent reports
	}
}
