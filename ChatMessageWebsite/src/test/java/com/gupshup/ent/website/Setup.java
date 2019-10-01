package com.gupshup.ent.website;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.gupshup.ent.website.lib.ENTWebSetupBO;
import com.gupshup.lib.SeleniumHelper;
import com.gupshup.lib.TestNGLogger;

public class Setup extends SeleniumHelper {

	public void driverSetup() {
		driver = getDriver();
	}

	@BeforeSuite(groups = { "sanity", "regression",
			"smoke" }, description = "Before Suite Setup the automation environment")
	public void setupStarter() throws IOException, ParseException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InterruptedException {
		TestNGLogger.logOnlyToConsole("------Started Execution of Before Suite------");

		TestNGLogger.logSteps("Clean Screenshot directory");
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + File.separator + "Screenshot"));

		TestNGLogger.logSteps("Clean Test-Resources directory");
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources"));
		FileUtils.deleteQuietly(new File(System.getProperty("user.dir") + File.separator + "failed_screenshot.zip"));

		TestNGLogger.logInfo("Preparing Execution Environment Setup");

		TestNGLogger.logSteps("Automation Configuration Setup");

		// accepting the credentials from Jenkins job
		ENTWebSetupBO.setPlatformURL(System.getProperty("webURL"));

		String listEmail = System.getProperty("email_list");
		ENTWebSetupBO.setClientEmail(listEmail);
		TestNGLogger.logSteps("Set Automation User Email Ids as " + listEmail);

		String urlJenkins = System.getProperty("jenkins_url");
		ENTWebSetupBO.setJenkinsJobURL(urlJenkins);
		TestNGLogger.logSteps("Set Jenkins Job URL is = " + urlJenkins);

		String browser = System.getProperty("browser");
		TestNGLogger.logSteps("Execution Browser = " + browser);

		boolean flagLaunchedBrowser = launchBrowser(browser);
		if (flagLaunchedBrowser) {
			TestNGLogger.logVerificationStep("Browsr is launched successfully");
		} else {
			TestNGLogger.logVerificationStep("Unable to launch browser, please check it manually");
		}

		TestNGLogger.logOnlyToConsole("------Ended Execution of Before Suite------");

	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		TestNGLogger.logOnlyToConsole("------Started Execution of After Suite------");
		tearDown();
		TestNGLogger.logOnlyToConsole("------Ended Execution of After Suite------");
	}

}
