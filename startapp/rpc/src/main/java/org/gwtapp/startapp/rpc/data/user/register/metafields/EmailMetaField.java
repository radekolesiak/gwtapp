package org.gwtapp.startapp.rpc.data.user.register.metafields;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class EmailMetaField extends MetaFieldAdapter<UserRegister, String> {

	public EmailMetaField() {
		this("email");
	}

	public EmailMetaField(String name) {
		super(name);
	}

	@Override
	public String get(UserRegister model) {
		return model.getEmail();
	}

	@Override
	public void set(UserRegister model, String value) {
		model.setEmail(value);
	}

}
