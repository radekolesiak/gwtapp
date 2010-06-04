package org.gwtapp.startapp.rpc.data;

import org.gwtapp.core.shared.data.HashModelData;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class TabModelImpl extends HashModelData implements TabModel {

	private UserRegister userRegister = Tab.USER_REGISTER.add(this).def();

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}
}
