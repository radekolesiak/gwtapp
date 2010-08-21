package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserImpl;

public class IdMetaField extends MetaFieldAdapter<UserImpl, Long> {

	public IdMetaField() {
		super("id");
	}

	@Override
	public Long get(UserImpl model) {
		return model.getId();
	}

	@Override
	public void set(UserImpl model, Long value) {
		model.setId(value);
	}
}
