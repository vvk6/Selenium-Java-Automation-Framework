package com.mytesting.SeleniumFrameWorkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.framework.base.TestBase;
import com.framework.objectrepository.CartPage;
import com.framework.objectrepository.HomePage;
import com.framework.objectrepository.SearchResultsPage;
import com.framework.utils.WaitUtil;

public class AddToCart extends TestBase {

	@Test(groups = {"Smoke", "Regression"})
	public void addProductToCartFromHomePage() throws InterruptedException  {
		
		launchApplication();
		
		HomePage hp = new HomePage(getDriver());
		 hp.isHomePageLoaded();
		 hp.scrollToNewProducts();
	 List<WebElement> ListOfNewProducts = hp.listOfNewProducts();
            WebElement product = ListOfNewProducts.get(0);
            String ProductPrice = hp.getProductCardPrice(0);
            String ProductTitle = hp.getProductCardTitle(0);
            System.out.println(ProductTitle);
            System.out.println(ProductPrice);
                        WaitUtil.moveToElement(getDriver(), hp.getProductCard(0));
		        hp.clickProductCardA2C(product);
	
		boolean itemAdded = hp.isProductAdded();
		String message = hp.productSuccessfullyAddedMsg();
		Assert.assertTrue(itemAdded, message);
				hp.clickOnHeaderCartIcon();
		String outerCartItemText = hp.inCartProductTitle(0);
		System.out.println(ProductTitle +"\n"+outerCartItemText);
		CartPage cp = hp.clickOnViewCart();
		boolean cartLoaded = cp.isCartPageLoaded();
		Assert.assertTrue(cartLoaded);
		Thread.sleep(10000);
	}
	
	@Parameters("Searchquery")
	@Test(groups = { "Regression"})
	public void addProductBySearch(@Optional("Dual")String Searchquery) throws InterruptedException {
        launchApplication();
		
		HomePage hp = new HomePage(getDriver());
		 hp.isHomePageLoaded();
		SearchResultsPage srp = hp.searchForProduct(Searchquery);
		Assert.assertTrue(srp.SearchProductFound());
		List<WebElement> SearchResultList = srp.productContainerList();
		Assert.assertTrue(SearchResultList.size()>0);
	 WebElement FirstProductCard = srp.getProductCard(0);
		String FirstProductPrice = srp.getProductCardPrice(0);
		String FirstProductTitle = srp.getProductCardTitle(0);
		System.out.println(FirstProductTitle +" : " +FirstProductPrice );
		
		srp.moveToElement(FirstProductCard);
		srp.clickProductCardA2C(FirstProductCard);
		
		boolean itemAdded = srp.isProductAdded();
		String message = srp.productSuccessfullyAddedMsg();
		Assert.assertTrue(itemAdded, message);
		hp.clickOnHeaderCartIcon();
String outerCartItemText = hp.inCartProductTitle(0);
System.out.println(FirstProductTitle +"\n"+outerCartItemText);
CartPage cp = hp.clickOnViewCart();
boolean cartLoaded = cp.isCartPageLoaded();
Assert.assertTrue(cartLoaded);
Thread.sleep(10000);

		
	}
}
