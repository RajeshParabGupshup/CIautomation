package com.gupshup.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils
{
	private static List<String> fileList;

	private static String OUTPUT_ZIP_FILE = "";

	private static String SOURCE_FOLDER = "";

	public ZipUtils()
	{
		fileList = new ArrayList<String>();
	}

	public static void zipFailedTestCaseScreenshot(String SourceFolder, String OutputZipFile)
	{

		SOURCE_FOLDER = SourceFolder;
		OUTPUT_ZIP_FILE = OutputZipFile;
		
		ZipUtils appZip = new ZipUtils();
		ZipUtils.generateFileList(new File(SOURCE_FOLDER));
		appZip.zipIt(OUTPUT_ZIP_FILE);
	}

	public static void zipFolder(String folderInputSource, String fileOutputZip)
	{
		SOURCE_FOLDER = folderInputSource;
		OUTPUT_ZIP_FILE = fileOutputZip;
		File file = new File(SOURCE_FOLDER);
		if (file.exists() && file.list().length > 0)
		{
			ZipUtils appZip = new ZipUtils();
			ZipUtils.generateFileList(new File(SOURCE_FOLDER));
			appZip.zipIt(OUTPUT_ZIP_FILE);
		}
		else
			System.out.println("Source Directory is not exists or empty");
	}

	public void zipIt(String zipFile)
	{
		byte[] buffer = new byte[1024];
		String source = new File(SOURCE_FOLDER).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try
		{

			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);
			FileInputStream in = null;

			for (String file : this.fileList)
			{
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try
				{
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0)
					{
						zos.write(buffer, 0, len);
					}
				}
				finally
				{
					in.close();
				}
			}

			zos.closeEntry();
			System.out.println("Folder successfully compressed");

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				zos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void generateFileList(File node)
	{
		// add file only
		if (node.isFile())
		{
			fileList.add(generateZipEntry(node.toString()));
		}

		if (node.isDirectory())
		{
			String[] subNote = node.list();
			for (String filename : subNote)
			{
				generateFileList(new File(node, filename));
			}
		}
	}

	private static String generateZipEntry(String file)
	{
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}
