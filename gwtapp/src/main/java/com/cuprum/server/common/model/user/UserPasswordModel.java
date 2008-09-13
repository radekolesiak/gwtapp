package com.cuprum.server.common.model.user;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.CrUserPasswordRemind;
import com.cuprum.server.common.utils.Random;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.common.client.exception.UnknownTokenException;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;

public class UserPasswordModel extends Model {
	private final static Logger LOGGER = Logger
			.getLogger(UserPasswordModel.class);

	public void verifyRemindPasswordToken(TValue<String> token) {
		if (getUserPasswordRemind(token.get()) == null) {
			token.error = new UnknownTokenException();
		}
	}

	public void verifyOldPassword(UserSession session, TValue<String> password) {
		User user = get(User.class, session.getUser().getId());
		verifyOldPassword(user, password);
	}

	public void verifyOldPassword(User user, TValue<String> password) {
		if (!user.getPassword().equals(password.get())) {
			password.error = new InvalidPasswordException();
		}
	}

	public void verifyNewPasswordSingle(TValue<String> password) {
		if (password.value.length() < TUserPassword.MIN_PASSWORD_LENGTH) {
			password.error = new TextToShortException();
		}
	}

	public void verifyNewPasswordDual(TDualValue<String> password) {
		if (!password.value.equals(password.second)) {
			password.error = new DualTextFieldInvalidException();
		} else {
			verifyNewPasswordSingle(password);
		}
	}

	public void updatePasswordByUser(UserSession session,
			TValue<String> password) {
		User user = get(User.class, session.getUser().getId());
		updatePasswordByUser(user, password);
	}

	public void updatePasswordByUser(User user, TValue<String> password) {
		user.setPassword(password.get());
		update(user);
	}

	public UserPasswordRemind getPasswordRemindToken(String login) {
		User user = getDAO().getBean(UserLoginModel.class).getUser(login);
		return getPasswordRemindToken(user);
	}

	public UserPasswordRemind getPasswordRemindToken(User user) {
		UserPasswordRemind remind = new UserPasswordRemind();

		remind.setUser(user);
		remind.setDate(new Date());
		remind.setUid(Random.getUid());

		saveOrUpdate(remind);

		return remind;
	}

	public UserPasswordRemind getUserPasswordRemind(String token) {
		return execute(new CrUserPasswordRemind(token));
	}

	public void updatePasswordByToken(TChangePasswordByToken password) {
		UserPasswordRemind remind = getUserPasswordRemind(password.uid.get());
		User user = get(User.class, remind.getUser().getId());
		user.setPassword(password.password.get());
		update(user);
		delete(remind);
	}
}
