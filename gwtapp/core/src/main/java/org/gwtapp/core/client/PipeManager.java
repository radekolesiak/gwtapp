package org.gwtapp.core.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gwtapp.core.rpc.data.Value;

public class PipeManager {

	private final static Set<PipeManager> managers = new HashSet<PipeManager>();
	private final static Map<Class<?>, Value<?>> models = new HashMap<Class<?>, Value<?>>();

	private final Map<Class<?>, List<Pipe<?>>> pipes = new HashMap<Class<?>, List<Pipe<?>>>();

	private boolean connected = false;

	public <T> void addPipe(final Class<Pipe<T>> c, final Pipe<T> pipe) {
		if (!pipes.containsKey(c)) {
			pipes.put(c, new ArrayList<Pipe<?>>());
		}
		if (!models.containsKey(c)) {
			models.put(c, new Value<T>());
		}
		pipes.get(c).add(pipe);
	}

	public <T> void removePipe(Class<Pipe<T>> c, Pipe<T> pipe) {
		if (pipes.containsKey(c)) {
			pipes.get(c).remove(pipe);
		}
	}

	public void connect() {
		if (!connected) {
			managers.add(this);
		}
		connected = true;
	}

	public void disconnect() {
		managers.remove(this);
		connected = false;
	}

	public <T> void fireValueChange(Class<Pipe<T>> c, T value) {
		fireValueChange(c, value, null);
	}

	@SuppressWarnings("unchecked")
	public <T> void fireValueChange(Class<Pipe<T>> c, T value, Pipe<T> skip) {
		Value<T> model = (Value<T>) models.get(c);
		if (model != null) {
			model.set(value);
		}
		if (pipes.get(c) != null) {
			for (Pipe<?> pipe : pipes.get(c)) {
				if (pipe != skip) {
					((Pipe<T>) pipe).fireValueChange(value);
				}
			}
		}
	}

	public <T> void setValue(Class<Pipe<T>> c, T value) {
		setValue(c, value, this);
	}

	public static <T> void setValue(Class<Pipe<T>> c, T value, Pipe<T> skip) {
		for (PipeManager manager : managers) {
			manager.fireValueChange(c, value, skip);
		}
	}

	public static <T> void setValue(Class<Pipe<T>> c, T value, PipeManager skip) {
		for (PipeManager manager : managers) {
			if (manager != skip) {
				manager.fireValueChange(c, value);
			}
		}
	}

	public static <T> void setBroadcastValue(Class<Pipe<T>> c, T value) {
		setValue(c, value, (PipeManager) null);
	}

	public static <T> T getValue(Class<Pipe<T>> c) {
		@SuppressWarnings("unchecked")
		Value<T> model = (Value<T>) models.get(c);
		if (model != null) {
			return model.get();
		} else {
			return null;
		}
	}
}
