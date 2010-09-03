package org.gwtapp.core.client.ui;

import org.gwtapp.core.rpc.exception.ValidationException;

public interface HasValidation<V extends ValidationException> {
	
	void setValidation(V validation);

	void clearValidation();
}
