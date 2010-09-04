package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.ValidationException;

public class ValidationCssBean implements ValidationCss {

	private Class<? extends ValidationException> validationClass;
	private String prefix = "";
	private String separator = "";
	private String style = "";

	@Override
	public void setValidationClass(
			Class<? extends ValidationException> validationClass) {
		this.validationClass = validationClass;
	}

	@Override
	public Class<? extends ValidationException> getValidationClass() {
		return validationClass;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public String getSeparator() {
		return separator;
	}

	@Override
	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String getStyle() {
		return style;
	}

	@Override
	public String getCSS() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(getValidationClass());
		generator.setPrefix(getPrefix());
		generator.setSeparator(getSeparator());
		generator.setStyle(getStyle());
		return generator.getCSS();
	}
}
