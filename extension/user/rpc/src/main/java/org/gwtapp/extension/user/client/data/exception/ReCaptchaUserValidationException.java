package org.gwtapp.extension.user.client.data.exception;

import org.gwtapp.validation.rpc.exception.Validation;
import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
@Validation
public class ReCaptchaUserValidationException extends ValidationException {

	public final static String RECAPTCHA = "recaptcha";

	@ValidationField(RECAPTCHA)
	public static enum ReCaptcha implements IsSerializable {
		INVALID
	}

	public ReCaptchaUserValidationException() {
		addReCaptcha(ReCaptcha.INVALID);
	}

	public void addReCaptcha(ReCaptcha reCaptcha) {
		add(RECAPTCHA, reCaptcha);
	}
}
