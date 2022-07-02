package com.qa.automation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import com.qa.automation.utility.getData_FromExcel;


//import com.qa.automation.utility.Utility;

public class BaseTest {

	public WebDriver driver;
	
	public static Logger logg;

	
	/*Initialize driver and load the browser*/
	public void initializeDriver(Hashtable<String, String> data) throws WebDriverException {
		try {
			
		
			String BrowserName=data.get("Browser");
			System.out.println(BrowserName);
			Properties prop;
			try {
				prop = this.readPropertiesFile();
				this.force_close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if (BrowserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers//chromedriver.exe");

				driver = new ChromeDriver();

				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				System.out.println(BrowserName + "Bowser loadedsuccessfully");
			}

			if (BrowserName.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");

				driver = new EdgeDriver();
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				System.out.println(BrowserName + "Bowser loadedsuccessfully");
			}
			

		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	/*Read data from property file*/
	public  Properties readPropertiesFile() throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      File file= new File("./config/configData.properties");
	      
	      try {
	         fis = new FileInputStream(file);
	         prop = new Properties();
	         prop.load(fis);
	         
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
	
	/**
	 * @Method: LoadUrl
	 * @param:	driver
	 * @throws 	Exception
	 */
	public void LoadUrl(WebDriver driver, String UrlName) throws Exception
	{
		try {
			//String file=System.getProperty("./config/configData.properties");
//			Properties prop= readPropertiesFile();
//			driver.get(prop.getProperty(UrlName));
//			driver.manage().timeouts().pageLoadTimeout(05, TimeUnit.SECONDS);
			
			System.out.println("Url address: "+getData_FromExcel.globalConfigmap.get(UrlName));
			String Url=getData_FromExcel.globalConfigmap.get(UrlName);
			driver.get(Url);
			
			//Thread.sleep(40000);
			driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println("Page loadedsuccessfully");
			
			
		}catch (WebDriverException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	
	public void tearDwon() {
		driver.close();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Browser closed successfully");
		
	}

	/*Forecfully kill the existing open applications*/
	
	public void force_close() throws IOException {
		try {

			Runtime.getRuntime().exec("taskkill /F /im chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /im msedge.exe");
			
		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}


