package org.gwtapp.startapp.client.data;

import org.gwtapp.core.rpc.data.HashModelData;
import org.gwtapp.startapp.client.data.user.register.UserRegister;

public class TabModelImpl extends HashModelData implements TabModel {

	private UserRegister userRegister = Tab.USER_REGISTER.add(this).def();

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}
}
