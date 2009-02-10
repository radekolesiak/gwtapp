package com.cuprum.web.common.client.exceptions.model.usersession;

import com.cuprum.web.widgets.common.client.exception.RpcException;

public class SessionNotFoundException extends RpcException {
	public SessionNotFoundException() {
		super();
	}
	
	public SessionNotFoundException(String msg) {
		super(msg);
	}
	
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 3824253638637535884L;
}
