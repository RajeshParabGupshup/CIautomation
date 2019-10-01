package com.gupshup.ent.website.lib;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gupshup.lib.TestNGLogger;

public class ExcelLib
{
	public static void generateBulkPhoneNumber(String fileName, List<String> headers, List<String> numbers)
	{
		try
		{

			TestNGLogger.logOnlyToConsole("Trying to create file " + fileName);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");

			HSSFRow rowhead = sheet.createRow((short) 0);
			int i = 0;
			for (String head : headers)
			{
				rowhead.createCell(i++).setCellValue(head);
			}

			for (String no : numbers)
			{
				HSSFRow row = sheet.createRow((short) 1);
				row.createCell(0).setCellValue(no);
			}

			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
			TestNGLogger.logInfo("Your excel file has been generated!");

		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
}
