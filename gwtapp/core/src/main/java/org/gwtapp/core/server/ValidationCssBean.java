package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.ValidationException;

public class ValidationCssBean {

	private Class<? extends ValidationException> validator;

	public void setValidator(Class<? extends ValidationException> validator) {
		this.validator = validator;
	}

	public Class<? extends ValidationException> getValidator() {
		return validator;
	}

	public String getCSS() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(getValidator());
		return generator.getCss();
	}
}
