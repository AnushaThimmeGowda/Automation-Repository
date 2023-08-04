package vTiger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class VtigerScenario3 {

	public static void main(String[] args) throws Throwable 
	{
		//browser launching
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		
		//loading url
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		
		//logging into application
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		//navigating to organization
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		
		//creating organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("(//input[@class='detailedViewTextBox'])[1]")).sendKeys("JSpiders Platform2");
		
		//selecting chemicals in industry dropdown
		driver.findElement(By.xpath("//option[@value='Chemicals']")).click();
		//driver.findElement(By.xpath("(//td[@valign='bottom'])[2]")).click();
		
		//saving
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(1000);
		
		//validation
		String organizationvalidation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationvalidation.contains("JSpiders Platform2"))
		{
			System.out.println("pass");
			System.out.println(organizationvalidation);
		}
		else
		{
			System.out.println("fail");
		}
		
		//logout of application
		WebElement logoutimage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(logoutimage).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfull");
		

	}

}
