package com.cuprum.server.common.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class Mail extends Thread {
	private final static Logger LOGGER = Logger.getLogger(Mail.class);

	private String content = "";

	private String recipientTo = "";

	public static void send() {
		send("Hello, world!\n", "r.olesiak@gmail.com");
	}

	public static void send(String content, String recipientTo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "ks356422.kimsufi.com");
		props.put("mail.from", "radek@ks356422.kimsufi.com");

		Session session = Session.getInstance(props, null);

		try {
			LOGGER.debug("Sending mail to " + recipientTo);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom();
			msg.setRecipients(Message.RecipientType.TO, recipientTo);
			msg.setSubject("JavaMail hello world example");
			msg.setSentDate(new Date());
			msg.setText(content);
			Transport.send(msg);
			LOGGER.debug("Mail has been sent");
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

	public static void main(String[] args) {
		send();
	}
}
