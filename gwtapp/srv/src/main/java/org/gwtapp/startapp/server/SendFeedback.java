package org.gwtapp.startapp.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendFeedback {

	private final static Logger log = Logger.getLogger(SendFeedback.class);

	public void sendFeedback(String from, String feedback) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("r.olesiak@gmail.com",
					"Radek Olesiak"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"r.olesiak@gmail.com", "Radek Olesiak"));
			msg.setSubject("Feedback from: " + from);
			msg.setText(feedback);
			Transport.send(msg);
			log.debug("Feedback has been sent");
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		} catch (AddressException e) {
			log.error("", e);
		} catch (MessagingException e) {
			log.error("", e);
		}
	}
}
