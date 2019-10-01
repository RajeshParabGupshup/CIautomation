package com.gupshup.lib;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailLib
{
	public static void sentMail(String mailSentTo, String subject, String msgBody, String fileReport, String zipFailedTest) throws MalformedURLException, UnsupportedEncodingException
	{

		String from = "bottest@gupshup.io";

		final String username = "bottest@gupshup.io";
		final String password = "Automation@123";
		Multipart multipart = new MimeMultipart();

		String host = "smtp.gmail.com";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from, "Automation Bot"));

			String[] recipientList = mailSentTo.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient : recipientList)
			{
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject(subject);

			// messageBodyPart.setText(msgBody
			// + "\n\n\n\nRegards,\n\nBot Monitoring Test\nwww.gupshup.io\nThe leading bot platform");
			// multipart.addBodyPart(messageBodyPart);
			// messageBodyPart = new MimeBodyPart();

			// message.setContent(multipart,"text/html");

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(msgBody, "text/html");

			// attach report file
			MimeBodyPart attachmentHTMLPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileReport);
			attachmentHTMLPart.setDataHandler(new DataHandler(source));
			attachmentHTMLPart.setFileName(new File(fileReport).getName());

			multipart.addBodyPart(attachmentHTMLPart);
			multipart.addBodyPart(messageBodyPart);
			
			// attach zip file
			if(!zipFailedTest.equals("")) {
				MimeBodyPart attachmentZipPart = new MimeBodyPart();
				DataSource source1 = new FileDataSource(zipFailedTest);
				attachmentZipPart.setDataHandler(new DataHandler(source1));
				attachmentZipPart.setFileName(new File(zipFailedTest).getName());
				multipart.addBodyPart(attachmentZipPart);
			}

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Sent message successfully with the subject '" + subject + "'");

		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
