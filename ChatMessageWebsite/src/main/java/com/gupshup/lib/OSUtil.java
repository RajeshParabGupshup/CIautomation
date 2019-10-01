package com.gupshup.lib;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class OSUtil
{
	public enum OS
	{
		WINDOWS, LINUX, MAC, SOLARIS
	};

	private static OS os = null;

	public static OS getOS()
	{
		if (os == null)
		{
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win"))
			{
				os = OS.WINDOWS;
			}
			else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix"))
			{
				os = OS.LINUX;
			}
			else if (operSys.contains("mac"))
			{
				os = OS.MAC;
			}
			else if (operSys.contains("sunos"))
			{
				os = OS.SOLARIS;
			}
		}
		return os;
	}

	public static String getUniqueName(String preText)
	{
		String uniqueName = preText + "_" + RandomStringUtils.randomAlphanumeric(5);
		return uniqueName;
	}

	public static void copyFile(String source, String destination) throws IOException
	{
		TestNGLogger.logOnlyToConsole("Copy File Path Locaiton '" + source + "'");
		TestNGLogger.logOnlyToConsole("Destination File Path Location '" + destination + "'");

		File from = new File(source);
		File to = new File(destination);

		if (from.exists() && !from.isDirectory())
		{
			TestNGLogger.logOnlyToConsole("Source File is present on given location.");
			FileUtils.copyFile(from, to);
			if (to.exists() && !to.isDirectory())
			{
				TestNGLogger.logOnlyToConsole("File is copied to destination location.");
			}
			else
			{
				TestNGLogger.logOnlyToConsole("Unable to copy file to destination location, Please check permission");
			}
		}
		else
		{
			TestNGLogger.logOnlyToConsole("Source File is not present on given location '" + source + "' check manually");
		}
	}

	public static void deleteFile(String fileName) throws IOException
	{
		FileUtils.forceDelete(new File(fileName));
	}

	public static String getClipboardText() throws HeadlessException, UnsupportedFlavorException, IOException
	{
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
	}

	public static void writeFile(String fileName, String content, boolean flagOverwrite) throws IOException
	{
		File myFoo = new File(fileName);
		FileOutputStream fooStream = new FileOutputStream(myFoo, flagOverwrite); // true to append
		// false to overwrite.
		byte[] myBytes = content.getBytes();
		fooStream.write(myBytes);
		fooStream.close();
	}

	public static void createParentDirectory(String fileName)
	{
		File parentFiles = new File(fileName);
		parentFiles.getParentFile().mkdirs();
	}
}