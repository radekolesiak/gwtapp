package org.gwtapp.extension.user.client.data.metafield.password;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserPassword;

public class IdMetaField extends MetaFieldAdapter<UserPassword, Long> {

	public IdMetaField() {
		super("id");
	}

	@Override
	public Long get(UserPassword model) {
		return model.getId();
	}

	@Override
	public void set(UserPassword model, Long value) {
		model.setId(value);
	}
}
