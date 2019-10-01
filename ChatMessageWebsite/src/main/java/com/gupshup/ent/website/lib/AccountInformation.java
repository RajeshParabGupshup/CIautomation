package com.gupshup.ent.website.lib;

public abstract class AccountInformation
{
	public abstract void setAccountBalance(int balance_credits);

	public abstract void setAccountExpiryDate(String expiryDate);

	public abstract void setAccountExpiryRemainingDays(int days);

	public abstract int getAccountBalance();

	public abstract String getAccountExpiryDate();

	public abstract int getAccountExpiryRemainingDays();
}
