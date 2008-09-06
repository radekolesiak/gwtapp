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

	private String content = "";

	private String recipientTo = "";

	public static void send() {
		send("Hello, world!\n", "r.olesiak@gmail.com");
	}

	public static void send(String content, String recipientTo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "docnotify.com");
		props.put("mail.from", "radek@docnotify.com");

		Session session = Session.getInstance(props, null);

		try {
			LOGGER.error("Sending mail");
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom();
			msg.setRecipients(Message.RecipientType.TO, recipientTo);
			msg.setSubject("JavaMail hello world example");
			msg.setSentDate(new Date());
			msg.setText(content);
			Transport.send(msg);
			LOGGER.error("Mail has been sent");
		} catch (MessagingException mex) {
			LOGGER.error(mex);
		}
	}

	public void run() {
		send(getContent(), getRecipientTo());
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param recipientTo
	 *            the recipientTo to set
	 */
	public void setRecipientTo(String recipientTo) {
		this.recipientTo = recipientTo;
	}

	/**
	 * @return the recipientTo
	 */
	public String getRecipientTo() {
		return recipientTo;
	}
}

public class Mail {
	public static void main(String[] args) {
		SendMail.send();
	}
}
