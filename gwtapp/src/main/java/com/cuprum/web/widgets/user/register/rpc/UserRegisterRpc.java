package com.cuprum.web.widgets.user.register.rpc;

import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.user.UserLoginModel;
import com.cuprum.server.common.utils.Mail;
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

		UserConfirm confirm = getBean(UserLoginModel.class).register(
				userRegister);

		if (!userRegister.hasErrors() && confirm != null) {
			Mail mail = new Mail();
			mail.setRecipientTo(userRegister.mail.value);
			mail.setContent("confirm=" + confirm.getUid());
			mail.start();
		}

		return userRegister;
	}
}
