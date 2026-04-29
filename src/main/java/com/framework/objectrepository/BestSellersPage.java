package com.framework.objectrepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.utils.WaitUtil;

public class BestSellersPage {
	
	WebDriver driver;
	
	public BestSellersPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	private By title = By.xpath("//h1[@class='tt-title']");
	private By ProductDivs = By.xpath("//div[@id='ajaxLoadData']/div");
	
	private By ProductTitle = By.xpath(".//h2/a");
	
	
	public void  isTitleVisible() {
		WaitUtil.waitForElementVisibility(driver, title);
		
	}
	
	public List<String> listOfProductTitles() {
		List<WebElement> productsContainer = driver.findElements(ProductDivs);
		List<String> bsTitles = new ArrayList<String>();
		for(WebElement ele: productsContainer) {
			bsTitles.add(ele.findElement(ProductTitle).getText().toLowerCase());
		}
		return bsTitles;
	}
	
	public List<WebElement> productContainerList() {
		
		 List<WebElement> productsContainer = driver.findElements(ProductDivs); 
		 return productsContainer;
		
	}
	
	
		
	

}
