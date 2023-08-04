package vTiger.GenericUtilities;

import org.testng.ITestContext;

/**
 * This class provides implementation for ITestListener interface
 */
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener
{
	ExtentReports reports;
	ExtentTest test;

	public void onTestStart(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println("Execution started"+methodName);
		test=reports.createTest(methodName);
	}

	public void onTestSuccess(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		//System.out.println("Pass"+methodName);
		test.log(Status.PASS, "Pass"+methodName);
	}

	public void onTestFailure(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		//System.out.println("fail"+methodName);
		test.log(Status.FAIL, "fail"+methodName);
		
		WebDriverFileUtility wu = new WebDriverFileUtility();
		JavaUtility ju = new JavaUtility();
		
		String screenshotName = methodName+ju.getSystemDateInFormat();
		
		//Take screen shot for failed test scripts- to attach with bug raising
		try {
			String path = wu.takeScreenshot(BaseClass.sdriver, screenshotName);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(result.getThrowable());//used to print he exceptions
		test.log(Status.INFO, result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println("Skip"+methodName);

		System.out.println(result.getThrowable());//used to print he exceptions

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) 
	{
		System.out.println("Execution Started");
		
		//Configure the extent report
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlReport.config().setDocumentTitle("vTiger execution reports");
		htmlReport.config().setReportName("Build1 vTiger execution reports");
		htmlReport.config().setTheme(Theme.DARK);
		
		//Report Generation
		reports=new ExtentReports();
		reports.attachReporter(htmlReport);
		reports.setSystemInfo("Base Browser", "Chrome");
		reports.setSystemInfo("Base Platform", "Testing-Env");
		reports.setSystemInfo("Base URl", "http://localhost:8888");
		reports.setSystemInfo("Base OS", "Windows");
		reports.setSystemInfo("Reporter", "Anusha");
	}

	public void onFinish(ITestContext context) 
	{
		System.out.println("Execution Ended");
		//Report Generation
		reports.flush();
	}

}
