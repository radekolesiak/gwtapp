package org.gwtapp.extension.user.client.data.exception;

import org.gwtapp.core.rpc.exception.Validation;
import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class UserValidationException extends ValidationException {

	public final static String EMAIL_REGEXP = ".+@.+\\.[a-z]+";
	public final static String ANY_UPPER_CASE_REGEXP = "[A-Z]+";
	public final static String ONLY_LETTERS_REGEXP = "[a-z]+";

	public final static String LOGIN = "login";
	public final static String EMAIL = "email";

	@ValidationField(LOGIN)
	public static enum Login implements IsSerializable {
		INVALID, TOO_SHORT, NOT_LETTERS_ONLY, NOT_LOWER_CASE, ALREADY_EXISTS
	}

	@ValidationField(EMAIL)
	public static enum Email implements IsSerializable {
		INVALID, ALREADY_EXISTS
	}

	public void setLogin(Login login) {
		set(LOGIN, login);
	}

	public void setEmail(Email email) {
		set(EMAIL, email);
	}
}
