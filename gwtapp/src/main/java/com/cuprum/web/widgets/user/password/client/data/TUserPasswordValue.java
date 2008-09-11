package com.cuprum.web.widgets.user.password.client.data;

import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.data.TDualStringValue;

public class TUserPasswordValue extends TDualStringValue implements HasRegExp {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 2717987440898267529L;

	public final static int MIN_PASSWORD_LENGTH = 3;

	public final static int MAX_PASSWORD_LENGTH = 16;

	public boolean match(String value) {
		return value != null && value.length() > 0;
	}
}
