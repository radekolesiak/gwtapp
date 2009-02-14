package com.cuprum.web.widgets.user.password.client.data;

import com.cuprum.web.common.client.data.TDualStringValue;
import com.cuprum.web.common.client.data.TStringValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.data.TValueList;

@SuppressWarnings("unchecked")
public class TChangePasswordByToken extends TValueList<TValue> {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -7741736951504063737L;

	public TStringValue uid = new TStringValue();

	public TDualStringValue password = new TUserPassword();

	public TChangePasswordByToken() {
		add(uid);
		add(password);
	}
}
