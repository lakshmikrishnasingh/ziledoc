package com.ziledoc.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.ziledoc.testCases.BaseClass;

public class CaptureScreenshot extends BaseClass {
	public void takeSnapShot(String screenshotPath) {
		TakesScreenshot screenShot =((TakesScreenshot)driver);
		
		try {
			File sourceFile=screenShot.getScreenshotAs(OutputType.FILE);
			File destinationFile=new File(screenshotPath);
			boolean fileCreationResult = destinationFile.createNewFile();
			FileUtils.copyFile(sourceFile, destinationFile);
			
		}
		
		catch(IOException exception) {
			logger.error(exception.getMessage());
		}
		
		
		
	}

}
