package org.gwtapp.ccalc.client.data.user.exception;

import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class UserValidationException extends RpcException {

	public final static String EMAIL_REGEXP = ".+@.+\\.[a-z]+";
	public final static String ANY_UPPER_CASE_REGEXP = "[A-Z]+";
	public final static String ONLY_LETTERS_REGEXP = "[a-z]+";

	public static enum Login implements IsSerializable {
		VALID, INVALID, TOO_SHORT, NOT_LETTERS_ONLY, NOT_LOWER_CASE, ALREADY_EXISTS
	}

	public static enum Email implements IsSerializable {
		VALID, INVALID, ALREADY_EXISTS
	}

	private Login login = Login.VALID;
	private Email email = Email.VALID;

	public void setLogin(Login login) {
		this.login = login;
	}

	public Login getLogin() {
		return login;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Email getEmail() {
		return email;
	}
}
