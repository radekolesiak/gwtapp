package org.gwtapp.startapp.rpc.data;

import org.gwtapp.core.rpc.data.AutoField;
import org.gwtapp.startapp.rpc.data.autofields.UserRegisterAutoField;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Tab extends IsSerializable {

	public final static AutoField<Tab, UserRegister> USER_REGISTER = new UserRegisterAutoField();

	UserRegister getUserRegister();

	void setUserRegister(UserRegister userRegister);
}