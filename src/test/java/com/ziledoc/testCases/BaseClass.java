package com.ziledoc.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.ziledoc.utilities.ReadConfig;



public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseUrl=readConfig.getApplicationURL();
	public String userName=readConfig.getUserName();
	public String password=readConfig.getPassword();
	
	
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		
		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4J.properties");
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver=new ChromeDriver();
			logger.info("Automation run in chrome browser");
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver=new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
