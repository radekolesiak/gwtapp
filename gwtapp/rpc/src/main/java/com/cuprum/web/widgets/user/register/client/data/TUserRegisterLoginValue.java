package com.cuprum.web.widgets.user.register.client.data;

import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.data.TStringValue;

public class TUserRegisterLoginValue extends TStringValue implements HasRegExp {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 2717987440898267529L;

	public final static int MIN_LOGIN_LENGTH = 5;

	public final static int MAX_LOGIN_LENGTH = 16;

	public boolean match(String value) {
		return value != null && value.length() > 0;
	}
}