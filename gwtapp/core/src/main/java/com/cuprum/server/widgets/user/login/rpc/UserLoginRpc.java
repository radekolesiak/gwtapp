package com.cuprum.server.widgets.user.login.rpc;

import org.apache.log4j.Logger;

import com.cuprum.server.common.model.user.IUserLoginModel;
import com.cuprum.server.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.widgets.common.client.exception.RpcException;
import com.cuprum.web.widgets.user.login.client.stub.IUserLogin;

/** {@inheritDoc} */
public class UserLoginRpc extends RemoteServiceServletSession
		implements IUserLogin {

	/** Logger for this class. */
	static final Logger LOGGER = Logger.getLogger(UserLoginRpc.class);

	/** UID. */
	private static final long serialVersionUID = 1215885952887020928L;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws InvalidPasswordException
	 * @throws UserNotFoundException
	 */
	@SuppressWarnings("finally")
	public final TUserSession login(final String login, final String password) {
		TUserSession session = new TUserSession();
		try {
			IUserLoginModel userLogin = getBean(IUserLoginModel.class);
			session.set(userLogin.login(login, password).getSession());
		} catch (RpcException e) {
			session.error = e;
		} finally {
			return session;
		}
	}

	/** {@inheritDoc} */
	public void logout(TUserSession session) {
		if (session != null && session.get() != null) {
			IUserLoginModel userLogin = getBean(IUserLoginModel.class);
			userLogin.logout(session.get());
		}
	}
}
