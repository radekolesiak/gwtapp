package com.cuprum.web.widgets.user.login.client.stub;

import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Authentication methods.
 * 
 * @author Radek Olesiak
 * 
 */
public interface IUserAuthentication extends RemoteService {
	/**
	 * @param login
	 *            Login of user.
	 * @param password
	 *            Password of user.
	 * @return Returns session with information about its creating status.
	 */
	TUserSession login(String login, String password);

	// UserDetails getCurrentUser();
	/** Logouts of user and close session associated with its. */
	void logout(TUserSession session) throws SessionNotFoundException;
}
