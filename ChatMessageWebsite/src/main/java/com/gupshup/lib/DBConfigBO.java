package com.gupshup.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author sumit2500
 * @date 08-Jan-2018
 * 
 */
public class DBConfigBO {

	// user credentials
	private static String userRead = "";
	private static String passRead = "";
	private static String userWrite = "";
	private static String passWrite = "";

	// enterprise Receipts DB
	private static String dbNameReceipts = "";
	private static String hostReceipts = "";
	private static String portReceipts = "";

	// enterprise Logging DB
	private static String dbNameLogging = "";
	private static String hostLogging = "";
	private static String portLogging = "";

	// enterprise Enterprise DB
	private static String hostEnterprise = "";
	private static String portEnterprise = "";
	private static String dbNameEnterprise = "";

	// enterprise SMSWeb DB
	private static String hostSMSWeb = "";
	private static String portSMSWeb = "";
	private static String dbNameSMSWeb = "";

	// Sender GATEWAYCONFIG DB
	private static String hostGatewayConfig = "";
	private static String portGatewayConfig = "";
	private static String dbNameGatewayConfig = "";

	// eterprise EntMsgLogging DB
	private static String hostEntMsgLogging = "";
	private static String portEntMsgLogging = "";
	private static String dbNameEntMsgLogging = "";

	private static Logger logger = Logger.getLogger(DBConfigBO.class.getName());

