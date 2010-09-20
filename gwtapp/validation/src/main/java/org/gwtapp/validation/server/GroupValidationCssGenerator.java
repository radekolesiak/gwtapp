package org.gwtapp.validation.server;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.validation.rpc.exception.ValidationException;

public class GroupValidationCssGenerator extends ValidationCssGeneratorBase {

	private String child = "";

	public GroupValidationCssGenerator() {
	}

	public GroupValidationCssGenerator(String child) {
		this.child=child;
	}

	@Override
	public String getCSS() {
		StringBuilder s = new StringBuilder();
		for (Class<?> c : getAnnotatedSubclasses()) {
			getCssForGroup(s, c);
		}
		for (Entry<String, Class<? extends ValidationException>> child : getChildrenFields()
				.entrySet()) {
			String name = child.getKey();
			if (StringUtils.isNotEmpty(this.child)) {
				name = child + "-" + name;
			}
			GroupValidationCssGenerator generator = new GroupValidationCssGenerator(
					name);
			generator.setPrefix(getPrefix());
			generator.setSeparator(getSeparator());
			generator.setStyle(getStyle());
			generator.setValidationClass(child.getValue());
			s.append(generator.getCSS());
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
		if (StringUtils.isNotEmpty(child)) {
			s.append(child);
			s.append("-");
		}
		s.append(fieldName);
		s.append(" ");
		s.append(separator);
		if (StringUtils.isNotEmpty(child)) {
			s.append(child);
			s.append("-");
		}
		s.append(fieldName);
	}

	private void appendStyle(StringBuilder s, String style) {
		s.append(" { ");
		s.append(style);
		s.append(" }\n");
	}
}
