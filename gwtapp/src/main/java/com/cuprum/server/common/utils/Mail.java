package com.cuprum.server.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

class SendMail extends Thread {
	private final static Logger LOGGER = Logger.getLogger(SendMail.class);

	public SendMail() {
		start();
	}

	public static void send() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "docnotify.com");
		props.put("mail.from", "radek@docnotify.com");

		Session session = Session.getInstance(props, null);

		try {
			LOGGER.error("Sending mail");
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom();
			msg.setRecipients(Message.RecipientType.TO, "r.olesiak@gmail.com");
			msg.setSubject("JavaMail hello world example");
			msg.setSentDate(new Date());
			msg.setText("Hello, world!\n");

			Transport.send(msg);
			LOGGER.error("Mail has been sent");
		} catch (MessagingException mex) {
			LOGGER.error(mex);
		}
	}

	public void run() {
		send();
	}
}

public class Mail {
	public static void main(String[] args) {
		SendMail.send();
	}
}
