package com.framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int count =0;
	private final static int maxTry =1;
	
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}
	

}
