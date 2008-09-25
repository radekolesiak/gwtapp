package com.cuprum.web.common.rpc;

import com.cuprum.web.common.client.data.TUserSession;

public class RemoteServiceServletUserSession extends
		RemoteServiceServletSession {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -8789952732330066985L;

	/**
	 * Reads user session id from query string.
	 * 
	 * @return User session.
	 */
	public final String getUserSession() {
		String session = null;

		if (getThreadLocalRequest() != null) {
			session = getThreadLocalRequest().getParameter(
					TUserSession.USER_SESSION_REQUEST);
		}

		return session;
	}
}
