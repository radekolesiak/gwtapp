package com.cuprum.web.widgets.common.client.exception;

import java.io.Serializable;

public abstract class RpcException extends RuntimeException implements Serializable {
	public RpcException() {
		super();
	}

	public RpcException(String msg) {
		super(msg);
	}
}
