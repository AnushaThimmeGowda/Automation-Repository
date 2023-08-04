package vTiger.GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provides implementation for IretryAnalyser interface
 * @author Computer
 *
 */

public class RetryAnalyserImplementation implements IRetryAnalyzer
{
	int count=1;
    int retryCount=3;
                                     //pass //fail
	public boolean retry(ITestResult result) 
	{
		//1<=3:true 2<=3:true 3<=3:true 4<=3:false
		while(count<=retryCount)
		{
			count++;  //2 3 4
			return true;  //retry retry retry
		}
		return false;  //do not retry
	}

}
