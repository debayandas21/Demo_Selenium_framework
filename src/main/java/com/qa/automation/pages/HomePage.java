package com.qa.automation.pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
//	@FindBy(name="userName") WebElement usrname;
//	@FindBy(name="password") WebElement password;
	
	By first_name = By.name("first_name");
	By last_name = By.name("last_name");
	
	ExtentTest report;

	/*Validate the url is correctly loaded*/
	public HomePage ValidateUrl(String ExpectedUrlTitle) throws Exception {
		try {
			String Url_Title = driver.getTitle();
			System.out.println(Url_Title);
			Assert.assertEquals(Url_Title, ExpectedUrlTitle);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return this;
	}
	
	
	/*Enter the user name password*/
	public HomePage EnterDetails(Hashtable<String,String>testData, String Username, String Password) throws Exception {
		try {
			checkElementExistence(driver, driver.findElement(first_name), 2);
			driver.findElement(first_name).click();
			waitForDefiniteTime(driver, 2);
			driver.findElement(first_name).sendKeys(testData.get(Username));
			System.out.println("Entered the Username: "+testData.get(Username));
			
			
			
			waitForDefiniteTime(driver, 4);
			checkElementExistence(driver, driver.findElement(last_name), 4);
			driver.findElement(last_name).click();
			waitForDefiniteTime(driver, 2);
			driver.findElement(last_name).sendKeys(testData.get(Password));
			System.out.println("Entered the password: "+testData.get(Password));
			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return this;

	}

}
