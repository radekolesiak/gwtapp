package org.gwtapp.startapp.client.data;

import org.gwtapp.core.client.data.AutoField;
import org.gwtapp.startapp.client.data.autofields.UserRegisterAutoField;
import org.gwtapp.startapp.client.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Tab extends IsSerializable {

	public final static AutoField<Tab, UserRegister> USER_REGISTER = new UserRegisterAutoField();

	UserRegister getUserRegister();

	void setUserRegister(UserRegister userRegister);
}
