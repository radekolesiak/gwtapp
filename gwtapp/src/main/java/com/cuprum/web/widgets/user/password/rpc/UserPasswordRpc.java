package com.cuprum.web.widgets.user.password.rpc;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.user.UserPasswordModel;
import com.cuprum.server.common.model.usersession.UserSessionModel;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.common.rpc.RemoteServiceServletUserSession;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;

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
			User user = modelPassword
					.get(User.class, session.getUser().getId());
			user.setPassword(passwords.newPassword.value);
			modelPassword.update(user);
		}

		return passwords;
	}
}
