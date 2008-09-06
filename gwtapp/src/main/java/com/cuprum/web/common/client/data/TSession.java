package com.cuprum.web.common.client.data;

import java.io.Serializable;

import com.cuprum.web.common.client.Equals;

public abstract class TSession extends TValue<String> implements Serializable {
	/** Constructor for IsSerializable. */
	public TSession() {
	}

	/**
	 * 
	 * @param id
	 *            Session id.
	 */
	public TSession(final String value) {
		this.value = value;
	}

	/** @return Returns hash code. */
	public final int hashCode() {
		if (value != null) {
			return value.hashCode();
		} else {
			return 0;
		}
	}

	/**
	 * Compares with second object.
	 * 
	 * @param e
	 *            Object to compare.
	 * @return Result of comparision
	 */
	public final boolean equals(final Object e) {
		if (!(e instanceof TSession)) {
			return false;
		} else {
			return equals((TSession) e);
		}
	}

	/**
	 * Compares with second object.
	 * 
	 * @param e
	 *            Object to compare.
	 * @return Result of comparision
	 */
	public final boolean equals(final TSession e) {
		return Equals.isEquals(this.value, e.value);
	}

}
