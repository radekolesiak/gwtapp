package org.gwtapp.core.rpc.exception;

@SuppressWarnings("serial")
public class ValidationException extends RpcException {

	public String getStyleName() {
		return "";
	}

	public String getStyleName(String name, Enum<?> e) {
		return ("validation-" + name + "-" + e.name().replaceAll("_", "-"))
				.toLowerCase();
	}
}
