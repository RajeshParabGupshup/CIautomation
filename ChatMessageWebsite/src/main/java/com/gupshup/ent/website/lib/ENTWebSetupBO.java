package com.gupshup.ent.website.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.json.simple.parser.ParseException;

import com.gupshup.lib.DBConfigBO;
import com.gupshup.lib.DataBaseLib;
import com.gupshup.lib.TestNGLogger;

public class ENTWebSetupBO {
	private static String urlPlatform = "";

	private static String clientEmail = "";
	private static String jenkinsJobURL = "";
	private static String flagTestExection = "PASS";

	public static void setPlatformURL(String urlPlatform) {
		ENTWebSetupBO.urlPlatform = urlPlatform;
	}
	
	public static String getPlatformURL() {
		return urlPlatform;
	}

	public static void setClientEmail(String clientEmail1) {
		clientEmail = clientEmail1;
	}

	public static String getClientEmail() {
		return clientEmail;
	}

	public static void setJenkinsJobURL(String jenkinsJobURL1) {
		jenkinsJobURL = jenkinsJobURL1;
	}

	public static String getJenkinsJobURL() {
		return jenkinsJobURL;
	}

	public static void setFlagTestExection(String flagTestExection1) {
		flagTestExection = flagTestExection1;
	}

	public static String getFlagTestExection() {
		return flagTestExection;
	}
}
