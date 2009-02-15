package com.cuprum.web.common.client.exceptions.usersession;

import com.cuprum.web.common.client.exceptions.RpcException;

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
