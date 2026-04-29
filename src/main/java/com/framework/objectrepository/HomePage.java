package com.framework.objectrepository;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.utils.WaitUtil;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	private By Hploading = By.xpath("//img[@class='loading']");
	private By BestSeller = By.xpath("//div[@class='tt-desctop-menu']//a[normalize-space()='Bestsellers']");
	private By NewProductsCaraousel = By.xpath("//div[@class='title_strip0']");
	private By NewProductsCaraouselItems = By.xpath(
			"//div[@class='title_strip0']/parent::div/following-sibling::div//div[contains(@class,'slick-active')]");
	private By AddToCartNewProductsCaraouselItems = By.xpath(".//div[@class='tt-row-btn']");
	private By ProductCardTitle = By.xpath(".//h2");
	private By ProductCardPrice = By.xpath(".//span[@class='new-price']");
	private By ProductCardAddtoCart = By.xpath(".//div[@class='tt-row-btn']");
	private By SuccessPopup = By.xpath("//div[contains(@class,'addDefinitionMessage')]");
	private By SuccessText = By.xpath("//span[@class='success_msg']");
	private By headerCartIcon = By.id("outer_cart");
	private By WishlistIcon = By.cssSelector(".tt-wishlist");
	private By IncartProductTitles = By.cssSelector(".tt-item-descriptions .tt-title");
	private By HeaderCartButton = By.cssSelector(".tt-cart-btn a");
	private By AllProductCards = By.cssSelector(".tt-product");
	private By ProductCardsAddtoCart = By.cssSelector(".tt-row-btn");
	private By ProductCardsTitle = By.cssSelector("h2 a");
	private By Inputbox = By.id("search_new");
	private By Searchicon = By.cssSelector(".new-btn-search");

	public void isHomePageLoaded() {
		WaitUtil.waitForElementVisibility(driver, Hploading);

	}

	public BestSellersPage clickBestSeller() {
		WebElement bestsellers = WaitUtil.waitForElementClickable(driver, BestSeller);

		bestsellers.click();
		return new BestSellersPage(driver);
	}

	public void scrollToNewProducts() {
		WaitUtil.ScrollToElement(driver, NewProductsCaraousel);
	}

	public List<WebElement> listOfNewProducts() {
		List<WebElement> ListOfNewProducts = driver.findElements(NewProductsCaraouselItems);
		return ListOfNewProducts;
	}

	public void moveToElement(WebElement ele) {
		WaitUtil.moveToElement(driver, ele);

	}

	public WebElement getProductCard(int index) {
		return driver.findElements(NewProductsCaraouselItems).get(index);
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
		return WaitUtil.waitForElementVisibilityBoolean(driver, SuccessPopup);

	}

	public String productSuccessfullyAddedMsg() {
		return driver.findElement(SuccessText).getText();
	}

	public void clickOnHeaderCartIcon() {
		driver.findElement(headerCartIcon).click();
	}
	public void clickOnWishList() {
		driver.findElement(WishlistIcon).click();
	}

	public String inCartProductTitle(int index) {
		return driver.findElements(IncartProductTitles).get(index).getText();
	}

	public CartPage clickOnViewCart() {
		driver.findElement(HeaderCartButton).click();

		return new CartPage(driver);
	}
	
	public String getProductTitleOnWishListPage(int index) {
		
	return 	driver.findElements(By.cssSelector(".tt-item")).get(index).findElement(By.cssSelector(".tt-title")).getText();
	}

	public SearchResultsPage searchForProduct(String query) {
		driver.findElement(Inputbox).sendKeys(query);
		driver.findElement(Searchicon).click();

		return new SearchResultsPage(driver);
	}

	public boolean AddItemToCart(String ProductName) {
		boolean productAdded = false;
		scrollToNewProducts();
		List<WebElement> ListOfProducts = driver.findElements(AllProductCards);

		for (WebElement Ele : ListOfProducts) {
			String ProductTitle = Ele.findElement(ProductCardsTitle).getText().toLowerCase();

			if (ProductTitle.contains(ProductName)) {
				moveToElement(Ele);
				if (Ele.findElement(ProductCardsAddtoCart).isDisplayed()) {

					clickProductCardA2C(Ele);
					productAdded = isProductAdded();
				}
			}
		}
		return productAdded;
	}

	public boolean emptyTheCart()  {
		      boolean isCartEmpty = false;
		while (!isCartEmpty) {
			
			WebElement firstProduct = driver.findElements(By.cssSelector(".tt-cart-list")).get(0);
			firstProduct.findElement(By.cssSelector(".tt-item-close")).click();
			WebElement removeBtn = WaitUtil.waitForElementClickable(driver, By.xpath("//a[text()='Remove']"));
			removeBtn.click();
			WaitUtil.waitForElementInVisibilityBoolean(driver, By.cssSelector("#operationMsg1"));
			/*
			 * clickOnHeaderCartIcon(); WaitUtil.waitForElementVisibility(driver,
			 * By.cssSelector(".tt-cart-layout")); isCartEmpty =
			 * driver.findElement(By.cssSelector(".tt-cart-empty")).isDisplayed();
			 */
			isCartEmpty=	isCartEmpty();
		        
		}
		return isCartEmpty;
	}
	
	public boolean isCartEmpty() {
		clickOnHeaderCartIcon();
		WaitUtil.waitForElementVisibility(driver, By.cssSelector(".tt-cart-layout"));
		return driver.findElement(By.cssSelector(".tt-cart-empty")).isDisplayed();
	}

	public void moveItemToWishList() {

		WebElement firstProduct = driver.findElements(By.cssSelector(".tt-cart-list")).get(0);
		String firstProductTitle = firstProduct.findElement(IncartProductTitles).getText();
		firstProduct.findElement(By.cssSelector(".tt-item-close")).click();
		WebElement removeBtn = WaitUtil.waitForElementClickable(driver, By.xpath("//a[text()='Move to Wishlist']"));
		removeBtn.click();
		isProductAdded();
		WaitUtil.waitForElementInVisibilityBoolean(driver, By.cssSelector("#operationMsg1"));
		clickOnWishList();
		WaitUtil.waitForElementVisibility(driver, By.xpath("//h1[text()='WISHLIST']"));
		String ProductTitleOnWishlist = getProductTitleOnWishListPage(0);

		System.out.println(firstProductTitle + "  : \n" + ProductTitleOnWishlist);
	}
}
	

