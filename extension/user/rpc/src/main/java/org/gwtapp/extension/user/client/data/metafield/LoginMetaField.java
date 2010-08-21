package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserImpl;

public class LoginMetaField extends MetaFieldAdapter<UserImpl, String> {

	public LoginMetaField() {
		super("login");
	}

	@Override
	public String get(UserImpl model) {
		return model.getLogin();
	}

	@Override
	public void set(UserImpl model, String value) {
		model.setLogin(value);
	}
}
