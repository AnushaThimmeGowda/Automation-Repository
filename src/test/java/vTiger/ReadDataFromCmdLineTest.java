package vTiger;

import org.testng.annotations.Test;

public class ReadDataFromCmdLineTest 
{
	@Test
	
	public void read() 
	{
		String USN = System.getProperty("Username");
		System.out.println(USN);
		String PWD = System.getProperty("Password");
		System.out.println(PWD);
		
	}

}
