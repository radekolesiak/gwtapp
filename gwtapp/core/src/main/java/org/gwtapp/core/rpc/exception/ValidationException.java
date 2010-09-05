package org.gwtapp.core.rpc.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	private Map<String, List<Enum<?>>> fields = new HashMap<String, List<Enum<?>>>();

	public void add(String name, Enum<?> value) {
		if (!fields.containsKey(name)) {
			fields.put(name, new ArrayList<Enum<?>>());
		}
		fields.get(name).add(value);
	}

	public List<Enum<?>> get(String name) {
		return fields.get(name);
	}

	public Map<String, List<Enum<?>>> getFields() {
		return new HashMap<String, List<Enum<?>>>(fields);
	}

	public void validate() throws ValidationException {
		if (!fields.isEmpty()) {
			throw this;
		}
	}
}
