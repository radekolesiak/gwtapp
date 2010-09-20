package org.gwtapp.extension.user.client.data.exception;

import org.gwtapp.validation.rpc.exception.Validation;
import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class ReCaptchaUserValidationException extends ValidationException {

	public final static String RECAPTCHA = "recaptcha";
	public final static String PASSWORD = "password";

	@ValidationField(RECAPTCHA)
	public static enum ReCaptcha implements IsSerializable {
		INVALID
	}

	@ValidationField(PASSWORD)
	public static enum Password implements IsSerializable {
		EMPTY, NOT_EQUALS, NOT_BETWEEN_6_20_CHARS
	}

	private UserValidationException userValidationException;

	public void addReCaptcha(ReCaptcha reCaptcha) {
		add(RECAPTCHA, reCaptcha);
	}

	public void addPassword(Password password) {
		add(PASSWORD, password);
	}

	public void setUserValidationException(
			UserValidationException userValidationException) {
		this.userValidationException = userValidationException;
	}

	public UserValidationException getUserValidationException() {
		return userValidationException;
	}
}
