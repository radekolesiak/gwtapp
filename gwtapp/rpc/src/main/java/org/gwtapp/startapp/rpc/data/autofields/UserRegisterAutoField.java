package org.gwtapp.startapp.rpc.data.autofields;

import org.gwtapp.core.rpc.data.AutoFieldAdapter;
import org.gwtapp.startapp.rpc.data.Tab;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;

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
