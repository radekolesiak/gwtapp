package com.cuprum.web.widgets.user.register.client.data;

import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.data.TDualStringValue;

public class TUserRegisterMailValue extends TDualStringValue implements
		HasRegExp {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 2717789440898267529L;

	public boolean match(String value) {
		return value != null && value.length() > 0;
	}
}
