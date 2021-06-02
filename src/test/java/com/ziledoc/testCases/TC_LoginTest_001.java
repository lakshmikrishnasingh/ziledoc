package com.ziledoc.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.ziledoc.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	
	@Test
	public void loginTest() {
		
		logger.info("User clicks on URL");
		
		driver.manage().window().maximize();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnSigninLink();
		logger.info("User navigated to sign in page");
		
		loginPage.setUserName(userName);
		logger.info("User entered userName: "+userName);
		
		loginPage.setPassword(password);
		logger.info("User entered password: "+password);
		
		loginPage.clickLogin();
		logger.info("User signed in to ZileDoc");
		
		if(driver.getTitle().equalsIgnoreCase("ZileDoc")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
	}
}
