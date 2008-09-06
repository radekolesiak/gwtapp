package com.cuprum.web.common.client.data;

import java.io.Serializable;

public class TDualValue<T extends Serializable> extends TValue<T> {
	
	/** Value for a process call */
	public T second;
	
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 730710367137262073L;

	public TDualValue<T> set(T value, T second) {
		this.second = second;
		set(value);
		return this;
	}

	public TDualValue<T> set(TDualValue<T> values) {
		return set(values.value, values.second);
	}
}
