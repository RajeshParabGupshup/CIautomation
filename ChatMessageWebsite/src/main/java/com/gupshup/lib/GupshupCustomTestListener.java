package com.gupshup.lib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.gupshup.ent.website.lib.ENTWebSetupBO;

public class GupshupCustomTestListener extends SeleniumHelper implements ITestListener {
	WebDriver driver = null;
	public static int intIndex = 1;

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TestNGLogger.logOnlyToConsole("-------------------------------------------------------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TestNGLogger.logOnlyToConsole("Setting Test Parameter to False");
		ENTWebSetupBO.setFlagTestExection("FAIL");
		TestNGLogger.logOnlyToConsole("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		try {
			takeScreenShot(methodName);
		} catch (IOException e) {

		}
		TestNGLogger.logOnlyToConsole("-------------------------------------------------------");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		result.setEndMillis(result.getEndMillis() + ((intIndex++) * 1000));
		TestNGLogger.logOnlyToConsole("-------------------------------------------------------");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
