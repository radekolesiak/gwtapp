package org.gwtapp.core.server.template;

import org.gwtapp.core.client.data.AutoField;

public class Model {

	public static String template(AutoField<?, ?> field) {
		if (field != null) {
			return template(field.name());
		} else {
			return "";
		}
	}

	public static String template(String name) {
		if (name != null && !name.isEmpty()) {
			return "t:template=\"" + name + "\"";
		} else {
			return "";
		}
	}
}
