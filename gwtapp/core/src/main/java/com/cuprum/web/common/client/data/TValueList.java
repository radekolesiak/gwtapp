package com.cuprum.web.common.client.data;

import java.util.ArrayList;

import com.cuprum.web.common.client.exceptions.MultipleValuesException;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("unchecked")
public class TValueList<T extends TValue> extends ArrayList<T> implements IValueCollection, IsSerializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -2912317755557759390L;

	public TValueList() {
	}

	public void clearErrors() {
		for (T value : this) {
			value.clearError();
		}
	}

	public boolean hasErrors() {
		for (T value : this) {
			if (value.hasError()) {
				return true;
			}
		}
		return false;
	}

	public void evalErrors() throws MultipleValuesException {
		if (hasErrors()) {
			throw new MultipleValuesException();
		}
	}
}
