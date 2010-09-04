package org.gwtapp.core.server;

public class GroupsValidationCssGenerator extends ValidationCssGeneratorBase {

	public GroupsValidationCssGenerator() {
	}

	@Override
	public String getCSS() {
		StringBuilder s = new StringBuilder();
		for (Class<?> c : getAnnotatedSubclasses()) {
			getCssForName(s, getValidationFieldValue(c));
		}
		return s.toString();
	}

	private void getCssForName(StringBuilder s, String fieldName) {
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
