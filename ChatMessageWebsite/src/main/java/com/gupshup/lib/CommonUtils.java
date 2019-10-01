package com.gupshup.lib;

public class CommonUtils
{
	public int getAccountTypeKey(String accounttype)
	{
		if (accounttype.equals("trans"))
			return 1;
		else if (accounttype.equals("promo"))
			return 2;
		else
			return 0;
	}
}
