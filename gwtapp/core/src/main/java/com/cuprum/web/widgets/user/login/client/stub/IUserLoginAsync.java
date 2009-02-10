package com.cuprum.web.widgets.user.login.client.stub;

import com.cuprum.web.common.client.data.TUserSession;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchronous for UserAuthentication.
 * 
 * @author Radek Olesiak
 * 
 */
public interface IUserLoginAsync {
	/**
	 * @param login
	 *            Login of user.
	 * @param password
	 *            Password of user.
	 * @param callback
	 *            Callback.
	 */
	void login(String login, String password,
			AsyncCallback<TUserSession> callback);

	// UserDetails getCurrentUser(AsyncCallback<Object> callback);
	/**
	 * @param callback
	 *            Callback.
	 */
	void logout(TUserSession session, AsyncCallback<Object> callback);
}
