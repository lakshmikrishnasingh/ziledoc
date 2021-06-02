package com.ziledoc.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reporter extends TestListenerAdapter
	{
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest logger;
		public ReadConfig readConfig = new ReadConfig();
		
			
		public void onStart(ITestContext testContext)
		{
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String reportName="ZileDoc-Test-Report-"+timeStamp+".html";
			
			htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+reportName);//specify location of the report
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
			
			extent=new ExtentReports();
			
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host name","localhost");
			extent.setSystemInfo("Environemnt",readConfig.getApplicationURL());
			extent.setSystemInfo("user",readConfig.getUserName());
			
			htmlReporter.config().setDocumentTitle("ZileDoc Test Project"); // Tile of report
			htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
			//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
			htmlReporter.config().setTheme(Theme.DARK);
		}
		
		public void onTestSuccess(ITestResult testResult)
		{
			logger=extent.createTest(testResult.getName()); // create new entry in the report
			logger.log(Status.PASS,MarkupHelper.createLabel(testResult.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
		}
		
		public void onTestFailure(ITestResult testResult)
		{
			logger=extent.createTest(testResult.getName()); // create new entry in th report
			logger.log(Status.FAIL,MarkupHelper.createLabel(testResult.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
			
			String screenshotPath=System.getProperty("user.dir")+"//Screenshots//"+testResult.getName()+".png";
			CaptureScreenshot captureScreenshot = new CaptureScreenshot();
			captureScreenshot.takeSnapShot(screenshotPath);
			

			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
				} 
			catch (IOException e) 
					{
					e.printStackTrace();
					}
			}
		
		public void onTestSkipped(ITestResult tr)
		{
			logger=extent.createTest(tr.getName()); // create new entry in th report
			logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
		

}
