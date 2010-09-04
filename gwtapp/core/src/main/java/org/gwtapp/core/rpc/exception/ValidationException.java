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

	public String getStyleName(Field field) {
		return ("validation-" + field.getName() + "-" + field.getValue().name()
				.replaceAll("_", "-")).toLowerCase();
	}

	public String getStyleName(String name, Enum<?> e) {
		return ("validation-" + name + "-" + e.name().replaceAll("_", "-"))
				.toLowerCase();
	}

	public String getStyleName() {
		String s = "";
		for (Field field : getFields()) {
			s += getStyleName(field);
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
