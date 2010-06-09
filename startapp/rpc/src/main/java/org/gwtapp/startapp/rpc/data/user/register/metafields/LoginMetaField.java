package org.gwtapp.startapp.rpc.data.user.register.metafields;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class LoginMetaField extends MetaFieldAdapter<UserRegister, String> {

	public LoginMetaField() {
		this("login");
	}

	public LoginMetaField(String name) {
		super(name);
	}

	@Override
	public Object get(UserRegister model) {
		return model.getLogin();
	}

	@Override
	public void set(UserRegister model, Object value) {
		model.setLogin((String) value);
	}

}
