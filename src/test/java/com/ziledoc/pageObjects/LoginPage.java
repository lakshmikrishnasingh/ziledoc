package com.ziledoc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver remoteDriver){
		driver=remoteDriver;
		
		PageFactory.initElements(remoteDriver, this);
	}

	@FindBy(partialLinkText="Sign In")
	@CacheLookup
	WebElement signInLink;

	@FindBy(name="email")
	@CacheLookup
	private
	WebElement userName;
	
	@FindBy(name="password")
	@CacheLookup
	private
	WebElement password;
	
	@FindBy(how=How.XPATH, using = "//span[text()='Login']")
	@CacheLookup
	WebElement loginBtn;

	public void clickOnSigninLink() {
		signInLink.click();
	}
	
	public void setUserName(String givenUserName) {
		userName.sendKeys(givenUserName);
	}

	public void setPassword(String givenPassword) {
		password.sendKeys(givenPassword);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}
}
