package org.gwtapp.startapp.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendFeedback {

	private final static Logger log = Logger.getLogger(SendFeedback.class);

	public void sendFeedback(String from, String feedback) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		InternetAddress me = new InternetAddress("r.olesiak@gmail.com",
				"Radek Olesiak");
		msg.setFrom(me);
		msg.setSubject("gwtapp.org feedback from: " + from);
		msg.setText(feedback);
		msg.addRecipient(Message.RecipientType.TO, me);
		Transport.send(msg);
		log.info("Feedback has been sent");
	}
}
