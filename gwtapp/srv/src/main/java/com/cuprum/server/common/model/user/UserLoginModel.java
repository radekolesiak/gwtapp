package com.cuprum.server.common.model.user;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.XqlLogin;
import com.cuprum.server.common.model.user.xql.XqlLoginPassword;
import com.cuprum.server.common.model.usersession.IUserSessionModel;
import com.cuprum.server.common.utils.Random;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotConfirmedException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;

public class UserLoginModel extends Model implements IUserLoginModel {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UserLoginModel.class);

	private void verifyUserInstance(String login) throws UserNotFoundException,
			UserNotConfirmedException, InvalidPasswordException {
		IUserRegisterModel modelRegister = getDAO().getBean(
				IUserRegisterModel.class);

		if (!existsLogin(login)) {
			throw new UserNotFoundException();
		} else if (modelRegister.isToConfirm(login)) {
			throw new UserNotConfirmedException();
		}
	}

	public User getUser(String login) throws UserNotFoundException,
			UserNotConfirmedException {
		verifyUserInstance(login);
		User user = execute(new XqlLogin(login));
		return user;
	}

	public User getUser(String login, String password)
			throws UserNotFoundException, UserNotConfirmedException,
			InvalidPasswordException {
		verifyUserInstance(login);
		User user = execute(new XqlLoginPassword(login, password));
		return user;
	}

	public UserSession login(User user) throws UserNotFoundException,
			InvalidPasswordException {
		return login(user.getLogin(), user.getPassword());
	}

	public UserSession login(String login, String password)
			throws UserNotFoundException, UserNotConfirmedException,
			InvalidPasswordException {

		IUserSessionModel modelSession = getDAO().getBean(
				IUserSessionModel.class);

		modelSession.cleanSessions();

		verifyUserInstance(login);

		User user = getUser(login, password);

		if (user == null) {
			throw new InvalidPasswordException();
		}

		UserSession connection = new UserSession();

		// TODO: get unique session id by database!
		connection.setSession(Random.getUid());

		connection.setUser(user);
		connection.setDate(new Date());

		save(connection);

		return connection;
	}

	public void logout(String session) {
		IUserSessionModel model = getDAO().getBean(IUserSessionModel.class);
		logout(model.getSession(session));
	}

	public void logout(UserSession session) {
		delete(session);
	}

	public boolean existsLogin(User user) {
		return existsLogin(user.getLogin());
	}

	public boolean existsLogin(String login) {
		return Util.isNotNull(execute(new XqlLogin(login)));
	}
}
