package org.gwtapp.core.client.pipe;

public interface PipeHandler<T> {
	void onChangeValue(T value);
}
