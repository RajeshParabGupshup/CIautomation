package com.gupshup.lib;

import org.testng.Reporter;

public class TestNGLogger
{
	public static void logSteps(String stepsDescription)
	{
		String stepsDes = "Step : " + stepsDescription;
		System.out.println(stepsDes);
		Reporter.log(stepsDes);
	}

	public static void logVerificationStep(String verficationStepDesc)
	{
		String stepsDes = "Verified : " + verficationStepDesc;
		System.out.println(stepsDes);
		Reporter.log(stepsDes);
	}

	public static void logInfo(String infoDescription)
	{
		System.out.println(infoDescription);
		Reporter.log(infoDescription);
	}

	public static void logOnlyToReport(String infoDescription)
	{
		Reporter.log(infoDescription);
	}

	public static void logOnlyToConsole(String infoDescription)
	{
		System.out.println(infoDescription);
	}

	public static void logSubTestInfo(int noSubTest, String testInfo)
	{
		String stepsDes = "SubTest No " + noSubTest + " : " + testInfo;
		System.out.println("\n" + stepsDes);
		Reporter.log("<br>" + stepsDes);
	}
}
