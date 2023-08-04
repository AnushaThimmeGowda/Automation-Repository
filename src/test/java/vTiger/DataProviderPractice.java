package vTiger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice 
{
  @Test(dataProvider="getData")
  public void addToCartTest(String name, int price,String model)
  {
	System.out.println("Phone is "+name+ " price is " +price+ " model is " +model);  
  }
  
  @DataProvider
  public Object[][] getData()
  {
	  Object[][] data = new Object [3][3];
	  
	  data[0][0]="IPhone";
	  data[0][1]=20000;
	  data[0][2]="S13";
	  
	  data[1][0]="Samsung";
	  data[1][1]=25000;
	  data[1][2]="A13";
	  
	  data[2][0]="Vivo";
	  data[2][1]=30000;
	  data[2][2]="V13";
	  
	  return data;
  }
  
 }
