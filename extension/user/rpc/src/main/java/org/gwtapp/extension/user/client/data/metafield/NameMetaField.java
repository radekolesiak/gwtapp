package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserImpl;

public class NameMetaField extends MetaFieldAdapter<UserImpl, String> {

	public NameMetaField() {
		super("name");
	}

	@Override
	public String get(UserImpl model) {
		return model.getName();
	}

	@Override
	public void set(UserImpl model, String value) {
		model.setName(value);
	}
}
