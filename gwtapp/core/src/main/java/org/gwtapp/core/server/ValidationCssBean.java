package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.ValidationException;

public class ValidationCssBean {

	private Class<? extends ValidationException> validator;
	private String[] matchers;

	public void setValidator(Class<? extends ValidationException> validator) {
		this.validator = validator;
	}

	public Class<? extends ValidationException> getValidator() {
		return validator;
	}

	public void setMatchers(String[] matchers) {
		this.matchers = matchers;
	}

	public String[] getMatchers() {
		return matchers;
	}

	public String getCSS() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(getValidator());
		String css = generator.getCssForMatcher(getMatchers());
		return css;
	}
}
