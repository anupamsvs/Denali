package com.pwc.denali2.estimator.mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

public class EmailImpl implements Email {
	private EmailData emailData;

	public EmailImpl(EmailData emailData) {
		this.emailData = emailData;
	}

	public void send() {
		final Properties properties = new Properties();

		properties.put("mail.debug", "true".equals(emailData.getDebug()) ? "true" : "false");

		properties.put("mail.smtp.auth", "false".equals(emailData.getSmtpAuth()) ? "false" : "true");
		properties.put("mail.smtp.host", StringUtils.trimToEmpty(emailData.getSmtpHost()));
		properties.put("mail.smtp.port", StringUtils.trimToEmpty(emailData.getSmtpPort()));

		properties.put("mail.smtp.starttls.enable",
				"false".equals(emailData.getSmtpStarttlsEnable()) ? "false" : "true");

		// properties.put("mail.smtp.port", "25");
		// properties.put("mail.smtp.socketFactory.port", "465");
		// properties.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// properties.put("mail.smtp.socketFactory.fallback", "false");

		Session mailSession = null;
		if (StringUtils.isNotEmpty(emailData.getUser()) && StringUtils.isNotEmpty(emailData.getPassword())) {
			properties.put("mail.user", StringUtils.trimToEmpty(emailData.getUser()));
			properties.put("mail.password", StringUtils.trimToEmpty(emailData.getPassword()));
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					String userName = properties.getProperty("mail.user");
					String password = properties.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};

			mailSession = Session.getInstance(properties, authenticator);
		} else {
			mailSession = Session.getInstance(properties);
		}

		MimeMessage message = new MimeMessage(mailSession);

		try {
			if (StringUtils.isNotEmpty(emailData.getFromAddress())) {
				InternetAddress from = new InternetAddress(StringUtils.trimToEmpty(emailData.getFromAddress()));
				from.setPersonal(StringUtils.trimToEmpty(emailData.getFromPersonal()));
				message.setFrom(from);
			}

			String toAddresses = StringUtils.trimToEmpty(emailData.getToAddress());
			if (StringUtils.isNotEmpty(toAddresses)) {
				String[] toAddress = toAddresses.split(",");
				for (int i = 0; i < toAddress.length; i++) {
					InternetAddress to = new InternetAddress(toAddress[i]);
					message.addRecipient(RecipientType.TO, to);
				}
			}

			String ccAddresses = StringUtils.trimToEmpty(emailData.getCcAddress());
			if (StringUtils.isNotEmpty(ccAddresses)) {
				String[] ccAddress = ccAddresses.split(",");
				for (int i = 0; i < ccAddress.length; i++) {
					InternetAddress cc = new InternetAddress(ccAddress[i]);
					message.addRecipient(RecipientType.CC, cc);
				}
			}

			String bccAddresses = StringUtils.trimToEmpty(emailData.getBccAddress());
			if (StringUtils.isNotEmpty(bccAddresses)) {
				String[] bccAddress = bccAddresses.split(",");
				for (int i = 0; i < bccAddress.length; i++) {
					InternetAddress bcc = new InternetAddress(bccAddress[i]);
					message.addRecipient(RecipientType.BCC, bcc);
				}
			}

			message.setSubject(StringUtils.trimToEmpty(emailData.getSubject()));
			// message.setContent(StringUtils.trimToEmpty(emailData.getContent()),
			// "text/html;charset=UTF-8");

			if (StringUtils.isNotBlank(emailData.getAttachmentFilePath())) {
				File logFile = new File(emailData.getAttachmentFilePath());
				Multipart multipart = new MimeMultipart();

				// Add mail content
				BodyPart contentPart = new MimeBodyPart();
				contentPart.setContent(StringUtils.trimToEmpty(emailData.getContent()), "text/html;charset=UTF-8");
				multipart.addBodyPart(contentPart);

				// Add mail file
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(logFile);
				attachmentBodyPart.setDataHandler(new DataHandler(source));

				// Prevent distortion
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(logFile.getName()));
				multipart.addBodyPart(attachmentBodyPart);

				message.setContent(multipart);
			} else {
				message.setContent(StringUtils.trimToEmpty(emailData.getContent()), "text/html;charset=UTF-8");
			}

			Transport.send(message);
		} catch (MessagingException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
