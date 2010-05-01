package org.gwtapp.html.rpc.ui;

public interface HValue<T> {
	
	void setValue(T value);

	T getValue();
}
