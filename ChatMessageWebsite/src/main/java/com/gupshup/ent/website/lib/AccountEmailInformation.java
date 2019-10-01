package com.gupshup.ent.website.lib;

public class AccountEmailInformation extends AccountInformation
{

	int emailBalance = 0;

	String emailExpiryDate = "";

	int emailRemainingDays = 0;

	public static AccountEmailInformation account_email;

	public static void setAccountEmail(AccountEmailInformation account_email1)
	{
		account_email = account_email1;
	}

	public static AccountEmailInformation getAccountEmail()
	{
		return account_email;
	}

	@Override
	public void setAccountBalance(int balance_credits)
	{
		emailBalance = balance_credits;
	}

	@Override
	public void setAccountExpiryDate(String expiryDate)
	{
		emailExpiryDate = expiryDate;
	}

	@Override
	public void setAccountExpiryRemainingDays(int days)
	{
		emailRemainingDays = days;
	}

	@Override
	public int getAccountBalance()
	{
		return emailBalance;
	}

	@Override
	public String getAccountExpiryDate()
	{
		return emailExpiryDate;
	}

	@Override
	public int getAccountExpiryRemainingDays()
	{
		return emailRemainingDays;
	}

}
