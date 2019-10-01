package com.gupshup.ent.website.lib;

public class AccountVoiceInformation extends AccountInformation
{

	int voiceBalance = 0;

	String voiceExpiryDate = "";

	int voiceRemainingDays = 0;

	public static AccountVoiceInformation account_voice;

	public static void setAccountVoice(AccountVoiceInformation account_voice1)
	{
		account_voice = account_voice1;
	}

	public static AccountVoiceInformation getAccountVoice()
	{
		return account_voice;
	}

	@Override
	public void setAccountBalance(int balance_credits)
	{
		voiceBalance = balance_credits;
	}

	@Override
	public void setAccountExpiryDate(String expiryDate)
	{
		voiceExpiryDate = expiryDate;
	}

	@Override
	public void setAccountExpiryRemainingDays(int days)
	{
		voiceRemainingDays = days;
	}

	@Override
	public int getAccountBalance()
	{
		return voiceBalance;
	}

	@Override
	public String getAccountExpiryDate()
	{
		return voiceExpiryDate;
	}

	@Override
	public int getAccountExpiryRemainingDays()
	{
		return voiceRemainingDays;
	}

}
