package org.gwtapp.validation.server;

public class EnumValidationCssGenerator extends ValidationCssGeneratorBase {

	public EnumValidationCssGenerator() {
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
