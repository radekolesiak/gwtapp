package com.cuprum.web.common.client.data;

import com.cuprum.web.common.client.exceptions.MultipleValuesException;

public interface IValueCollection {
	
	void clearErrors();

	boolean hasErrors();

	void evalErrors() throws MultipleValuesException;
}
