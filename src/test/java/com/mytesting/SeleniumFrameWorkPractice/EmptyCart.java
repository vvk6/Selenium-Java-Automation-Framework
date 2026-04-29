package com.mytesting.SeleniumFrameWorkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.framework.base.TestBase;
import com.framework.objectrepository.HomePage;
import com.framework.utils.WaitUtil;

public class EmptyCart extends TestBase{

	
	
	
	@Parameters("ProductNameToAdd")
	@Test(groups = {"Smoke", "Regression"})
	public void removeItemsFromCart(@Optional("lenovo")String Searchquery) throws InterruptedException {
        launchApplication();
        HomePage hp = new HomePage(getDriver());
        
		Assert.assertTrue(hp.AddItemToCart(Searchquery));
		Thread.sleep(10000);
		hp.clickOnHeaderCartIcon();
		Thread.sleep(3000);
		 boolean isCartEmpty = hp.emptyTheCart();
		Assert.assertTrue(isCartEmpty);

	}
	
	@Parameters("ProductNameToAdd")
	@Test(groups = {"Smoke"})
	public void moveToWishlist(@Optional("sony")String Searchquery) throws InterruptedException {
        launchApplication();
        HomePage hp = new HomePage(getDriver());
		Assert.assertTrue(hp.AddItemToCart(Searchquery));
		Thread.sleep(10000);
		hp.clickOnHeaderCartIcon();
		Thread.sleep(3000);
		hp.moveItemToWishList();
		 

	}
}
