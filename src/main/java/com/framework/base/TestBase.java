package com.framework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
	
	public static ThreadLocal<WebDriver>  tdriver = new ThreadLocal<>();
	
	public static Properties prop;
	
   static {
	   try {
		   prop = new Properties();
		   String path = System.getProperty("user.dir")+"\\resources\\config.properties"; 
		   
		   FileInputStream fis = new FileInputStream(path);
		   prop.load(fis);
		   fis.close();
	   }catch (IOException e) {
		   throw new RuntimeException("CRITICAL: Could not load config.properties at " + System.getProperty("user.dir"), e);
	   }
   }
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setup(@Optional("Chrome")String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			tdriver.set(new ChromeDriver());
		}else if(browser.equalsIgnoreCase("firefox")) {
			tdriver.set(new FirefoxDriver());
		}else if(browser.equalsIgnoreCase("edge")) {
			tdriver.set(new EdgeDriver());
		}else {
			throw new IllegalArgumentException("Browser "+browser+" is not supported. please provide correct browser name");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		}
	
	public static WebDriver getDriver() {
		return tdriver.get();
	}
	
	public static void launchApplication() {
		
		String url = prop.getProperty("url");
		getDriver().get(url);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		tdriver.remove();
	}

}
