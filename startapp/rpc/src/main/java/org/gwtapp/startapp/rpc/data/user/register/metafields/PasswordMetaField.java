package org.gwtapp.startapp.rpc.data.user.register.metafields;

import org.gwtapp.core.shared.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class PasswordMetaField extends MetaFieldAdapter<UserRegister, String> {

	public PasswordMetaField() {
		this("password");
	}

	public PasswordMetaField(String name) {
		super(name);
	}

	@Override
	public Object get(UserRegister model) {
		return model.getPassword();
	}

	@Override
	public void set(UserRegister model, Object value) {
		model.setPassword((String) value);
	}

}
