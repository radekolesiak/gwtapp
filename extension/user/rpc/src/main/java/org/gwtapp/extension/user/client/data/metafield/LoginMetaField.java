package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.User;

public class LoginMetaField extends MetaFieldAdapter<User, String> {

	public LoginMetaField() {
		super("login");
	}

	@Override
	public String get(User model) {
		return model.getLogin();
	}

	@Override
	public void set(User model, String value) {
		model.setLogin(value);
	}
}
