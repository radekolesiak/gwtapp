package com.cuprum.web.widgets.user.register.client.data;

import java.io.Serializable;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.data.TValueList;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;

@SuppressWarnings("unchecked")
public class TUserRegisterValue extends TValueList<TValue> implements Serializable {

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
