package com.cuprum.server.common.utils;

import com.google.gwt.user.client.rpc.RemoteService;

public class RemoteServiceDAOMap extends AbstractDAOMap<RemoteService> {
	public final static RemoteServiceDAOMap DAO = new RemoteServiceDAOMap();

	@Override
	protected RemoteServiceDAO createDAO() {
		return new RemoteServiceDAO();
	}
}
