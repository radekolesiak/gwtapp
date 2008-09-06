package com.cuprum.web.common.rpc;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.stub.IConnectionSession;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ConnectionSessionRpc extends RemoteServiceServlet implements
		IConnectionSession {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -4314081539603481697L;

	public TConnectionSession getNewConnectionSession(String moduleName) {
		TConnectionSession session = new TConnectionSession();
		session.set(moduleName);
		return session;
	}
}
