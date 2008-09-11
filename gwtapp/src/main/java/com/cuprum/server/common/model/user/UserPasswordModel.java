package com.cuprum.server.common.model.user;

import com.cuprum.server.common.model.Model;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.password.client.data.TUserPasswordValue;

public class UserPasswordModel extends Model {
	public void verifyOldPassword(TValue<String> password) {

	}

	public void verifyPasswordSingle(TValue<String> password) {
		if (password.value.length() < TUserPasswordValue.MIN_PASSWORD_LENGTH) {
			password.error = new TextToShortException();
		}
	}

	public void verifyPasswordDual(TDualValue<String> password) {
		if (!password.value.equals(password.second)) {
			password.error = new DualTextFieldInvalidException();
		} else {
			verifyPasswordSingle(password);
		}
	}
}
