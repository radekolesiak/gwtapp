package com.cuprum.web.widgets.user.register.rpc;

import com.cuprum.server.common.model.user.UserLoginModel;
import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.model.user.UserAlreadyExistsException;
import com.cuprum.web.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;

public class UserRegisterRpc extends RemoteServiceServletSession implements
		IUserRegister {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5230394229602005082L;

	public TUserRegisterValue processUserRegister(
			TUserRegisterValue userRegister) {

		userRegister.clearErrors();

		TStringValue login = userRegister.getValueLogin();

		if (login instanceof HasRegExp
				&& !((HasRegExp) login).match(login.value)) {
			login.error = new RegExpException();
		} else {
			if (login.value.length() < TUserRegisterValue.MIN_LOGIN_LENGTH) {
				login.error = new TextToShortException();
			} else {
				UserLoginModel userLogin = getBean(UserLoginModel.class);
				if (userLogin.exists(login.value)) {
					login.error = new UserAlreadyExistsException();
				}
			}
		}

		TDualStringValue password = userRegister.getValuePassword();

		if (!password.value.equals(password.second)) {
			password.error = new DualTextFieldInvalidException();
		} else if (password.value.length() < TUserRegisterValue.MIN_PASSWORD_LENGTH) {
			password.error = new TextToShortException();
		}

		TDualStringValue mail = userRegister.getValueMail();

		if (!mail.value.equals(password.second)) {
			mail.error = new DualTextFieldInvalidException();
		} else if (mail instanceof HasRegExp
				&& !((HasRegExp) mail).match(mail.value)) {
			mail.error = new RegExpException();
		}

		return userRegister;
	}
}
