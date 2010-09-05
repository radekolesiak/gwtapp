package org.gwtapp.validation.server;

public class GroupValidationCssGenerator extends ValidationCssGeneratorBase {

	public GroupValidationCssGenerator() {
	}

	@Override
	public String getCSS() {
		StringBuilder s = new StringBuilder();
		for (Class<?> c : getAnnotatedSubclasses()) {
			getCssForGroup(s, c);
		}
		return s.toString();
	}

	public void getCssForGroup(StringBuilder s, Class<?> c) {
		getCssForFieldName(s, getValidationFieldValue(c));
	}

	private void getCssForFieldName(StringBuilder s, String fieldName) {
		appendStyleClass(s, getPrefix(), getSeparator(), fieldName);
		appendStyle(s, getStyle());
	}

	private void appendStyleClass(StringBuilder s, String prefix,
			String separator, String fieldName) {
		s.append(prefix);
		s.append(" ");
		s.append(".validation-");
		s.append(fieldName);
		s.append(" ");
		s.append(separator);
		s.append(fieldName);
	}

	private void appendStyle(StringBuilder s, String style) {
		s.append(" { ");
		s.append(style);
		s.append(" }\n");
	}
}
