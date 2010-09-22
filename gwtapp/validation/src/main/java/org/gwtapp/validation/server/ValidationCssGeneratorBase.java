package org.gwtapp.validation.server;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.gwtapp.validation.rpc.exception.ValidationException;
import org.gwtapp.validation.rpc.exception.ValidationField;

public abstract class ValidationCssGeneratorBase implements ValidationCss {

	private final static Map<Class<? extends ValidationException>, Map<String, Class<? extends ValidationException>>> childrenFields = new ConcurrentHashMap<Class<? extends ValidationException>, Map<String, Class<? extends ValidationException>>>();
	private final static Map<Class<? extends ValidationException>, List<Class<?>>> annotatedSubclasses = new ConcurrentHashMap<Class<? extends ValidationException>, List<Class<?>>>();
	private final static Map<Class<?>, List<Enum<?>>> enumConstants = new ConcurrentHashMap<Class<?>, List<Enum<?>>>();
	private final static Map<Class<?>, String> validationFieldValues = new ConcurrentHashMap<Class<?>, String>();

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

	public Map<String, Class<? extends ValidationException>> getChildrenFields() {
		if (!childrenFields.containsKey(getValidationClass())) {
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
			childrenFields.put(getValidationClass(), children);
		}
		return childrenFields.get(getValidationClass());
	}

	public List<Class<?>> getAnnotatedSubclasses() {
		if (!annotatedSubclasses.containsKey(getValidationClass())) {
			List<Class<?>> list = new ArrayList<Class<?>>();
			Class<?>[] classes = getValidationClass().getClasses();
			for (Class<?> c : classes) {
				if (c.isEnum()
						&& c.getAnnotation(ValidationField.class) != null) {
					list.add(c);
				}
			}
			annotatedSubclasses.put(getValidationClass(), list);
		}
		return annotatedSubclasses.get(getValidationClass());
	}

	public List<Enum<?>> getEnumConstants(Class<?> c) {
		if (!enumConstants.containsKey(c)) {
			List<Enum<?>> list = new ArrayList<Enum<?>>();
			Object[] constants = c.getEnumConstants();
			for (Object o : constants) {
				list.add((Enum<?>) o);
			}
			enumConstants.put(c, list);
		}
		return enumConstants.get(c);
	}

	public String getValidationFieldValue(Class<?> c) {
		if (!validationFieldValues.containsKey(c)) {
			validationFieldValues.put(c, c.getAnnotation(ValidationField.class)
					.value());
		}
		return validationFieldValues.get(c);
	}
}
