package com.gupshup.ent.website.lib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import com.gupshup.lib.DataBaseLib;
import com.gupshup.lib.TestNGLogger;

/**
 * @author name
 * @date 09-Jan-2018
 * 
 */
public class DBValidator {
	private static final String RECEIPTS_DB = "RECEIPTS";

	private static final String LOGGING_DB = "LOGGING";

	private static final String SMSWEB_DB = "SMSWEB";

	private static Logger logger = Logger.getLogger(DBValidator.class.getName());

	private DBValidator() {
		throw new IllegalStateException("Utility Class");
	}

	public static JSONObject getReceiptsDRStatus(String causeId, String noClient) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {

		// create a json for database result
		JSONObject jsonResultDeliveryReportStatus = new JSONObject();

		String nameDataBase = RECEIPTS_DB;

		String statusQuery = "select stat from DeliveryReport where causeId='" + causeId + "' and destAddr = '91"
				+ noClient + "';";
		TestNGLogger.logInfo("Executing Query = " + statusQuery);
		// get the status
		List<Object[]> statusResult = null;
		try {
			statusResult = DataBaseLib.getRecords(nameDataBase, statusQuery);
		} catch (Exception e) {
			logger.error("Failed to fetch Delivery Report status Data", e);
		}

		jsonResultDeliveryReportStatus.put("status", statusResult.get(0)[0]);

		// if failed than take metadata from db
		if (!statusResult.equals("DELIVRD")) {

			// get the metadata
			String metadataResult = getReceiptsMetadata(causeId, noClient);
			jsonResultDeliveryReportStatus.put("metadata", metadataResult);

		} else {
			jsonResultDeliveryReportStatus.put("metadata", "");
		}

		return jsonResultDeliveryReportStatus;
	}

	public static String getReceiptsMetadata(String causeId, String noClient) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {
		String nameDataBase = RECEIPTS_DB;
		String metadataQuery = "select metadata from DeliveryReport where causeId='" + causeId + "' and destAddr = '91"
				+ noClient + "';";
		TestNGLogger.logInfo("Executing Query = " + metadataQuery);
		// get the status
		List<Object[]> metadata = null;
		try {
			metadata = DataBaseLib.getRecords(nameDataBase, metadataQuery);
		} catch (Exception e) {
			logger.error("Failed to fetch Delivery Report metadata Data", e);
		}
		return (String) metadata.get(0)[0];
	}

	public static boolean getAccountIsTemplateFlag(String keyAccount) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {
		boolean flagTemplate = false;
		String queryAccountTemplateFlag = "select value from EntUserAttrValue where attrId = 66 and userId in (select id from Users where phoneNo = '-"
				+ keyAccount + "');";

		TestNGLogger.logInfo("Executing Query = " + queryAccountTemplateFlag);

		// get the status
		List<Object[]> metadata = new ArrayList<>();
		try {
			metadata = DataBaseLib.getRecords(SMSWEB_DB, queryAccountTemplateFlag);
		} catch (Exception e) {
			logger.error("Failed to fetch Delivery Report metadata Data", e);
		}

		if (!metadata.isEmpty())
			flagTemplate = (boolean) ((String) metadata.get(0)[0]).equalsIgnoreCase("true");
		return flagTemplate;
	}


	public static JSONObject getMsgLogStatus(String causeId, String noClient) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {
		// create a json for database result
		JSONObject jsonResultMsgLogStatus = new JSONObject();

		try {

			// map the status code and print it
			String nameDataBase = LOGGING_DB;

			String statusQuery = "select status from MsgLog where causeId='" + causeId + "' and sender = '91" + noClient
					+ "';";
			TestNGLogger.logInfo("Executing Query = " + statusQuery);
			// get the status
			List<Object[]> statusResult = DataBaseLib.getRecords(nameDataBase, statusQuery);
			String strStatus = (String) statusResult.get(0)[0];
			jsonResultMsgLogStatus.put("status", strStatus);
			System.out.println("Status from MsgLog is: " + strStatus);
			// if failed than take metadata from db
			if (!strStatus.equals("DISPATCHED")) {
				String metadataQuery = "select metadata from MsgLog where causeId='" + causeId + "' and sender = '91"
						+ noClient + "';";
				TestNGLogger.logInfo("Executing Query = " + metadataQuery);
				// get the status
				List<Object[]> metadataResult = DataBaseLib.getRecords(nameDataBase, metadataQuery);
				jsonResultMsgLogStatus.put("metadata", (String) metadataResult.get(0)[0]);
			} else {
				jsonResultMsgLogStatus.put("metadata", "");
			}

		} catch (Exception e) {
			logger.error("Exception while getting MsgLog status", e);
			throw e;
		}
		return jsonResultMsgLogStatus;
	}

	public static JSONObject getMsgLogStatusAndCauseId(String text, String noClient) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {
		// create a json for database result
		JSONObject jsonResultMsgLogStatus = new JSONObject();

		try {

			// map the status code and print it
			String nameDataBase = LOGGING_DB;

			String statusQuery = "select status,causeId from MsgLog where text='"
					+ StringEscapeUtils.escapeEcmaScript(text) + "' and sender = '91" + noClient
					+ "' and timestamp > unix_timestamp(NOW() - INTERVAL 5 MINUTE) ;";

			TestNGLogger.logInfo("Executing Query = " + statusQuery);
			// get the status
			List<Object[]> statusResult = DataBaseLib.getRecords(nameDataBase, statusQuery);

			String strStatus = (String) statusResult.get(0)[0];
			jsonResultMsgLogStatus.put("status", strStatus);
			jsonResultMsgLogStatus.put("causeId", (long) statusResult.get(0)[1]);

			// if failed than take metadata from db
			if (!strStatus.equals("DISPATCHED")) {
				String metadataQuery = "select metadata from MsgLog where text='" + text + "' and sender = '91"
						+ noClient + "' and timestamp > unix_timestamp(NOW() - INTERVAL 5 MINUTE) ;";
				TestNGLogger.logInfo("Executing Query = " + metadataQuery);
				// get the status
				List<Object[]> metadataResult = DataBaseLib.getRecords(nameDataBase, metadataQuery);
				jsonResultMsgLogStatus.put("metadata", (String) metadataResult.get(0)[0]);
			} else {
				jsonResultMsgLogStatus.put("metadata", "");
			}
		} catch (Exception e) {
			logger.error("Exception while getting MsgLog status", e);
			throw e;
		}
		return jsonResultMsgLogStatus;
	}

}