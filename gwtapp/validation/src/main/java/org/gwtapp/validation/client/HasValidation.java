package org.gwtapp.validation.client;

import org.gwtapp.validation.rpc.exception.ValidationException;

public interface HasValidation<V extends ValidationException> {
	
	void setValidation(V validation);

	void clearValidation();
}
