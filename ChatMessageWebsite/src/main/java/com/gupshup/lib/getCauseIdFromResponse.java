package com.gupshup.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getCauseIdFromResponse {

	public static String getNumbersFromString(String strData) {
		String causeId = null ;
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(strData);
		if (m.find())
			//countMacher = Integer.parseInt(m.group(0));
			causeId = m.group(0);
		return causeId;
	}
}
