package org.gwtapp.core.rpc.exception;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	private Map<String, Enum<?>> fields = new HashMap<String, Enum<?>>();

	public String getStyleClass(String name, Enum<?> e) {
		String style = "";
		style += "validation-" + name;
		style += " ";
		style += "validation-" + name + "-" + e.name();
		return style.replaceAll("_", "-").toLowerCase();
	}

	public String getStyleClass() {
		String s = "";
		for (Map.Entry<String, Enum<?>> field : fields.entrySet()) {
			if (field.getValue() != null) {
				s += getStyleClass(field.getKey(), field.getValue());
				s += " ";
			}
		}
		return s;
	}

	public void set(String name, Enum<?> value) {
		fields.put(name, value);
	}

	public Enum<?> get(String name) {
		return fields.get(name);
	}

	public void validate() throws ValidationException {
		for (Enum<?> value : fields.values()) {
			if (value != null) {
				throw this;
			}
		}
	}
}
