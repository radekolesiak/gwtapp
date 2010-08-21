package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserPassword;

public class PasswordVerifyMetaField extends
		MetaFieldAdapter<UserPassword, String> {

	public PasswordVerifyMetaField() {
		super("password-verify");
	}

	@Override
	public String get(UserPassword model) {
		return model.getPasswordVerify();
	}

	@Override
	public void set(UserPassword model, String value) {
		model.setPasswordVerify(value);
	}
}
