package com.gupshup.ent.website.lib;

public class AccountSMSInformation extends AccountInformation
{

	int smsBalance = 0;

	String smsExpiryDate = "";

	int smsRemainingDays = 0;

	public static AccountSMSInformation account_sms;

	public static void setAccountSMS(AccountSMSInformation account_sms1)
	{
		account_sms = account_sms1;
	}

	public static AccountSMSInformation getAccountSMS()
	{
		return account_sms;
	}

	@Override
	public void setAccountBalance(int balance_credits)
	{
		smsBalance = balance_credits;
	}

	@Override
	public void setAccountExpiryDate(String expiryDate)
	{
		smsExpiryDate = expiryDate;
	}

	@Override
	public void setAccountExpiryRemainingDays(int days)
	{
		smsRemainingDays = days;
	}

	@Override
	public int getAccountBalance()
	{
		return smsBalance;
	}

	@Override
	public String getAccountExpiryDate()
	{
		return smsExpiryDate;
	}

	@Override
	public int getAccountExpiryRemainingDays()
	{
		return smsRemainingDays;
	}

}
