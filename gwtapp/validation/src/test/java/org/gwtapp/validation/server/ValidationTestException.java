package org.gwtapp.validation.server;

import org.gwtapp.validation.rpc.exception.Validation;
import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class ValidationTestException extends ValidationException {

	@ValidationField("login")
	public static enum Login implements IsSerializable {
		VALID, INVALID, TOO_SHORT, NOT_LETTERS_ONLY, NOT_LOWER_CASE, ALREADY_EXISTS
	}

	@ValidationField("email")
	public static enum Email implements IsSerializable {
		VALID, INVALID, ALREADY_EXISTS
	}

	@ValidationField("subvalidation")
	private SubValidationTestException subValidationTestException;

	public void setSubValidationTestException(
			SubValidationTestException subValidationTestException) {
		this.subValidationTestException = subValidationTestException;
	}

	public SubValidationTestException getSubValidationTestException() {
		return subValidationTestException;
	}
}
