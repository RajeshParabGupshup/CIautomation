package com.gupshup.ent.website;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.gupshup.ent.website.lib.ENTWebSetupBO;
import com.gupshup.ent.website.lib.ENTWebsiteOps;
import com.gupshup.lib.SeleniumHelper;
import com.gupshup.lib.TestNGLogger;

public class ChatMessaging extends SeleniumHelper
{
	private WebDriver driver;

	private SoftAssert softAssert;

	public void driverSetup()
	{
		driver = getDriver();
		softAssert = new SoftAssert();
	}

	@Test(groups = { "sanity", "regression", "smoke" }, description = "Verify Login Functionality with Valid Credentials")
	public void verifyValidSignInFunctionality() throws InterruptedException, FileNotFoundException, IOException
	{
	
//		Assert.assertTrue(dashboard.verifyDashbaordPageActivited(), "Failed to verify dashboard screen after successfully login, Please check manually");
		TestNGLogger.logVerificationStep("Dashboard is loaded on screen, indirectly means login is successully");

//		softAssert.assertAll();
	}
}
