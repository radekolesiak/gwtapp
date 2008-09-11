package com.cuprum.server.common.model.user;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.password.client.data.TUserPasswordValue;

public class UserPasswordModel extends Model {
	private final static Logger LOGGER = Logger
			.getLogger(UserPasswordModel.class);

	public void verifyOldPassword(UserSession session, TValue<String> password) {
		User user = get(User.class, session.getUser().getId());
		if (!user.getPassword().equals(password.get())) {
			password.error = new InvalidPasswordException();
		}
	}

	public void verifyNewPasswordSingle(TValue<String> password) {
		if (password.value.length() < TUserPasswordValue.MIN_PASSWORD_LENGTH) {
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
}
