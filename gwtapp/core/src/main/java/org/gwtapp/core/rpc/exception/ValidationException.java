package org.gwtapp.core.rpc.exception;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	private Map<String, Enum<?>> fields = new HashMap<String, Enum<?>>();

	public void set(String name, Enum<?> value) {
		fields.put(name, value);
	}

	public Enum<?> get(String name) {
		return fields.get(name);
	}

	public Map<String, Enum<?>> getFields() {
		return new HashMap<String, Enum<?>>(fields);
	}

	public void validate() throws ValidationException {
		for (Enum<?> value : fields.values()) {
			if (value != null) {
				throw this;
			}
		}
	}
}
