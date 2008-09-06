package com.cuprum.web.common.client.data;

import com.cuprum.web.common.client.Constants;

/**
 * Session information container.
 * 
 * @author Radek Olesiak
 * 
 */
public class TUserSession extends TSession {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -4460400281605775601L;

	/** Used on client side application. */
	private static TUserSession session = null;

	/**
	 * Sets default session.
	 * 
	 * @param session
	 *            Default session.
	 */
	public static final void setSession(final TUserSession session) {
		TUserSession.session = session;
	}

	/** @return Returns current default session. */
	public static final TUserSession getSession() {
		return TUserSession.session;
	}

	/** Session request value for url query string. */
	public static final String USER_SESSION_REQUEST = Constants.USER_SESSION_REQUEST;
}
