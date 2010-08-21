package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;

public class PasswordMetaField extends MetaFieldAdapter<UserPasswordImpl, String> {

	public PasswordMetaField() {
		super("password");
	}

	@Override
	public String get(UserPasswordImpl model) {
		return model.getPassword();
	}

	@Override
	public void set(UserPasswordImpl model, String value) {
		model.setPassword(value);
	}
}
