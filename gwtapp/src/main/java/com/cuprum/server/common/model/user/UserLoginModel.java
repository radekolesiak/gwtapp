package com.cuprum.server.common.model.user;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.CrLogin;
import com.cuprum.server.common.model.user.xql.CrLoginPassword;
import com.cuprum.server.common.model.usersession.xql.CrCleanUserSessions;
import com.cuprum.server.common.model.usersession.xql.CrGetUserSession;
import com.cuprum.server.common.utils.Random;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotConfirmedException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;

public class UserLoginModel extends Model {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UserLoginModel.class);

	public UserSession login(User user) throws UserNotFoundException,
			InvalidPasswordException {
		return login(user.getLogin(), user.getPassword());
	}

	public UserSession login(String login, String password)
			throws UserNotFoundException, UserNotConfirmedException,
			InvalidPasswordException {

		UserRegisterModel modelRegister = getDAO().getBean(
				UserRegisterModel.class);

		execute(new CrCleanUserSessions());

		User user = execute(new CrLoginPassword(login, password));

		if (Util.isNotNull(user)) {
			UserSession connection = new UserSession();

			// TODO: get unique session id by database!
			connection.setSession(Random.getUid());

			connection.setUser(user);
			connection.setDate(new Date());

			save(connection);

			return connection;
		} else {
			if (!existsLogin(login)) {
				throw new UserNotFoundException();
			} else if (modelRegister.isToConfirm(login)) {
				throw new UserNotConfirmedException();
			} else {
				throw new InvalidPasswordException();
			}
		}
	}

	public void logout(String session) {
		logout((UserSession) execute(new CrGetUserSession(session)));
	}

	public void logout(UserSession session) {
		delete(session);
	}

	public boolean existsLogin(User user) {
		return existsLogin(user.getLogin());
	}

	public boolean existsLogin(String login) {
		return Util.isNotNull(execute(new CrLogin(login)));
	}
}
