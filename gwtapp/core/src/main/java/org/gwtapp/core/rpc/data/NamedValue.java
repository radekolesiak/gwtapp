package org.gwtapp.core.rpc.data;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NamedValue<T> implements IsSerializable {

	private String name;
	private T value;

	public NamedValue() {
	}

	public NamedValue(String name, T value) {
		setName(name);
		setValue(value);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

}
