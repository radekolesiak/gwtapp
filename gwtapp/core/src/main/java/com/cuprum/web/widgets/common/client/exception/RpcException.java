package com.cuprum.web.widgets.common.client.exception;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class RpcException extends RuntimeException implements
		Serializable, IsSerializable {
	public RpcException() {
		super();
	}

	public RpcException(String msg) {
		super(msg);
	}
}
