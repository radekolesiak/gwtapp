package com.cuprum.server.common.rpc;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.stub.IConnectionSession;

public class ConnectionSessionRpc extends RemoteServiceServletSession implements
		IConnectionSession {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -4314081539603481697L;

	public TConnectionSession getNewConnectionSession() {
		TConnectionSession session = new TConnectionSession();
		// e.g. set unique connection session id per IP and browser
		session.set(getModuleName());
		return session;
	}
}
