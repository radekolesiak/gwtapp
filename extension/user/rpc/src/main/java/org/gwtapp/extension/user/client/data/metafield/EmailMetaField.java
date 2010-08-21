package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserImpl;

public class EmailMetaField extends MetaFieldAdapter<UserImpl, String> {

	public EmailMetaField() {
		super("email");
	}

	@Override
	public String get(UserImpl model) {
		return model.getEmail();
	}

	@Override
	public void set(UserImpl model, String value) {
		model.setEmail(value);
	}
}
