package org.gwtapp.core.client;

public class Value<T> {

	private T value = null;

	public Value() {
	}

	public Value(T value) {
		set(value);
	}

	public T get() {
		return value;
	}

	public void set(T value) {
		this.value = value;
	}
}
