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

	private String smtp = "";

	private String subject = "";

	private String content = "";

	private String mailFrom = "";

	private String recipientTo = "";

	private static int counter = 0;

	synchronized protected int updateCounter() {
		return counter++;
	}

	@Override
	public void run() {
		int localCounter = updateCounter();

		Properties props = new Properties();
		props.put("mail.smtp.host", getSmtp());
		props.put("mail.from", getMailFrom());

		Session session = Session.getInstance(props, null);

		try {
			LOGGER.debug("[" + localCounter + "] Sending mail to "
					+ getRecipientTo());
			MimeMessage msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, getRecipientTo());
			msg.setSubject(getSubject());
			msg.setSentDate(new Date());
			msg.setText(getContent());
			Transport.send(msg);
			LOGGER.debug("[" + localCounter + "] Mail has been sent to "
					+ getRecipientTo());
		} catch (MessagingException mex) {
			LOGGER.error("[" + localCounter + "] ", mex);
		}
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

	/**
	 * @param mailFrom
	 *            the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param smtp
	 *            the smtp to set
	 */
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	/**
	 * @return the smtp
	 */
	public String getSmtp() {
		return smtp;
	}
}
