package com.cuprum.server.widgets.user.password.rpc;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.property.IPropertyModel;
import com.cuprum.server.common.model.user.IUserLoginModel;
import com.cuprum.server.common.model.user.IUserPasswordModel;
import com.cuprum.server.common.model.usersession.IUserSessionModel;
import com.cuprum.server.common.rpc.RemoteServiceUserSession;
import com.cuprum.server.common.utils.Mail;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.widgets.common.client.exception.RpcException;
import com.cuprum.web.widgets.user.password.client.ChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.register.properties.Properties;

public class UserPasswordRpc extends RemoteServiceUserSession implements
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

		IUserPasswordModel modelPassword = getDAO().getBean(
				IUserPasswordModel.class);
		IUserSessionModel modelSession = getDAO().getBean(
				IUserSessionModel.class);

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

		IUserLoginModel modelLogin = getDAO().getBean(IUserLoginModel.class);

		IUserPasswordModel modelPassword = getDAO().getBean(
				IUserPasswordModel.class);

		Property remindUrl = getBean(IPropertyModel.class).get(
				Properties.REMIND_URL);

		try {
			User user = modelLogin.getUser(login.get());

			UserPasswordRemind remind = modelPassword
					.getPasswordRemindToken(user);

			String smtp = getBean(IPropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_SMTP_SERVER)
					.getValue();
			String noreply = getBean(IPropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_USER_NOREPLY)
					.getValue();

			Mail mail = new Mail();
			mail.setSmtp(smtp);
			mail.setMailFrom(noreply);
			mail.setRecipientTo(user.getMail());
			mail.setSubject("Password remind.");
			mail.setContent(remindUrl + "?"
					+ ChangePasswordByToken.REMIND_REQUEST + "="
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

		IUserPasswordModel modelPassword = getDAO().getBean(
				IUserPasswordModel.class);

		modelPassword.verifyNewPasswordDual(password.password);
		modelPassword.verifyRemindPasswordToken(password.uid);

		if (!password.hasErrors()) {
			modelPassword.updatePasswordByToken(password);
		}

		return password;
	}
}
