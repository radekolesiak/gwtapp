package org.gwtapp.core.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pipe<T> {

	private final List<PipeHandler<T>> handlers = new ArrayList<PipeHandler<T>>();

	public Pipe() {
	}

	public Pipe(PipeHandler<T>... handlers) {
		this.handlers.addAll(Arrays.asList(handlers));
	}

	public void fireValueChange(T value) {
		for (PipeHandler<T> handler : handlers) {
			handler.onChangeValue(value);
		}
	}

	public void addHandler(PipeHandler<T> handler) {
		handlers.add(handler);
	}

	public void removeHandler(PipeHandler<T> handler) {
		handlers.remove(handler);
	}
}
