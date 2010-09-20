package org.gwtapp.validation.server;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.validation.rpc.exception.ValidationException;

public class EnumValidationCssGenerator extends ValidationCssGeneratorBase {

	private String child = "";

	public EnumValidationCssGenerator() {
	}

	public EnumValidationCssGenerator(String child) {
		this.child = child;
	}

	@Override
	public String getCSS() {
		StringBuilder s = new StringBuilder();
		for (Class<?> c : getAnnotatedSubclasses()) {
			getCssForEnum(s, c);
		}
		for (Entry<String, Class<? extends ValidationException>> child : getChildrenFields()
				.entrySet()) {
			String name = child.getKey();
			if (StringUtils.isNotEmpty(this.child)) {
				name = child + "-" + name;
			}
			EnumValidationCssGenerator generator = new EnumValidationCssGenerator(
					name);
			generator.setPrefix(getPrefix());
			generator.setSeparator(getSeparator());
			generator.setStyle(getStyle());
			generator.setValidationClass(child.getValue());
			s.append(generator.getCSS());
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
		if (StringUtils.isNotEmpty(child)) {
			s.append(child);
			s.append("-");
		}
		s.append(enumFieldName);
		s.append(" ");
		s.append(separator);
		if (StringUtils.isNotEmpty(child)) {
			s.append(child);
			s.append("-");
		}
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
