package com.cuprum.web.widgets.user.login.rpc;

import org.apache.log4j.Logger;

import com.cuprum.server.common.model.user.UserLoginModel;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.widgets.common.client.exception.RpcException;
import com.cuprum.web.widgets.user.login.client.stub.IUserAuthentication;

/** {@inheritDoc} */
public class UserAuthenticationRpc extends RemoteServiceServletSession
		implements IUserAuthentication {

	/** Logger for this class. */
	static final Logger LOGGER = Logger.getLogger(UserAuthenticationRpc.class);

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
			UserLoginModel userLogin = getBean(UserLoginModel.class);
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
			UserLoginModel userLogin = getBean(UserLoginModel.class);
			userLogin.logout(session.get());
		}
	}
}
