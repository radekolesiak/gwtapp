package org.gwtapp.startapp.rpc.data.user.register.metafields;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class PasswordAutoField extends MetaFieldAdapter<UserRegister, String> {

	public PasswordAutoField() {
		this("password");
	}

	public PasswordAutoField(String name) {
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
