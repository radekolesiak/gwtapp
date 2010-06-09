package org.gwtapp.startapp.rpc.data;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.startapp.rpc.data.metafields.UserRegisterMetaField;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Tab extends IsSerializable {

	public final static MetaField<Tab, UserRegister> USER_REGISTER = new UserRegisterMetaField();

	UserRegister getUserRegister();

	void setUserRegister(UserRegister userRegister);
}
