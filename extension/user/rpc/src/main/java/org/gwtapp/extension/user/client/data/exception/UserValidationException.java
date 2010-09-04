package org.gwtapp.extension.user.client.data.exception;

import java.util.List;

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

	private Login login;
	private Email email;

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
		fields.add(new Field(LOGIN, getLogin()));
		fields.add(new Field(EMAIL, getEmail()));
		return fields;
	}
}
