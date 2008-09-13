package com.cuprum.web.widgets.user.register.client.data;

import java.io.Serializable;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.data.TValuesMap;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;

public class TUserRegisterValue extends TValuesMap implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -2947890396745121191L;

	public TStringValue login = new TUserRegisterLoginValue();

	public TDualStringValue password = new TUserPassword();

	public TDualStringValue mail = new TUserRegisterMailValue();

	public TStringValue uid = new TStringValue();

	public TStringValue getValueLogin() {
		return login;
	}

	public TDualStringValue getValuePassword() {
		return password;
	}

	public TDualStringValue getValueMail() {
		return mail;
	}

	public TStringValue getValueUid() {
		return uid;
	}

	public TUserRegisterValue() {
		add(login);
		add(password);
		add(mail);
	}
}
