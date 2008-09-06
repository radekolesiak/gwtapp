package com.cuprum.web.widgets.user.register.client.data;

import java.io.Serializable;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.data.TValuesMap;

public class TUserRegisterValue extends TValuesMap implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -2947890396745121191L;

	public final static int MIN_LOGIN_LENGTH = 5;

	public final static int MAX_LOGIN_LENGTH = 16;

	public final static int MIN_PASSWORD_LENGTH = 3;

	public final static int MAX_PASSWORD_LENGTH = 16;

	public TStringValue login = new TUserRegisterLoginValue();

	public TDualStringValue password = new TUserRegisterPasswordValue();

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
