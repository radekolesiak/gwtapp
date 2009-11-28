package org.gwtapp.startapp.client.data.user.register.autofields;

import org.gwtapp.core.client.data.AutoFieldAdapter;
import org.gwtapp.startapp.client.data.user.register.UserRegister;

public class EmailAutoField extends AutoFieldAdapter<UserRegister, String> {

	public EmailAutoField() {
		this("email");
	}

	public EmailAutoField(String name) {
		super(name);
	}

	@Override
	public Object get(UserRegister model) {
		return model.getEmail();
	}

	@Override
	public void set(UserRegister model, Object value) {
		model.setEmail((String) value);
	}

}
