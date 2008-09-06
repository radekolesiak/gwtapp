package com.cuprum.web.widgets.user.register.rpc;

import com.cuprum.server.common.model.user.UserLoginModel;
import com.cuprum.web.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;

public class UserRegisterRpc extends RemoteServiceServletSession implements
		IUserRegister {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5230394229602005082L;

	public TUserRegisterValue processUserRegister(
			TUserRegisterValue userRegister) {

		getBean(UserLoginModel.class).register(userRegister);

		return userRegister;
	}
}
