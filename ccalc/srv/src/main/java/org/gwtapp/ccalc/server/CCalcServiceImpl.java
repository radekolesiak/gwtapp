package org.gwtapp.ccalc.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gwtapp.ccalc.client.api.CCalcService;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Email;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Login;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CCalcServiceImpl extends RemoteServiceServlet implements
		CCalcService, UserService {

	private final static Logger log = Logger.getLogger(CCalcServiceImpl.class);

	private static final long serialVersionUID = -7438675123588513834L;

	private final static boolean hasAttachment = false;

	@Override
	public void backup(Book book) throws RpcException {
		if (book == null || !isAllowedRecipient(book.getMail())) {
			return;
		}
		Session session = Session.getDefaultInstance(new Properties(), null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("r.olesiak@gmail.com",
					"FIFO Currency Calculator Backup"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(book
					.getMail()));
			msg
					.setSubject("FIFO Currency Calculator Backup: "
							+ book.getName());

			if (!hasAttachment) {
				msg.setText(new String(Utils.encode(book)));
			} else {
				Multipart mp = new MimeMultipart();

				MimeBodyPart htmlPart = new MimeBodyPart();
				htmlPart
						.setContent(new String(Utils.encode(book)), "text/html");
				mp.addBodyPart(htmlPart);

				MimeBodyPart attachment = new MimeBodyPart();
				attachment.setFileName(Utils.getFilename(book));
				attachment.setContent(new String(Utils.encode(book)),
						Utils.CONTENT_TYPE);
				mp.addBodyPart(attachment);

				msg.setContent(mp);
			}
			Transport.send(msg);
		} catch (Exception e) {
			log.error("", e);
			// throw new RuntimeException(e);
			// throw new RpcException();
		}
	}

	private boolean isAllowedRecipient(String recipient) {
		if (recipient == null) {
			return false;
		}
		if ("r.olesiak@gmail.com".equals(recipient)) {
			return true;
		}
		if ("radek@olesiak.biz".equals(recipient)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String login) throws RpcException {
		throw new NotImplementedException();
	}

	@Override
	public long addUser(User user) throws RpcException {
		if (true)
			throw new NotImplementedException();
		UserValidationException validation = new UserValidationException();
		if (StringUtils.isEmpty(user.getLogin())) {
			validation.setLogin(Login.INVALID);
		} else if (user.getLogin().matches("[A-Z]+")) {
			validation.setLogin(Login.NOT_LOWER_CASE);
		} else if (!user.getLogin().matches("[a-z]+")) {
			validation.setLogin(Login.NOT_LETTERS_ONLY);
		} else if (user.getLogin().length() < 3) {
			validation.setLogin(Login.TOO_SHORT);
		}
		if (validation.getLogin() != Login.VALID
				|| validation.getEmail() != Email.VALID) {
			throw validation;
		}
		return -1;
	}
}
