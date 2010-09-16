package org.gwtapp.core.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gwtapp.core.rpc.data.Value;

public class PipesManager {

	private final static Set<PipesManager> managers = new HashSet<PipesManager>();
	private final static Map<Class<?>, Value<?>> models = new HashMap<Class<?>, Value<?>>();

	private final Map<Class<?>, List<Pipe<?>>> pipes = new HashMap<Class<?>, List<Pipe<?>>>();
	// private final Map<Pipe<?>, PipeHandler<?>> handlers = new
	// HashMap<Pipe<?>, PipeHandler<?>>();

	private boolean connected = false;

	public <T> void addPipe(final Class<Pipe<T>> c, final Pipe<T> pipe) {
		if (!pipes.containsKey(c)) {
			pipes.put(c, new ArrayList<Pipe<?>>());
		}
		if (!models.containsKey(c)) {
			models.put(c, new Value<T>());
		}
		pipes.get(c).add(pipe);
		/*-
		PipeHandler<T> handler = new PipeHandler<T>() {
			@Override
			public void onChangeValue(T value) {
				fireValueChangeEvent(pipe, c, value);
			}
		};
		pipe.addHandler(handler);
		handlers.put(pipe, handler);
		 */
	}

	public <T> void removePipe(Class<Pipe<T>> c, Pipe<T> pipe) {
		if (pipes.containsKey(c)) {
			/*-
			@SuppressWarnings("unchecked")
			PipeHandler<T> handler = (PipeHandler<T>) handlers.get(pipe);
			if (handler != null) {
				pipe.removeHandler(handler);
			}*/
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

	public <T> void fireValueChangeEvent(Class<Pipe<T>> c, T value) {
		fireValueChangeEvent(null, c, value);
	}

	@SuppressWarnings("unchecked")
	public <T> void fireValueChangeEvent(Pipe<T> sender, Class<Pipe<T>> c,
			T value) {
		Value<T> model = (Value<T>) models.get(c);
		if (model != null) {
			model.set(value);
		}
		if (pipes.get(c) != null) {
			for (Pipe<?> pipe : pipes.get(c)) {
				if (pipe != sender) {
					((Pipe<T>) pipe).fireValueChange(value);
				}
			}
		}
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

	public static <T> void setValue(Class<Pipe<T>> c, T value) {
		setValue((PipesManager) null, c, value);
	}

	public static <T> void setValue(Pipe<T> sender, Class<Pipe<T>> c, T value) {
		for (PipesManager manager : managers) {
			manager.fireValueChangeEvent(sender, c, value);
		}
	}

	public static <T> void setValue(PipesManager sender, Class<Pipe<T>> c,
			T value) {
		for (PipesManager manager : managers) {
			if (manager != sender) {
				manager.fireValueChangeEvent(null, c, value);
			}
		}
	}
}
