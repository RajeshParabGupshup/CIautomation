package com.gupshup.ent.website.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gupshup.lib.SMSType;
import com.gupshup.lib.TestNGLogger;

public class ENTWebsiteOps {

	static int limitSimpleEnglishSMSCount = 8000;

	static int limitOtherLanguageSMSCount = 2000;

	static int englishFirstBillableLength = 160;
	static int englishSecondBillableLength = 146;
	static int englishThreeBillableLength = 153;

	static int unicodeFirstBillableLength = 70;
	static int unicodeSecondBillableLength = 64;
	static int unicodeThreeBillableLength = 67;

	public static boolean verifyExpiryDaysLimit(String typeExpiryDescription, int daysLimit) {
		boolean flagExpiryDaysVerify = false;

		String daysPattern = ": (\\d)+";
		Pattern r = Pattern.compile(daysPattern);
		Matcher m = r.matcher(typeExpiryDescription);
		while (m.find()) {
			String strDays = m.group(0);
			strDays = strDays.substring(2, strDays.length());
			strDays = strDays.replaceAll(",", "");
			TestNGLogger.logInfo("Available Expiry Days remaining = " + strDays);
			int days = Integer.parseInt(strDays);
			if (days > daysLimit)
				flagExpiryDaysVerify = true;
		}
		return flagExpiryDaysVerify;
	}

	public static boolean verifyExpiryCreditLimit(String typeExpiryDescription, int creditLimit) {
		boolean flagExpiryCreditVerify = false;

		String creditPattern = "e (\\d+\\,?)+";
		Pattern r = Pattern.compile(creditPattern);
		Matcher m = r.matcher(typeExpiryDescription);
		while (m.find()) {
			String strCredit = m.group(0);
			strCredit = strCredit.substring(2, strCredit.length());
			strCredit = strCredit.replaceAll(",", "");
			TestNGLogger.logInfo("Available Credit remaining = " + strCredit);
			int credits = Integer.parseInt(strCredit);
			if (credits >= creditLimit)
				flagExpiryCreditVerify = true;
		}
		return flagExpiryCreditVerify;
	}

	public static boolean verifyExpiryDateLimit(String typeExpiryDescription, int daysLimit) {
		boolean flagDateVerify = false;

		String daysPattern = "n [ \\t]*([^\\n\\r]*), ";
		Pattern r = Pattern.compile(daysPattern);
		Matcher m = r.matcher(typeExpiryDescription);
		while (m.find()) {
			String strDays = m.group(0);
			strDays = strDays.substring(2, (strDays.length() - 2));
			System.out.println("Days = " + strDays);
		}
		return flagDateVerify;
	}

	public static int getMessageSMSCount(SMSType typeMessage, int lengthMessage) {
		int nSegments = 0;
		int firstBillableLength = 0;
		int secondBillableLength = 0;
		int ThreeBillableLength = 0;

		if (typeMessage == SMSType.ENGLISH) {
			firstBillableLength = englishFirstBillableLength;
			secondBillableLength = englishSecondBillableLength;
			ThreeBillableLength = englishThreeBillableLength;
		} else if (typeMessage == SMSType.UNICODE) {
			firstBillableLength = unicodeFirstBillableLength;
			secondBillableLength = unicodeSecondBillableLength;
			ThreeBillableLength = unicodeThreeBillableLength;
		}

		if (lengthMessage <= firstBillableLength) {
			nSegments = 1;
		} else {
			nSegments = 1;
			int newLength = lengthMessage - firstBillableLength;
			if (newLength <= secondBillableLength) {
				nSegments++;
			} else {
				int newLengthAgain = newLength - secondBillableLength;
				nSegments++;

				int tempThreeCount = 0;
				if (newLengthAgain % ThreeBillableLength == 0) {
					tempThreeCount = newLengthAgain / ThreeBillableLength;
				} else {
					tempThreeCount = newLengthAgain / ThreeBillableLength + 1;
				}
				nSegments += tempThreeCount;
			}
		}
		return nSegments;

	}

	public static int getMessageCharacterLeft(String typeSMS, int lengthSMS) {
		int leftChar = 0;

		if (typeSMS.contains("englishSimple"))
			leftChar = limitSimpleEnglishSMSCount - lengthSMS;
		else if (typeSMS.contains("englisheUnicode"))
			leftChar = limitOtherLanguageSMSCount - lengthSMS;

		return leftChar;
	}
}
