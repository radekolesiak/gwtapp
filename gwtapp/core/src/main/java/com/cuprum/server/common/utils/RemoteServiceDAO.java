package com.cuprum.server.common.utils;

import com.google.gwt.user.client.rpc.RemoteService;

public class RemoteServiceDAO extends AbstractDAO<RemoteService> {
	
	protected String getClassPath() {
		return "/com/cuprum/server/config/rpc/bean-rpc-" + getName()
				+ ".cfg.xml";
	}
}
