package org.gwtapp.startapp.client.data.user.register.autofields;

import org.gwtapp.core.rpc.data.AutoFieldAdapter;
import org.gwtapp.startapp.client.data.user.register.UserRegister;

public class PasswordAutoField extends AutoFieldAdapter<UserRegister, String> {

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
