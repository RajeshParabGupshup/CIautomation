package com.gupshup.ent.website.framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AccountConfigBO
{
	private static String loginuserId = "";

	private static String loginpassword = "";

	private static String accountType = "";

	boolean isTemplateFlag = false;

	private static String envurl = "";

	private static String trans_userId_templatetrue = "";

	private static String trans_password_templatetrue = "";

	private static String trans_userId_templatefalse = "";

	private static String trans_password_templatefalse = "";

	private static String promo_userId = "";

	private static String promo_password = "";

	static String configFilePath = "/src/main/resources/account.config.properties";

	private static FileInputStream inputStream = null;

	public AccountConfigBO(String env)
	{
		if (env.equalsIgnoreCase("PROD"))
			setProdConfig();
		else
			setQAConfig();

		envurl = System.getProperty("webURL");
		if (!envurl.isEmpty())
		{
			AccountConfigBO.setEnvurl(envurl);
		}

		loginuserId = System.getProperty("userId");
		loginpassword = System.getProperty("pass");
		accountType = System.getProperty("accountType");
		boolean isTemplateFlag = System.getProperty("isTemplate").contains("true") ? true : false;

		if (!loginuserId.isEmpty() && !loginpassword.isEmpty() && !accountType.isEmpty())
		{
			if (accountType.equalsIgnoreCase("promo"))
			{
				AccountConfigBO.setPromo_userId(loginuserId);
				AccountConfigBO.setPromo_password(loginpassword);
			}
			else if (accountType.equalsIgnoreCase("trans") && (isTemplateFlag))
			{
				AccountConfigBO.setTrans_userId_templatetrue(loginuserId);
				AccountConfigBO.setTrans_password_templatetrue(loginpassword);
			}
			else if (accountType.equalsIgnoreCase("trans") && (!isTemplateFlag))
			{
				AccountConfigBO.setTrans_userId_templatefalse(loginuserId);
				AccountConfigBO.setTrans_password_templatefalse(loginpassword);
			}
		}
	}

	public static void setQAConfig()
	{
		try
		{
			inputStream = new FileInputStream(new File(configFilePath));
			Properties properties = new Properties();
			properties.load(inputStream);
			AccountConfigBO.setEnvurl("qa_envurl");
			AccountConfigBO.setTrans_userId_templatetrue("qa_trans_userId_templatetrue");
			AccountConfigBO.setTrans_password_templatetrue("qa_trans_password_templatetrue");
			AccountConfigBO.setTrans_userId_templatefalse("qa_trans_userId_templatefalse");
			AccountConfigBO.setTrans_password_templatefalse("qa_trans_password_templatefalse");
			AccountConfigBO.setPromo_userId("qa_promo_userId");
			AccountConfigBO.setPromo_password("qa_promo_password");
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
	}

	public static void setProdConfig()
	{
		try
		{
			inputStream = new FileInputStream(new File(configFilePath));
			Properties properties = new Properties();
			properties.load(inputStream);
			AccountConfigBO.setEnvurl("prod_envurl");
			AccountConfigBO.setTrans_userId_templatetrue("prod_trans_userId_templatetrue");
			AccountConfigBO.setTrans_password_templatetrue("prod_trans_password_templatetrue");
			AccountConfigBO.setTrans_userId_templatefalse("prod_trans_userId_templatefalse");
			AccountConfigBO.setTrans_password_templatefalse("prod_trans_password_templatefalse");
			AccountConfigBO.setPromo_userId("prod_promo_userId");
			AccountConfigBO.setPromo_password("prod_promo_password");
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
	}

	public static String getEnvurl()
	{
		return envurl;
	}

	public static void setEnvurl(String envurl)
	{
		AccountConfigBO.envurl = envurl;
	}

	public static String getTrans_userId_templatetrue()
	{
		return trans_userId_templatetrue;
	}

	public static void setTrans_userId_templatetrue(String trans_userId_templatetrue)
	{
		AccountConfigBO.trans_userId_templatetrue = trans_userId_templatetrue;
	}

	public static String getTrans_password_templatetrue()
	{
		return trans_password_templatetrue;
	}

	public static void setTrans_password_templatetrue(String trans_password_templatetrue)
	{
		AccountConfigBO.trans_password_templatetrue = trans_password_templatetrue;
	}

	public static String getTrans_userId_templatefalse()
	{
		return trans_userId_templatefalse;
	}

	public static void setTrans_userId_templatefalse(String trans_userId_templatefalse)
	{
		AccountConfigBO.trans_userId_templatefalse = trans_userId_templatefalse;
	}

	public static String getTrans_password_templatefalse()
	{
		return trans_password_templatefalse;
	}

	public static void setTrans_password_templatefalse(String trans_password_templatefalse)
	{
		AccountConfigBO.trans_password_templatefalse = trans_password_templatefalse;
	}

	public static String getPromo_userId()
	{
		return promo_userId;
	}

	public static void setPromo_userId(String promo_userId)
	{
		AccountConfigBO.promo_userId = promo_userId;
	}

	public static String getPromo_password()
	{
		return promo_password;
	}

	public static void setPromo_password(String promo_password)
	{
		AccountConfigBO.promo_password = promo_password;
	}

}
