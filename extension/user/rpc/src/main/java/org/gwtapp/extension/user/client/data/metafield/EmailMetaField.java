package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.User;

public class EmailMetaField extends MetaFieldAdapter<User, String> {

	public EmailMetaField() {
		super("email");
	}

	@Override
	public String get(User model) {
		return model.getEmail();
	}

	@Override
	public void set(User model, String value) {
		model.setEmail(value);
	}
}
