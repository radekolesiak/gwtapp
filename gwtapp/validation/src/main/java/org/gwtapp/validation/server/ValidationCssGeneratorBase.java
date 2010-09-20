package org.gwtapp.validation.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

public abstract class ValidationCssGeneratorBase implements ValidationCss {

	private Class<? extends ValidationException> validationClass;
	private String prefix = "";
	private String separator = "";
	private String style = "";

	public void setValidationClass(
			Class<? extends ValidationException> validationClass) {
		this.validationClass = validationClass;
	}

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

	public Map<String, Class<? extends ValidationException>> getChildrenFields() {
		Map<String, Class<? extends ValidationException>> children = new HashMap<String, Class<? extends ValidationException>>();
		Field[] fields = getValidationClass().getDeclaredFields();
		for (Field field : fields) {
			ValidationField annotation = field
					.getAnnotation(ValidationField.class);
			if (annotation != null) {
				try {
					children.put(annotation.value(), field.getType()
							.asSubclass(ValidationException.class));
				} catch (Exception e) {
				}
			}
		}
		return children;
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
