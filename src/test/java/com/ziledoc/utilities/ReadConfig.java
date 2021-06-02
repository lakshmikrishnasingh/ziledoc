package com.ziledoc.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ReadConfig {
	Properties properties = new Properties();
	
	public ReadConfig() {
		File src = new File("./Configuration/Config.properties");
		
		try {
			FileInputStream fileIOStream = new FileInputStream(src);
			properties.load(fileIOStream);
		} catch(Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url=properties.getProperty("baseUrl");
		return url;
	}
	
	public String getUserName() {
		return properties.getProperty("userName");
		
	}
	
	public String getPassword() {
		return properties.getProperty("password");
	}
	
	public String getChromePath() {
		return properties.getProperty("chromepath");
	}
	
	public String getFirefoxPath() {
		return properties.getProperty("firefoxpath");
	}

	
}
