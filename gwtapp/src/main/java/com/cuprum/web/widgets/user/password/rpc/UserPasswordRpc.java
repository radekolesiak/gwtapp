package com.cuprum.web.widgets.user.password.rpc;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.property.PropertyModel;
import com.cuprum.server.common.model.user.UserLoginModel;
import com.cuprum.server.common.model.user.UserPasswordModel;
import com.cuprum.server.common.model.usersession.UserSessionModel;
import com.cuprum.server.common.utils.Mail;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.common.rpc.RemoteServiceServletUserSession;
import com.cuprum.web.widgets.common.client.exception.RpcException;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.register.client.Constants;
import com.cuprum.web.widgets.user.register.properties.Properties;

public class UserPasswordRpc extends RemoteServiceServletUserSession implements
		IUserPassword {

	private final static Logger LOGGER = Logger
			.getLogger(UserPasswordRpc.class);

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 4954867121395247984L;

	public TChangePasswordByUser processChangePasswordByUser(
			TChangePasswordByUser passwords) throws SessionNotFoundException {

		passwords.clearErrors();

		UserPasswordModel modelPassword = getDAO().getBean(
				UserPasswordModel.class);
		UserSessionModel modelSession = getDAO()
				.getBean(UserSessionModel.class);

		UserSession session = modelSession.getSession(getUserSession());

		modelPassword.verifyOldPassword(session, passwords.oldPassword);
		modelPassword.verifyNewPasswordDual(passwords.newPassword);

		if (!passwords.hasErrors()) {
			modelPassword.updatePasswordByUser(session, passwords.newPassword);
		}

		return passwords;
	}

	public TChangePasswordGetToken changePasswordGetToken(
			TChangePasswordGetToken login) {

		login.clearError();

		UserLoginModel modelLogin = getDAO().getBean(UserLoginModel.class);

		UserPasswordModel modelPassword = getDAO().getBean(
				UserPasswordModel.class);

		Property remindUrl = getBean(PropertyModel.class).get(
				Properties.REMIND_URL);

		try {
			User user = modelLogin.getUser(login.get());

			UserPasswordRemind remind = modelPassword
					.getPasswordRemindToken(user);

			String smtp = getBean(PropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_SMTP_SERVER)
					.getValue();
			String noreply = getBean(PropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_USER_NOREPLY)
					.getValue();

			Mail mail = new Mail();
			mail.setSmtp(smtp);
			mail.setMailFrom(noreply);
			mail.setRecipientTo(user.getMail());
			mail.setSubject("Password remind.");
			mail.setContent(remindUrl + "?" + Constants.REMIND_REQUEST + "="
					+ remind.getUid());
			mail.start();
		} catch (RpcException e) {
			login.error = e;
		}

		return login;
	}

	public TChangePasswordByToken changePasswordByToken(
			TChangePasswordByToken password) {

		password.clearErrors();

		UserPasswordModel modelPassword = getDAO().getBean(
				UserPasswordModel.class);

		modelPassword.verifyNewPasswordDual(password.password);
		modelPassword.verifyRemindPasswordToken(password.uid);

		if (!password.hasErrors()) {
			modelPassword.updatePasswordByToken(password);
		}

		return password;
	}
}
