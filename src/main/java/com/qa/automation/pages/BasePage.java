package com.qa.automation.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
		
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	
	
	/*wait until object is not displayed*/
	public static void WaitForObjectToLoad(WebDriver driver, WebElement element) throws NoSuchElementException {
		try {
			do {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				System.out.println("element is not yet displayed on the page, wait for 2 seconds");
			} while (element.isDisplayed() == false);
			System.out.println("element is displayed on the page");
		} catch (NoSuchElementException noe) {
			noe.printStackTrace();
		}
	}
	
	
	
	/* Check the element exist in the page or not */
	public static void checkElementExistence(WebDriver driver, WebElement element, int time)
			throws NoSuchElementException {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		try {
			if (element.isDisplayed()) {
				flag = true;
				System.out.println("element is displayed on the page ");
			}
			try {
				if (flag == false) {
					driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
				}
			} catch (NoSuchElementException noe) {
				noe.printStackTrace();
				System.out.println("No such element displayed on this page");
			}

		} catch (NoSuchElementException noe) {
			noe.printStackTrace();
		}
	}
	
	
	public static void waitForDefiniteTime(WebDriver driver, int time) throws Exception {

		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
