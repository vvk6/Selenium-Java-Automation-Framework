package com.framework.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.framework.utils.WaitUtil;

public class CartPage {
 
	WebDriver driver;
	
	private By CartPageHeader = By.xpath("//h1[text()='SHOPPING CART']");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isCartPageLoaded() {
		return WaitUtil.waitForElementVisibilityBoolean(driver, CartPageHeader);
	}
}
