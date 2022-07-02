package com.qa.automation.test;
import java.io.FileNotFoundException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automation.pages.HomePage;
import com.qa.automation.utility.getData_FromExcel;

public class TC002_Demo1Test extends BaseTest{
	
//Hashtable<String,String> testData=null;
	
	@DataProvider(name="readtestData")
	public Object[][] getTestdata(){
		Object[][] testData= getData_FromExcel.getDataFrom_excel("Sheet1", this.getClass().getSimpleName().toString());
		try {
			getData_FromExcel.getDataFromConfigFile();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testData;
		
		
		
	}
	
	@Test(dataProvider = "readtestData")
	public void ValidateHomePage(Hashtable<String, String> testData, ITestContext conext) throws Exception {
 
		try {
			super.initializeDriver(testData);
			super.LoadUrl(driver, "NewTour_Url");
			//logg.info("Url loaded successfully");

			HomePage hp = new HomePage(driver);

			hp.ValidateUrl("Welcome: Mercury Tours");
			hp.EnterDetails(testData, "First Name", "Last Name");


		
			
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}finally {
			super.tearDwon();
		}
	}

}
