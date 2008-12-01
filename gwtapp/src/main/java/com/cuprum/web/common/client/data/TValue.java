package com.cuprum.web.common.client.data;

import java.io.Serializable;

import com.cuprum.web.widgets.common.client.exception.RpcException;
import com.google.gwt.user.client.rpc.IsSerializable;

public class TValue<T extends Serializable> implements IsSerializable {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -4289330029611037159L;

	/** Value for a process call */
	public T value;

	/** The reasons of value process error */
	public RpcException error;

	public TValue() {
	}

	public void evalError() throws RpcException {
		if (error != null) {
			throw error;
		}
	}

	public T get() {
		return value;
	}

	public TValue<T> set(T value) {
		this.value = value;
		return this;
	}

	public TValue<T> set(TValue<T> value) {
		return set(value.value);
	}

	public boolean hasError() {
		return error != null;
	}

	public void clearError() {
		error = null;
	}
}
