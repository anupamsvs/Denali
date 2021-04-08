package com.pwc.denali2.estimator.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public class SendMail {
	public void sendTest(String status,String toMail) throws UnsupportedEncodingException, MessagingException {
		EmailData data = new EmailData();
		data.setDebug("true");

		data.setSmtpHost("smtp.gmail.com");
		data.setSmtpPort("587");
		data.setUser("username");
		data.setPassword("123456");
		data.setFromAddress("username@gmail.com");
		data.setFromPersonal("Denali");
		data.setToAddress(toMail);
		data.setSubject("Your engagement status has been set to : "+ status +".");


		data.setContent("Your engagement status has been set to : "+ status +".<br>"+"This is an automated email. Please do not reply to this message.");

		Email mail = new EmailImpl(data);
		mail.send();
	}
}
