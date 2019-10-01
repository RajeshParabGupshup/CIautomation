package com.gupshup.ent.website.lib;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.gupshup.lib.TestNGLogger;

public class CSVLib
{

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";

	private static final String NEW_LINE_SEPARATOR = "\n";

	public static void generateBulkPhoneNumber(String fileName, List<String> headers, List<String> numbers)
	{
		FileWriter fileWriter = null;
		try
		{
			TestNGLogger.logOnlyToConsole("Trying to create file " + fileName);

			fileWriter = new FileWriter(fileName);
			// Write the CSV file header
			int size = headers.size();
			int i = 1;
			for (String head : headers)
			{
				if (size == i)
					fileWriter.append(head);
				else
					fileWriter.append(head + COMMA_DELIMITER);
				i++;
			}
			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			// Write a new student object list to the CSV file
			for (String no1 : numbers)
			{

				fileWriter.append(String.valueOf(no1));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			TestNGLogger.logInfo("CSV file was created successfully !!!");
		}
		catch (Exception e)
		{
			TestNGLogger.logInfo("Error in CsvFileWriter !!!");
			e.printStackTrace();

		}
		finally
		{
			try
			{
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e)
			{
				TestNGLogger.logInfo("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();

			}
		}
	}
}
