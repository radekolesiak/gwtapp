package org.gwtapp.core.server;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.exception.ValidationException;
import org.gwtapp.core.rpc.exception.ValidationField;

public class ValidationCssGeneratorBase {
	
	private Class<? extends ValidationException> validationClass;

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

	public String getValidationFieldValue(Class<?> c) {
		return c.getAnnotation(ValidationField.class).value();
	}		
}
