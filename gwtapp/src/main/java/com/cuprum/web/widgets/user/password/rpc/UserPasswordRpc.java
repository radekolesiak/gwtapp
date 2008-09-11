package com.cuprum.web.widgets.user.password.rpc;

import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;

public class UserPasswordRpc extends RemoteServiceServletSession implements
		IUserPassword {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 4954867121395247984L;

	public TChangePasswordByUser processChangePasswordByUser(
			TChangePasswordByUser passwords) throws SessionNotFoundException {
		return null;
	}
}
