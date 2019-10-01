/**
 * 
 */
package com.gupshup.lib;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 * @author sumit2500
 * @date 04-Jan-2018
 * 
 */
public class Utils {

	private static Logger logger = Logger.getLogger(Utils.class.getName());

	// To get response time
	public static double getResponseTime(double reqST) {
		double responseTime = 0;

		try {
			double reqET = System.currentTimeMillis();
			responseTime = (reqET - reqST) / 1000;
		} catch (Exception e) {
			logger.error("Unable to calculate response, returned 0", e);
		}
		return responseTime;
	}

	public static void createParentDirectory(String fileName) {
		File parentFiles = new File(fileName);
		parentFiles.getParentFile().mkdirs();
	}

	public static void deleteDirectory(File fileDirectory) throws IOException {
		FileUtils.deleteDirectory(fileDirectory);
	}

	public String getResourceFilePath(String fileResource) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource(fileResource).getPath();
	}

	public String getResourcesLocation() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("TestPlan.xls").getFile());
		return file.getParent();
	}

	public static String getStringRegEx(String data, String pattern) {
		String fetchedData = "";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(data);
		if (m.find())
			fetchedData = m.group(1);

		return fetchedData;
	}

	public static String getRandomNumeric() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	public static String getRandomAlphaNumeric(String prefixText) {
		Random r = new Random(System.currentTimeMillis());
		int numeric = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
		return prefixText + "_" + numeric;
	}

	public static String getRandomAlphabetic(String prefixText) {
		return prefixText + "_" + RandomStringUtils.randomAlphanumeric(5);
	}
}
