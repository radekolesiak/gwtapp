package org.gwtapp.startapp.rpc.data.metafields;

import org.gwtapp.core.shared.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.Tab;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;

public class UserRegisterMetaField extends MetaFieldAdapter<Tab, UserRegister> {

	public UserRegisterMetaField() {
		this("userRegister");
	}

	public UserRegisterMetaField(String name) {
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
