package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.User;

public class NameMetaField extends MetaFieldAdapter<User, String> {

	public NameMetaField() {
		super("name");
	}

	@Override
	public String get(User model) {
		return model.getName();
	}

	@Override
	public void set(User model, String value) {
		model.setName(value);
	}
}
