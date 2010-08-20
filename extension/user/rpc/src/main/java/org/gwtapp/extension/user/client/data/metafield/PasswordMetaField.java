package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserPassword;

public class PasswordMetaField extends MetaFieldAdapter<UserPassword, String> {

	public PasswordMetaField() {
		super("password");
	}

	@Override
	public String get(UserPassword model) {
		return model.getPassword();
	}

	@Override
	public void set(UserPassword model, String value) {
		model.setPassword(value);
	}
}
