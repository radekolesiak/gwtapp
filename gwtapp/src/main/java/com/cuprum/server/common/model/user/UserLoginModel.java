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
			throws UserNotFoundException, InvalidPasswordException {

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
			if (Util.isNull(execute(new CrLogin(login)))) {
				throw new UserNotFoundException();
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

	public boolean exists(User user) {
		return exists(user.getLogin());
	}

	public boolean exists(String login) {
		return Util.isNotNull(execute(new CrLogin(login)));
	}

	public void register(User user) {
		register(user.getLogin(), user.getPassword());
	}

	public void register(String login, String password) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
	}
}
