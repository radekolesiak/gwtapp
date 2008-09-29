package com.cuprum.web.common.client.data;

import java.io.Serializable;

import com.cuprum.web.common.client.Constants;
import com.google.gwt.user.client.rpc.IsSerializable;

public class TConnectionSession extends TSession implements Serializable, IsSerializable {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5862055787682898534L;

	/** Used on client side application. */
	private static TConnectionSession session = null;

	/**
	 * Sets default session.
	 * 
	 * @param session
	 *            Default session.
	 */
	public static final void setSession(final TConnectionSession session) {
		TConnectionSession.session = session;
	}

	/** @return Returns current default session. */
	public static final TConnectionSession getSession() {
		return TConnectionSession.session;
	}

	/** Session request value for url query string. */
	public static final String CONNECTION_SESSION_REQUEST = Constants.CONNECTION_SESSION_REQUEST;
}
