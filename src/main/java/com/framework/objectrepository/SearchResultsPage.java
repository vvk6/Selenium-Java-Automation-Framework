package com.framework.objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.utils.WaitUtil;

public class SearchResultsPage {
	
	
	
	
	WebDriver driver;
	public SearchResultsPage(WebDriver driver) {
		this.driver= driver;
	}
	
	private By SearchHeader = By.cssSelector("h1.tt-title");
	private By NoProdutsFound = By.xpath("//h1[text()='No Products Found']");
	private By ProductCards = By.cssSelector(".tt-product");

	private By ProductCardTitle = By.cssSelector("h2.tt-title");
	private By ProductCardPrice = By.cssSelector("span.new-price");
	private By ProductCardAddtoCart = By.cssSelector(".tt-row-btn");
	private By SuccessPopup = By.xpath("//div[contains(@class,'addDefinitionMessage')]");
	private By SuccessText = By.xpath("//span[@class='success_msg']");
	private By headerCartIcon = By.id("outer_cart");
	
	public boolean SearchProductFound() {
		
		return WaitUtil.waitForElementInVisibilityBoolean(driver, NoProdutsFound) ;
	}
	
	public List<WebElement> productContainerList() {
		
		 List<WebElement> productsContainer = driver.findElements(ProductCards); 
		 return productsContainer;
		
	}
	
	public void moveToElement(WebElement ele) {
		WaitUtil.moveToElement(driver, ele);
		
	}
	
public WebElement getProductCard(int index) {
	return	driver.findElements(ProductCards).get(index);
	}
	
public String getProductCardTitle(int index) {
	return getProductCard(index).findElement(ProductCardTitle).getText().toLowerCase();
}

public String getProductCardPrice(int index) {
	return getProductCard(index).findElement(ProductCardPrice).getText().toLowerCase();
}

public void clickProductCardA2C(int index) {
	getProductCard(index).findElement(ProductCardAddtoCart).click();
	
}
	
public void clickProductCardA2C(WebElement ele) {
	ele.findElement(ProductCardAddtoCart).click();
	
}

public boolean isProductAdded() {
	return   WaitUtil.waitForElementVisibilityBoolean(driver, SuccessPopup);
	
}
public String productSuccessfullyAddedMsg() {
	return driver.findElement(SuccessText).getText();
}

public void clickOnHeaderCartIcon() {
	driver.findElement(headerCartIcon).click();
}

	
	
}
