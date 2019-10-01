/**
 * 
 */
package com.gupshup.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.gupshup.lib.DBConfigBO;

/**
 * @author sumit2500
 * @date 05-Jan-2018
 * 
 */
public class DataBaseLib
{
	private static DataBaseLib instance;

	private static HashMap<String, Connection> dbReadConnections;

	private static HashMap<String, Connection> dbWriteConnections;

	public static int retryDBConnection = 12;

	private static Logger logger = Logger.getLogger(DataBaseLib.class.getName());

	public DataBaseLib()
	{
		dbReadConnections = new HashMap<>();
		dbWriteConnections = new HashMap<>();
	}


	public static DataBaseLib getInstance()
	{
		if (instance == null)
			instance = new DataBaseLib();
		return instance;
	}

	public static void cleanUp() throws SQLException
	{
		for (Connection conn : dbReadConnections.values())
		{
			if (conn != null)
			{
				conn.close();
			}
		}
		for (Connection conn : dbWriteConnections.values())
		{
			if (conn != null)
			{
				conn.close();
			}
		}
	}

	private static Connection getDBReadConnection(String dbName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection conn = dbReadConnections.get(dbName);
		if (conn == null)
		{

			// fetch details for connection
			String dbHost = "";
			String dbPort = "";
			String userName = DBConfigBO.getReadUserID();
			String password = DBConfigBO.getReadPassword();
			switch (dbName)
			{

			case "RECEIPTS":
				dbHost = DBConfigBO.getReceiptsHost();
				dbPort = DBConfigBO.getReceiptsPort();
				break;
			case "LOGGING":
				dbHost = DBConfigBO.getHostLogging();
				dbPort = DBConfigBO.getPortLogging();
				break;
			case "SMSWEB":
				dbHost = DBConfigBO.getHostSMSWeb();
				dbPort = DBConfigBO.getPortSMSWeb();
				break;
			case "Enterprise":
				dbHost = DBConfigBO.getHostEnterprise();
				dbPort = DBConfigBO.getPortEnterprise();
				break;
			case "GATEWAYCONFIG":
				dbHost = DBConfigBO.getHostGatewayConfig();
				dbPort = DBConfigBO.getPortGatewayConfig();
				break;
			case "EntMsgLogging":
				dbHost = DBConfigBO.getHostEntMsgLogging();
				dbPort = DBConfigBO.getPortEntMsgLogging();
				break;
			default:
				break;
			}

			// create connection
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String dbURI = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + userName + "&password="
					+ password + "&autoReconnect=true&useSSL=false";

			conn = DriverManager.getConnection(dbURI);
			dbReadConnections.put(dbName, conn);
		}
		return conn;
	}

	private static Connection getDBWriteConnection(String dbName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection conn = dbWriteConnections.get(dbName);
		if (conn == null)
		{
			// fetch details for connection
			String dbHost = "";
			String dbPort = "";
			String userName = DBConfigBO.getWriteUserId();
			String password = DBConfigBO.getWritePassword();
			switch (dbName)
			{

			case "RECEIPTS":
				dbHost = DBConfigBO.getReceiptsHost();
				dbPort = DBConfigBO.getReceiptsPort();
				break;
			case "LOGGING":
				dbHost = DBConfigBO.getHostLogging();
				dbPort = DBConfigBO.getPortLogging();
				break;
			case "SMSWEB":
				dbHost = DBConfigBO.getHostSMSWeb();
				dbPort = DBConfigBO.getPortSMSWeb();
				break;
			case "Enterprise":
				dbHost = DBConfigBO.getHostEnterprise();
				dbPort = DBConfigBO.getPortEnterprise();
				break;
			case "GATEWAYCONFIG":
				dbHost = DBConfigBO.getHostGatewayConfig();
				dbPort = DBConfigBO.getPortGatewayConfig();
				break;
			case "EntMsgLogging":
				dbHost = DBConfigBO.getHostEntMsgLogging();
				dbPort = DBConfigBO.getPortEntMsgLogging();
				break;
			default:
				break;
			}

			// create connection
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String dbURI = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + userName + "&password="
					+ password + "&autoReconnect=true&useSSL=false";
			conn = DriverManager.getConnection(dbURI);
			dbWriteConnections.put(dbName, conn);
		}
		return conn;
	}

	@SuppressWarnings("resource")
	private static ResultSet getResultSet(String dbName, String query)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException
	{
		ResultSet rs1 = null;
		try
		{

			Connection conn = getDBReadConnection(dbName);

			int cnt = 0;

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (!rs.next())
			{

				Thread.sleep(15000);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				cnt++;

				if (cnt > retryDBConnection)
				{

					logger.error("Failed to get any results from the query : " + query + " on database : " + dbName);
					break;
				}

			}
			rs.beforeFirst();
			rs1 = rs;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("================================getResultSet: " + e.getMessage());

		}

		return rs1;

	}

	public static List<Object[]> getRecords(String dbName, String query)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException
	{
		List<Object[]> strResutSetData = new ArrayList<>();

		try
		{
			ResultSet rs = getResultSet(dbName, query);

			while (rs.next())
			{

				rs.absolute(rs.getRow());

				ResultSetMetaData rsmd = rs.getMetaData();
				Object[] objColumn = new Object[rsmd.getColumnCount()];
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{

					int type = rsmd.getColumnType(i);
					switch (type)
					{
					case Types.VARCHAR:
					case Types.CHAR:
						objColumn[i - 1] = rs.getString(i);
						break;
					case Types.BIGINT:
						objColumn[i - 1] = rs.getLong(i);
						break;
					case Types.BOOLEAN:
						objColumn[i - 1] = rs.getBoolean(i);
						break;
					}

				}
				strResutSetData.add(objColumn);

			}
		}
		catch (Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		return strResutSetData;
	}

//	public static List<String> getStringMultipleRow(String dbName, String query, int column)
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
//			InterruptedException {
//		List<String> lstResultSetData = new ArrayList<>();
//
//		ResultSet rs = getResultSet(dbName, query);
//		while (rs.next()) {
//			String strResult = rs.getString(column);
//			lstResultSetData.add(strResult);
//		}
//
//		return lstResultSetData;
//	}

	@SuppressWarnings("resource")
	public static int setResultSet(String dbName, String query) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InterruptedException {
		Connection conn = getDBWriteConnection(dbName);
		int rs = 0;
		int cnt = 0;
		Statement stmt = conn.createStatement();
		rs = stmt.executeUpdate(query);

		while (rs != 1)
		{

			Thread.sleep(15000);

			rs = stmt.executeUpdate(query);
			cnt++;
			if (cnt > retryDBConnection)
			{
				logger.error("Failed to get any results from the query : " + query + " on database : " + dbName);
				break;
			}

		}

		return rs;
	}

	public static boolean setString(String dbName, String query) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InterruptedException {

		int rs = setResultSet(dbName, query);
		return (rs == 1) ? true : false;

	}

}