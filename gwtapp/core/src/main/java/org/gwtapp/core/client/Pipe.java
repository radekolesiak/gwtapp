package org.gwtapp.core.client;

public interface Pipe<T> {
	
	void fireValueChange(T value);
	
	void addHandler(PipeHandler<T> handler);
	
	void removeHandler(PipeHandler<T> handler);
}
