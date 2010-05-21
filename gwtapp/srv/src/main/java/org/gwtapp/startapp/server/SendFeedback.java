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
		if (isValidEmail(from)) {
			msg.setFrom(new InternetAddress(from, true));
			msg.setSubject("gwtapp.org feedback");
			log.debug("VALID EMAIL");
		} else {
			msg.setFrom(new InternetAddress("r.olesiak@gmail.com",
					"Radek Olesiak"));
			msg.setSubject("gwtapp.org feedback from: " + from);
			log.debug("INVALID EMAIL");
		}
		msg.setText(feedback);
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"r.olesiak@gmail.com", "Radek Olesiak"));
		Transport.send(msg);
		log.info("Feedback has been sent");
	}

	private boolean isValidEmail(String email) {
		return false;
	}
}