	public void setDBConfigSetup(String fileConfig) throws IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileConfig).getFile());
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			logger.error("Exception while reading database config file", e);
			throw e;
		}

		setReadUserID(prop.getProperty("read_user"));
		setReadPassword(prop.getProperty("read_pass"));
		setWriteUserId(prop.getProperty("write_user"));
		setWritePassword(prop.getProperty("write_pass"));

		setReceiptsHost(prop.getProperty("host_receipts"));
		setReceiptsPort(prop.getProperty("port_receipts"));
		setReceiptsDBName(prop.getProperty("db_name_receipts"));

		setHostLogging(prop.getProperty("host_logging"));
		setPortLogging(prop.getProperty("port_logging"));
		setDbNameLogging(prop.getProperty("db_name_logging"));

		setHostEnterprise(prop.getProperty("host_enterprise"));
		setPortEnterprise(prop.getProperty("port_enterprise"));
		setDbNameEnterprise(prop.getProperty("db_name_enterprise"));

		setHostSMSWeb(prop.getProperty("host_smsweb"));
		setPortSMSWeb(prop.getProperty("port_smsweb"));
		setDbNameSMSWeb(prop.getProperty("db_name_smsweb"));

		setHostGatewayConfig(prop.getProperty("host_gatewayconfig"));
		setPortGatewayConfig(prop.getProperty("port_gatewayconfig"));
		setDbNameGatewayConfig(prop.getProperty("db_name_gatewayconfig"));
		
		setHostEntMsgLogging(prop.getProperty("host_entmsglogging"));
		setPortEntMsgLogging(prop.getProperty("port_entmsglogging"));
		setDbNameEntMsgLogging(prop.getProperty("db_name_entmsglogging"));
	}

	public static void setReadUserID(String user) {
		userRead = user;
	}

	public static String getReadUserID() {
		return userRead;
	}

	public static void setReadPassword(String pass) {
		passRead = pass;
	}

	public static String getReadPassword() {
		return passRead;
	}

	public static void setWriteUserId(String user) {
		userWrite = user;
	}

	public static String getWriteUserId() {
		return userWrite;
	}

	public static void setWritePassword(String pass) {
		passWrite = pass;
	}

	public static String getWritePassword() {
		return passWrite;
	}

	public static void setReceiptsHost(String host) {
		hostReceipts = host;
	}

	public static String getReceiptsHost() {
		return hostReceipts;
	}

	public static void setReceiptsPort(String port) {
		portReceipts = port;
	}

	public static String getReceiptsPort() {
		return portReceipts;
	}

	public static void setReceiptsDBName(String dbName) {
		dbNameReceipts = dbName;
	}

	public static String getReceiptsDBName() {
		return dbNameReceipts;
	}

	/**
	 * @param dbNameLogging
	 *            the dbNameLogging to set
	 */
	public static void setDbNameLogging(String dbNameLogging) {
		DBConfigBO.dbNameLogging = dbNameLogging;
	}

	/**
	 * @return the dbNameLogging
	 */
	public static String getDbNameLogging() {
		return dbNameLogging;
	}

	/**
	 * @param hostLogging
	 *            the hostLogging to set
	 */
	public static void setHostLogging(String hostLogging) {
		DBConfigBO.hostLogging = hostLogging;
	}

	/**
	 * @return the hostLogging
	 */
	public static String getHostLogging() {
		return hostLogging;
	}

	/**
	 * @param portLogging
	 *            the portLogging to set
	 */
	public static void setPortLogging(String portLogging) {
		DBConfigBO.portLogging = portLogging;
	}

	/**
	 * @return the portLogging
	 */
	public static String getPortLogging() {
		return portLogging;
	}

	/**
	 * @param hostEnterprise
	 *            the hostEnterprise to set
	 */
	public static void setHostEnterprise(String hostEnterprise) {
		DBConfigBO.hostEnterprise = hostEnterprise;
	}

	/**
	 * @return the hostEnterprise
	 */
	public static String getHostEnterprise() {
		return hostEnterprise;
	}

	/**
	 * @param dbNameEnterprise
	 *            the dbNameEnterprise to set
	 */
	public static void setDbNameEnterprise(String dbNameEnterprise) {
		DBConfigBO.dbNameEnterprise = dbNameEnterprise;
	}

	/**
	 * @return the dbNameEnterprise
	 */
	public static String getDbNameEnterprise() {
		return dbNameEnterprise;
	}

	/**
	 * @param portEnterprise
	 *            the portEnterprise to set
	 */
	public static void setPortEnterprise(String portEnterprise) {
		DBConfigBO.portEnterprise = portEnterprise;
	}

	/**
	 * @return the portEnterprise
	 */
	public static String getPortEnterprise() {
		return portEnterprise;
	}

	/**
	 * @param dbNameSMSWeb
	 *            the dbNameSMSWeb to set
	 */
	public static void setDbNameSMSWeb(String dbNameSMSWeb) {
		DBConfigBO.dbNameSMSWeb = dbNameSMSWeb;
	}

	/**
	 * @return the dbNameSMSWeb
	 */
	public static String getDbNameSMSWeb() {
		return dbNameSMSWeb;
	}

	/**
	 * @param hostSMSWeb
	 *            the hostSMSWeb to set
	 */
	public static void setHostSMSWeb(String hostSMSWeb) {
		DBConfigBO.hostSMSWeb = hostSMSWeb;
	}

	/**
	 * @return the hostSMSWeb
	 */
	public static String getHostSMSWeb() {
		return hostSMSWeb;
	}

	/**
	 * @param portSMSWeb
	 *            the portSMSWeb to set
	 */
	public static void setPortSMSWeb(String portSMSWeb) {
		DBConfigBO.portSMSWeb = portSMSWeb;
	}

	/**
	 * @return the portSMSWeb
	 */
	public static String getPortSMSWeb() {
		return portSMSWeb;
	}

	/**
	 * @param dbNameGatewayConfig
	 *            the dbNameGatewayConfig to set
	 */
	public static void setDbNameGatewayConfig(String dbNameGatewayConfig) {
		DBConfigBO.dbNameGatewayConfig = dbNameGatewayConfig;
	}

	/**
	 * @return the dbNameGatewayConfig
	 */
	public static String getDbNameGatewayConfig() {
		return dbNameGatewayConfig;
	}

	/**
	 * @param hostGatewayConfig
	 *            the hostGatewayConfig to set
	 */
	public static void setHostGatewayConfig(String hostGatewayConfig) {
		DBConfigBO.hostGatewayConfig = hostGatewayConfig;
	}

	/**
	 * @param portGatewayConfig
	 *            the portGatewayConfig to set
	 */
	public static void setPortGatewayConfig(String portGatewayConfig) {
		DBConfigBO.portGatewayConfig = portGatewayConfig;
	}

	/**
	 * @return the hostGatewayConfig
	 */
	public static String getHostGatewayConfig() {
		return hostGatewayConfig;
	}

	/**
	 * @return the portGatewayConfig
	 */
	public static String getPortGatewayConfig() {
		return portGatewayConfig;
	}

	/**
	 * @param dbNameEntMsgLogging the dbNameEntMsgLogging to set
	 */
	public static void setDbNameEntMsgLogging(String dbNameEntMsgLogging) {
		DBConfigBO.dbNameEntMsgLogging = dbNameEntMsgLogging;
	}
	
	/**
	 * @return the dbNameEntMsgLogging
	 */
	public static String getDbNameEntMsgLogging() {
		return dbNameEntMsgLogging;
	}
	
	/**
	 * @param hostEntMsgLogging the hostEntMsgLogging to set
	 */
	public static void setHostEntMsgLogging(String hostEntMsgLogging) {
		DBConfigBO.hostEntMsgLogging = hostEntMsgLogging;
	}
	
	/**
	 * @return the hostEntMsgLogging
	 */
	public static String getHostEntMsgLogging() {
		return hostEntMsgLogging;
	}
	
	/**
	 * @param portEntMsgLogging the portEntMsgLogging to set
	 */
	public static void setPortEntMsgLogging(String portEntMsgLogging) {
		DBConfigBO.portEntMsgLogging = portEntMsgLogging;
	}
	
	/**
	 * @return the portEntMsgLogging
	 */
	public static String getPortEntMsgLogging() {
		return portEntMsgLogging;
	}
	
}
