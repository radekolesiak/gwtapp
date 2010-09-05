package org.gwtapp.extension.user.client.data.exception;

import org.gwtapp.core.rpc.exception.Validation;
import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class UserValidationException extends ValidationException {

	public final static String EMAIL_REGEXP = ".+@.+\\.[a-z]+";
	public final static String ANY_UPPER_CASE_REGEXP = "[^A-Z]*[A-Z][^A-Z]*";
	public final static String ONLY_LETTERS_REGEXP = "[a-zA-Z]+";

	public final static String LOGIN = "login";
	public final static String EMAIL = "email";

	@ValidationField(LOGIN)
	public static enum Login implements IsSerializable {
		EMPTY, TOO_SHORT, NOT_LETTERS_ONLY, NOT_LOWER_CASE, ALREADY_EXISTS
	}

	@ValidationField(EMAIL)
	public static enum Email implements IsSerializable {
		EMPTY, INVALID, ALREADY_EXISTS
	}

	public void addLogin(Login login) {
		add(LOGIN, login);
	}

	public void addEmail(Email email) {
		add(EMAIL, email);
	}
}
