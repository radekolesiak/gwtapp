package com.cuprum.web.common.client.data;

import java.io.Serializable;
import java.util.Vector;

import com.cuprum.web.widgets.common.client.exception.MultipleException;

public class TValuesMap extends Vector<TValue> implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -2912317755557759390L;

	@SuppressWarnings("unchecked")
	public void clearErrors() {
		for (TValue value : this) {
			if (value != null) {
				value.error = null;
			}
		}
	}

	public boolean hasErrors() {
		for (TValue value : this) {
			try {
				value.evalError();
			} catch (RuntimeException e) {
				return true;
			}
		}
		return false;
	}

	public TValuesMap() {
	}

	public void evalErrors() throws MultipleException {
		if (hasErrors()) {
			throw new MultipleException();
		}
	}
}
