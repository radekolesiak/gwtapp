package org.gwtapp.validation.server;

import org.gwtapp.validation.rpc.exception.Validation;
import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class SubValidationTestException extends ValidationException {

	@ValidationField("password")
	public static enum Password implements IsSerializable {
		INVALID
	}
}
