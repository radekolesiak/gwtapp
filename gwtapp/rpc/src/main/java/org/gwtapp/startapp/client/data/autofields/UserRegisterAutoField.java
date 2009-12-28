package org.gwtapp.startapp.client.data.autofields;

import org.gwtapp.core.client.data.AutoFieldAdapter;
import org.gwtapp.startapp.client.data.Tab;
import org.gwtapp.startapp.client.data.user.register.UserRegister;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModelImpl;

public class UserRegisterAutoField extends AutoFieldAdapter<Tab, UserRegister> {

	public UserRegisterAutoField() {
		this("userRegister");
	}

	public UserRegisterAutoField(String name) {
		super(name);
	}

	@Override
	public Object get(Tab model) {
		return model.getUserRegister();
	}

	@Override
	public void set(Tab model, Object value) {
		model.setUserRegister(((UserRegister) value));
	}

	@Override
	public UserRegister def() {
		return new UserRegisterModelImpl();
	}
}
