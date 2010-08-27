package org.gwtapp.extension.user.client.data.metafield.user;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.User;

public class IdMetaField extends MetaFieldAdapter<User, Long> {

	public IdMetaField() {
		super("id");
	}

	@Override
	public Long get(User model) {
		return model.getId();
	}

	@Override
	public void set(User model, Long value) {
		model.setId(value);
	}
}
