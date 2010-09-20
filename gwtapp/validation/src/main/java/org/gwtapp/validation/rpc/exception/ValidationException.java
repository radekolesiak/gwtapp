package org.gwtapp.validation.rpc.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtapp.core.rpc.exception.RpcException;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	private Map<String, List<Enum<?>>> fields = new HashMap<String, List<Enum<?>>>();
	private Map<String, ValidationException> children = new HashMap<String, ValidationException>();

	public void add(String name, Enum<?> value) {
		if (!fields.containsKey(name)) {
			fields.put(name, new ArrayList<Enum<?>>());
		}
		fields.get(name).add(value);
	}

	public void set(String name, ValidationException value) {
		children.put(name, value);
	}

	public List<Enum<?>> get(String name) {
		return fields.get(name);
	}

	public Map<String, List<Enum<?>>> getFields() {
		return new HashMap<String, List<Enum<?>>>(fields);
	}

	public Map<String, ValidationException> getChildren() {
		return new HashMap<String, ValidationException>(children);
	}

	public void validate() throws ValidationException {
		if (!fields.isEmpty() || !children.isEmpty()) {
			throw this;
		}
	}
}
