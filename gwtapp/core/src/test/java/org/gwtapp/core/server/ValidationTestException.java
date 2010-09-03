package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.Validation;
import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class ValidationTestException extends ValidationException {
	
	@ValidationField
	public static enum Login implements IsSerializable {
		VALID, INVALID, TOO_SHORT, NOT_LETTERS_ONLY, NOT_LOWER_CASE, ALREADY_EXISTS
	}
}
