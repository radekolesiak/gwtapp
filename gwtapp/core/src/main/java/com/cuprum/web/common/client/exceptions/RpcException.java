package com.cuprum.web.common.client.exceptions;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public abstract class RpcException extends RuntimeException implements
		Serializable, IsSerializable {
	public RpcException() {
		super();
	}

	public RpcException(String msg) {
		super(msg);
	}
}
