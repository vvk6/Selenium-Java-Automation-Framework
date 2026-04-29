package com.mytesting.SeleniumFrameWorkPractice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.base.TestBase;
import com.framework.objectrepository.BestSellersPage;
import com.framework.objectrepository.HomePage;
import com.framework.utils.ExcelUtility;

public class BestSellerTest extends  TestBase{
	
	@Test(groups = {"Smoke", "Regression"})
	public void CheckBestseller() throws IOException {
		
		launchApplication();
		
		HomePage hp = new HomePage(getDriver());
		 hp.isHomePageLoaded();
		 BestSellersPage bs=  hp.clickBestSeller();
		
		 
		bs.isTitleVisible();
		List<String> bsTitles = bs.listOfProductTitles();
		
		String ExcelPath = System.getProperty("user.dir")+"\\resources\\BestSellersExcel.xlsx";
		
		//System.out.println(ExcelPath);
		
		int bestSellerCount = ExcelUtility.getRowCount(ExcelPath, "Sheet1");
		
		Assert.assertEquals(bestSellerCount, bsTitles.size());
		for(int i=1;i<=bestSellerCount;i++) {
			String excelTitle= ExcelUtility.getCellData(ExcelPath, "Sheet1", i, 0).toLowerCase();
			
			boolean isFound =bsTitles.stream().anyMatch(siteTitle->siteTitle.contains(excelTitle)|| excelTitle.contains(siteTitle));
			//System.out.println(isFound);
			if(isFound) {
				ExcelUtility.setCellData(ExcelPath, "Sheet1", i, 1, "Valid");
			}else {
				ExcelUtility.setCellData(ExcelPath, "Sheet1", i, 1, "InValid");
			}
		}
		
		
		
		
		
	}

	
}
