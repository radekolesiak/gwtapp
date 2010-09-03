package org.gwtapp.core.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.core.rpc.exception.Validation;
import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

public class ValidationCssGenerator {

	private Class<? extends ValidationException> validationClass;

	public ValidationCssGenerator() {
	}

	public void setValidationClass(
			Class<? extends ValidationException> validationClass) {
		this.validationClass = validationClass;
	}

	public Class<? extends ValidationException> getValidationClass() {
		return validationClass;
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

	public String getValidationName() {
		return validationClass.getAnnotation(Validation.class).value();
	}

	public String getValidationFieldName(Class<?> c) {
		return c.getAnnotation(ValidationField.class).value();
	}

	public String getCssForMatcher(String matcher) {
		return "";
	}

	public void getCssForMatcherAndField(StringBuilder s, String matcher,
			String fieldName, Enum<?> field) {
		appendMatcher(s, matcher);
		appendMatcher(s, getValidationName());
		{
			s.append(".validation-");
			s.append(fieldName);
			s.append("-");
			s.append(field.name());
		}
		s.append(" ");
		{
			s.append(".validation.");
			s.append(fieldName);
			s.append("-");
			s.append(field.name());
		}
		s.append(" { display: block; }\n");
	}

	private void appendMatcher(StringBuilder s, String matcher) {
		if (!StringUtils.isEmpty(matcher)) {
			s.append(".");
			s.append(matcher);
			s.append(" ");
		}
	}
}
