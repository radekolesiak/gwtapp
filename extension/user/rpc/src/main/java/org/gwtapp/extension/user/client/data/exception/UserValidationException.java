package org.gwtapp.extension.user.client.data.exception;

import java.util.List;

import org.gwtapp.core.rpc.exception.ValidationException;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class UserValidationException extends ValidationException {

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

	@Override
	public List<Field> getFields() {
		List<Field> fields = super.getFields();
		fields.add(new Field("login", getLogin()));
		fields.add(new Field("email", getEmail()));
		return fields;
	}
}
