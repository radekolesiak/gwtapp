package org.gwtapp.core.server;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

public class ValidationCssGenerator implements ValidationCss {

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

	public List<Class<?>> getAnnotatedSubclasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		Class<?>[] classes = getValidationClass().getClasses();
		for (Class<?> c : classes) {
			if (c.isEnum() && c.getAnnotation(ValidationField.class) != null) {
				list.add(c);
			}
		}
		return list;
	}

	public List<Enum<?>> getEnumConstants(Class<?> c) {
		List<Enum<?>> list = new ArrayList<Enum<?>>();
		Object[] constants = c.getEnumConstants();
		for (Object o : constants) {
			list.add((Enum<?>) o);
		}
		return list;
	}

	public String getValidationFieldName(Class<?> c) {
		return c.getAnnotation(ValidationField.class).value();
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
		String fieldName = getValidationFieldName(c);
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
