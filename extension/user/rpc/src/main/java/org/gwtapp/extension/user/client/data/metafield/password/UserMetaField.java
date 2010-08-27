package org.gwtapp.extension.user.client.data.metafield.password;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserPassword;

public class UserMetaField extends MetaFieldAdapter<UserPassword, User> {

	public UserMetaField() {
		super("user");
	}

	@Override
	public User get(UserPassword model) {
		return model.getUser();
	}

	@Override
	public void set(UserPassword model, User value) {
		model.setUser(value);
	}
}
