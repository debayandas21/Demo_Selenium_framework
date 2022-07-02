package com.qa.automation.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {
	
		public ExtentReports extent = new ExtentReports();
	    public ExtentSparkReporter reporter;
	    public ExtentTest test;
	
	public static String dfltPath = "C:\\Selenium_practice\\MavenDemo_Test\\";
	
	String NameFrmat=null;	
	public String fileLocation() throws Exception
	{
		String actualPath=null;
			
		
		try {
			
			//create systemDateTime
		
			Date now = new Date();
			SimpleDateFormat sdf =new SimpleDateFormat("ddmmyyhhmmss");
			NameFrmat= sdf.format(now);
			
			String reportPath=System.getProperty("user.dir")+"\\reports";
			
			File fl= new File(reportPath, NameFrmat);
			fl.mkdir();
			
			if(fl.exists()) {
			 actualPath= fl.getAbsolutePath();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("unable to create any file path");
		} return actualPath;
	}
	
	
		public ExtentTest extentReport(String testcaseName,String path) {

	        reporter = new ExtentSparkReporter(path);
			
			test= extent.createTest(testcaseName);
			
			return test;
			
		}
	    

	}

