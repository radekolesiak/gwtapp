package com.cuprum.web.common.client.data;

import com.cuprum.web.widgets.common.client.exception.MultipleException;

public interface IValueCollection {
	void clearErrors();

	boolean hasErrors();

	void evalErrors() throws MultipleException;
}
