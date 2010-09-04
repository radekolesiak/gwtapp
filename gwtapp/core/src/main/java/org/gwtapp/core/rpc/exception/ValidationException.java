package org.gwtapp.core.rpc.exception;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.data.NamedValue;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	public static class Field extends NamedValue<Enum<?>> {

		public Field() {
		}

		public Field(String name, Enum<?> value) {
			setName(name);
			setValue(value);
		}
	}

	public String getStyleClass(Field field) {
		return getStyleClass(field.getName(), field.getValue());
	}

	public String getStyleClass(String name, Enum<?> e) {
		String style = "";
		style += "validation-" + name;
		style += " ";
		style += "validation-" + name + "-" + e.name();
		return style.replaceAll("_", "-").toLowerCase();
	}

	public String getStyleClass() {
		String s = "";
		for (Field field : getFields()) {
			s += getStyleClass(field);
			s += " ";
		}
		return s;
	}

	// TODO try replace by deferred binding and already existing annotation
	// try by removing this method and binding getStyleName() method
	public List<Field> getFields() {
		return new ArrayList<Field>();
	}
}
