package com.cuprum.server.common.model.user;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.CrLogin;
import com.cuprum.server.common.model.user.xql.CrLoginPassword;
import com.cuprum.server.common.model.user.xql.CrMail;
import com.cuprum.server.common.model.user.xql.CrUserConfirm;
import com.cuprum.server.common.model.usersession.xql.CrCleanUserSessions;
import com.cuprum.server.common.model.usersession.xql.CrGetUserSession;
import com.cuprum.server.common.utils.Random;
import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.MailAlreadyExistsException;
import com.cuprum.web.common.client.exceptions.model.user.UserAlreadyExistsException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;

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

	public boolean existsLogin(User user) {
		return existsLogin(user.getLogin());
	}

	public boolean existsLogin(String login) {
		return Util.isNotNull(execute(new CrLogin(login)));
	}

	public boolean existsMail(User user) {
		return existsMail(user.getMail());
	}

	public boolean existsMail(String mail) {
		return Util.isNotNull(execute(new CrMail(mail)));
	}

	public UserConfirm register(TUserRegisterValue userRegister) {
		if (!errorsUserRegister(userRegister)) {
			// If there are not error to try insert user into database.
			User user = new User();
			user.setLogin(userRegister.login.get());
			user.setPassword(userRegister.password.get());
			user.setMail(userRegister.mail.get());
			try {
				save(user);
				UserConfirm confirm = new UserConfirm();
				confirm.setDate(new Date());
				confirm.setUid(Random.getUid());
				confirm.setUser(user);
				confirm.setPassword(user.getPassword());
				user.setPassword(Random.getUid());
				update(user);
				save(confirm);
				return confirm;
			} catch (DataIntegrityViolationException e) {
				// Probably threads concurency problem. Check it again.
				if (!errorsUserRegister(userRegister)) {
					// Unidentified problem. To throw upper the exception.
					LOGGER.error(e);
					throw e;
				}
			}
		}
		return null;
	}

	public boolean errorsUserRegister(TUserRegisterValue userRegister) {
		userRegister.clearErrors();

		TStringValue login = userRegister.getValueLogin();

		if (login instanceof HasRegExp
				&& !((HasRegExp) login).match(login.value)) {
			login.error = new RegExpException();
		} else if (login.value.length() < TUserRegisterValue.MIN_LOGIN_LENGTH) {
			login.error = new TextToShortException();
		} else if (existsLogin(login.value)) {
			login.error = new UserAlreadyExistsException();
		}

		TDualStringValue password = userRegister.getValuePassword();

		if (!password.value.equals(password.second)) {
			password.error = new DualTextFieldInvalidException();
		} else if (password.value.length() < TUserRegisterValue.MIN_PASSWORD_LENGTH) {
			password.error = new TextToShortException();
		}

		TDualStringValue mail = userRegister.getValueMail();

		if (!mail.value.equals(mail.second)) {
			mail.error = new DualTextFieldInvalidException();
		} else if (mail instanceof HasRegExp
				&& !((HasRegExp) mail).match(mail.value)) {
			mail.error = new RegExpException();
		} else if (existsMail(mail.value)) {
			mail.error = new MailAlreadyExistsException();
		}

		return userRegister.hasErrors();
	}

	public boolean confirm(String uid) {
		UserConfirm confirm = execute(new CrUserConfirm(uid));
		if (confirm != null) {
			User user = get(User.class, confirm.getUser().getId());
			user.setPassword(confirm.getPassword());
			update(user);
			delete(confirm);
			return user != null;
		} else {
			return false;
		}
	}
}
