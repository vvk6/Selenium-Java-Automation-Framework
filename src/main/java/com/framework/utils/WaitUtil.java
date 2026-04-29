package com.framework.utils;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	private static final int TIMEOUT = 10;
	
	
	public static void waitForElementVisibility(WebDriver driver, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
public static boolean waitForElementVisibilityBoolean(WebDriver driver, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		
		
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}

public static boolean waitForElementInVisibilityBoolean(WebDriver driver, By locator) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	
	
	try {
	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	return true;
	}
	catch(Exception e) {
		System.out.println(e);
		return false;
	}
	
}
	
	
public static  WebElement waitForElementClickable(WebDriver driver, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}

public static void ScrollToElement(WebDriver driver, By locator) {
	
	JavascriptExecutor js  = (JavascriptExecutor) driver;
	WebElement ele = driver.findElement(locator);
	
	js.executeScript("arguments[0].scrollIntoView(true);", ele);
	
}

public static void moveToElement (WebDriver driver, By locator) {
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(locator)).perform();
}
public static void moveToElement (WebDriver driver, WebElement ele) {
	Actions action = new Actions(driver);
	action.moveToElement(ele).perform();
}
}
