package com.gupshup.lib;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPLib
{
	static String SFTPHOST = "10.40.1.13";

	static int SFTPPORT = 22;

	static String SFTPUSER = "qa-sftp";

	static String SFTPPASS = "AgMofequeag4";

	static String SFTPWORKINGDIR = "/files/ENTWebAutomation";

	public static void sendFile(String fileName)
	{

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		TestNGLogger.logOnlyToConsole("preparing the host information for sftp.");
		try
		{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			TestNGLogger.logOnlyToConsole("Host " + SFTPHOST + " with USERNAME " + SFTPUSER + " connected.");
			channel = session.openChannel("sftp");
			channel.connect();
			TestNGLogger.logOnlyToConsole("sftp channel opened and connected.");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
			File f = new File(fileName);

			channelSftp.put(new FileInputStream(f), f.getName());

			TestNGLogger.logOnlyToConsole("File " + f.getName() + " transfered successfully to host.");
		}
		catch (Exception ex)
		{
			TestNGLogger.logOnlyToConsole("Exception found while tranfer the response.");
			TestNGLogger.logOnlyToConsole(ex.getMessage());
		}
		finally
		{
			channelSftp.exit();
			TestNGLogger.logOnlyToConsole("sftp Channel exited.");
			channel.disconnect();
			TestNGLogger.logOnlyToConsole("Channel disconnected.");
			session.disconnect();
			TestNGLogger.logOnlyToConsole("Host " + SFTPHOST + " Session disconnected.");
		}
	}

	public static void removeFile(String fileName)
	{

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		TestNGLogger.logOnlyToConsole("preparing the host information for sftp.");
		try
		{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			TestNGLogger.logOnlyToConsole("Host " + SFTPHOST + " with USERNAME " + SFTPUSER + " connected.");
			channel = session.openChannel("sftp");
			channel.connect();
			TestNGLogger.logOnlyToConsole("sftp channel opened and connected.");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
			File f = new File(fileName);
			TestNGLogger.logOnlyToConsole("File Name " + f.getName());

			channelSftp.rm(f.getName());

			TestNGLogger.logOnlyToConsole("File removed successfully from host.");
		}
		catch (Exception ex)
		{
			TestNGLogger.logOnlyToConsole("Exception found while tranfer the response.");
			TestNGLogger.logOnlyToConsole(ex.getMessage());
		}
		finally
		{
			channelSftp.exit();
			TestNGLogger.logOnlyToConsole("sftp Channel exited.");
			channel.disconnect();
			TestNGLogger.logOnlyToConsole("Channel disconnected.");
			session.disconnect();
			TestNGLogger.logOnlyToConsole("Host " + SFTPHOST + " Session disconnected.");
		}
	}
}
