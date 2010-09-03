package org.gwtapp.core.server;

import java.util.ArrayList;
import java.util.List;

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
			if (c.getAnnotation(ValidationField.class) != null) {
				list.add(c);
			}
		}
		return list;
	}
}
