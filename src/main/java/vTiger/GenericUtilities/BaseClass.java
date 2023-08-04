package vTiger.GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vTiger.ObjectRepository.HomePage;
import vTiger.ObjectRepository.LoginPage;

public class BaseClass 
{
	public JavaUtility jUtil = new JavaUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public WebDriverFileUtility wUtil = new WebDriverFileUtility();
	
	public WebDriver driver = null;
	
	//Only used for listeners to take screen shot
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = "RegressionSuite")
	public void bsConfig()
	{
		System.out.println("====== db connection successful ======");
	}
	
	//@Parameters("browser") //holds a value
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig() throws Throwable //String BROWSER
	{
		//Read browser name and URL from property File
		String BROWSER = pUtil.getDataFormPropertyFile("browser");
		String URL = pUtil.getDataFormPropertyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + " === Browser launched ===");

		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + " === Browser launched ===");}
			else if(BROWSER.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
				driver.manage().window().maximize();
				System.out.println(BROWSER+"---browser launched");
			}
		 else {
			System.out.println("==== invalid Browser name ====");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForTheElementsToLoad(driver);

		//Only used for listeners to take screen shot
		sdriver=driver;
		
		//Load the URL
		driver.get(URL);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable
	{
		//read username and passowrd from property file
		String USERNAME = pUtil.getDataFormPropertyFile("username");
		String PASSWORD = pUtil.getDataFormPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("Login Successful");
	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() throws Throwable
	{
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		
		System.out.println("==== Logout Successful ====");
	}
		
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println(" ========== Browser Closed ========");
	}
		
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("====== db connection closed ======");
	}


}
