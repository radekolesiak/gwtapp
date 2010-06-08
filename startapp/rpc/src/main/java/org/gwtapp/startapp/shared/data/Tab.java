package org.gwtapp.startapp.shared.data;

import org.gwtapp.core.shared.data.MetaField;
import org.gwtapp.startapp.shared.data.metafields.UserRegisterMetaField;
import org.gwtapp.startapp.shared.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Tab extends IsSerializable {

	public final static MetaField<Tab, UserRegister> USER_REGISTER = new UserRegisterMetaField();

	UserRegister getUserRegister();

	void setUserRegister(UserRegister userRegister);
}
