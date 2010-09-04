package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.ValidationException;

public class ValidationCssGenerator extends ValidationCssGeneratorBase
		implements ValidationCss {

	private Class<? extends ValidationException> validationClass;
	private String prefix = "";
	private String separator = "";
	private String style = "";

	public ValidationCssGenerator() {
	}

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
		StringBuilder s = new StringBuilder();
		for (Class<?> c : getAnnotatedSubclasses()) {
			getCssForEnum(s, c);
		}
		return s.toString();
	}

	public void getCssForEnum(StringBuilder s, Class<?> c) {
		String fieldName = getValidationFieldValue(c);
		for (Enum<?> field : getEnumConstants(c)) {
			getCssForEnumConstant(s, fieldName, field);
		}
	}

	public void getCssForEnumConstant(StringBuilder s, String fieldName,
			Enum<?> field) {
		String enumFieldName = getEnumFieldName(fieldName, field);
		appendStyleClass(s, getPrefix(), getSeparator(), enumFieldName);
		appendStyle(s, getStyle());
	}

	private void appendStyleClass(StringBuilder s, String prefix,
			String separator, String enumFieldName) {
		s.append(prefix);
		s.append(" ");
		s.append(".validation-");
		s.append(enumFieldName);
		s.append(" ");
		s.append(separator);
		s.append(enumFieldName);
	}

	private void appendStyle(StringBuilder s, String style) {
		s.append(" { ");
		s.append(style);
		s.append(" }\n");
	}

	private String getEnumFieldName(String fieldName, Enum<?> field) {
		return fieldName + "-" + getEnumConstantName(field);
	}

	private String getEnumConstantName(Enum<?> field) {
		return field.name().replaceAll("_", "-").toLowerCase();
	}
}
