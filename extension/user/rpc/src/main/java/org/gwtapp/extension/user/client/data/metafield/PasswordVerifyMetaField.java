package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;

public class PasswordVerifyMetaField extends
		MetaFieldAdapter<UserPasswordImpl, String> {

	public PasswordVerifyMetaField() {
		super("password-verify");
	}

	@Override
	public String get(UserPasswordImpl model) {
		return model.getPasswordVerify();
	}

	@Override
	public void set(UserPasswordImpl model, String value) {
		model.setPasswordVerify(value);
	}
}
