package com.gupshup.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPLib
{

	private static String server = "10.80.11.125";

	private static int port = 21;

	private static String user = "gupshup";

	private static String pass = "Gup$hup.i0";

	private static String windowsDefaultLocation = "EmailUIAutomation\\";

	public static FTPClient ftpConnect() throws IOException
	{
		FTPClient ftpClient = new FTPClient();
		try
		{
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			// ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			TestNGLogger.logOnlyToConsole("FTP Server " + server + " Connected");
		}
		catch (Exception ex)
		{
			TestNGLogger.logOnlyToConsole("Error: " + ex.getMessage());
			ex.printStackTrace();
			throw ex;
		}
		return ftpClient;
	}

	public static void ftpDisconnect(FTPClient ftpClient) throws IOException
	{
		if (ftpClient.isConnected())
		{
			ftpClient.logout();
			ftpClient.disconnect();
			TestNGLogger.logOnlyToConsole("FTP Server " + server + " Disconnected");
		}
	}

	public static boolean checkFilePresence(String fileName)
	{
		boolean flagPresence = false;
		FTPClient ftp = null;
		try
		{

			ftp = ftpConnect();
			InputStream inputStream = ftp.retrieveFileStream(windowsDefaultLocation + fileName);
			int returnCode = ftp.getReplyCode();
			if (inputStream == null || returnCode == 550)
			{
				flagPresence = false;
			}
			else
				flagPresence = true;

		}
		catch (Exception ex)
		{
			TestNGLogger.logOnlyToConsole("Error: " + ex.getMessage());
			ex.printStackTrace();
			flagPresence = false;
		}
		finally
		{
			try
			{
				ftpDisconnect(ftp);
			}
			catch (Exception ex1)
			{
				ex1.printStackTrace();
			}
		}
		return flagPresence;
	}

	public static boolean sendFile(String fileName)
	{
		boolean flagSent = false;
		FTPClient ftp = null;
		try
		{

			ftp = ftpConnect();

			File localFile = new File(fileName);

			// String firstRemoteFile = windowsDefaultLocation + localFile.getName();
			String firstRemoteFile = windowsDefaultLocation + localFile.getName();

			InputStream inputStream = new FileInputStream(localFile);

			// ftpClient.makeDirectory("sumitTest");
			TestNGLogger.logOnlyToConsole("Start uploading file...");
			boolean done = ftp.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (done)
				flagSent = true;

		}
		catch (Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			flagSent = false;
		}
		finally
		{
			try
			{
				ftpDisconnect(ftp);
			}
			catch (Exception ex1)
			{
				ex1.printStackTrace();
			}
		}
		return flagSent;
	}

	public static boolean removeFile(String fileName)
	{
		boolean flagRemove = false;
		FTPClient ftp = null;
		try
		{

			ftp = ftpConnect();
			ftp.deleteFile(windowsDefaultLocation + fileName);
			flagRemove = true;
		}
		catch (Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			flagRemove = false;
		}
		finally
		{
			try
			{
				ftpDisconnect(ftp);
			}
			catch (Exception ex1)
			{
				ex1.printStackTrace();
			}
		}
		return flagRemove;
	}
}
