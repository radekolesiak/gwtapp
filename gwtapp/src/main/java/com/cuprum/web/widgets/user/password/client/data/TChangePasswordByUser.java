package com.cuprum.web.widgets.user.password.client.data;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.data.TValuesMap;

public class TChangePasswordByUser extends TValuesMap {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 3923773289799089584L;

	public TStringValue oldPassword = new TStringValue();

	public TDualStringValue newPassword = new TUserPassword();

	public TChangePasswordByUser() {
		add(oldPassword);
		add(newPassword);
	}
}
