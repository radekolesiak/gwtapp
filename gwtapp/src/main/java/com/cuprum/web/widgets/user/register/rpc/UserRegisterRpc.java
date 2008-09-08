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

		System.out.println("1: " + getRequest().getContextPath());
		System.out.println("2: " + getRequest().getRemoteAddr());
		System.out.println("3: " + getRequest().getRemoteHost());
		System.out.println("4: " + getRequest().getLocalAddr());
		System.out.println("5: " + getRequest().getLocalName());
		System.out.println("6: " + getRequest().getSession().getId());

		//Mail mail = new Mail();
		//mail.setRecipientTo(user.getMail());
		//mail.setContent("http://)
		//LOGGER.debug("new user with ID: " + user.getId());

		getBean(UserLoginModel.class).register(userRegister);

		return userRegister;
	}
}
