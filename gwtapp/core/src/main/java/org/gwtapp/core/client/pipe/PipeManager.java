package org.gwtapp.core.client.pipe;

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
	private boolean enabled = true;

	public <T> void addPipe(final Class<? extends Pipe<T>> c, final Pipe<T> pipe) {
		if (!pipes.containsKey(c)) {
			pipes.put(c, new ArrayList<Pipe<?>>());
		}
		if (!models.containsKey(c)) {
			models.put(c, new Value<T>());
		}
		pipes.get(c).add(pipe);
	}

	public <T> void removePipe(Class<? extends Pipe<T>> c, Pipe<T> pipe) {
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

	public boolean isConnected() {
		return connected;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public <T> void fireValueChanged(Class<? extends Pipe<T>> c, T value) {
		if (isUsable()) {
			fireValueChanged(c, value, null);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void fireValueChanged(Class<? extends Pipe<T>> c, T value,
			Pipe<T> skip) {
		if (isUsable()) {
			getModel(c).set(value);
			if (pipes.get(c) != null) {
				for (Pipe<?> pipe : pipes.get(c)) {
					if (pipe != skip) {
						((Pipe<T>) pipe).fireValueChanged(value);
					}
				}
			}
		}
	}

	public <T> void setValue(Class<? extends Pipe<T>> c, T value) {
		if (isUsable()) {
			setValue(c, value, this);
		}
	}

	public <T> void setValue(Class<? extends Pipe<T>> c, T value,
			PipeManager skip) {
		if (isUsable()) {
			getModel(c).set(value);
			for (PipeManager manager : managers) {
				if (manager != skip) {
					manager.fireValueChanged(c, value);
				}
			}
		}
	}

	public <T> void setValue(Class<? extends Pipe<T>> c, T value, Pipe<T> skip) {
		if (isUsable()) {
			for (PipeManager manager : managers) {
				manager.fireValueChanged(c, value, skip);
			}
		}
	}

	public static <T> void setBroadcastValue(Class<? extends Pipe<T>> c, T value) {
		getModel(c).set(value);
		for (PipeManager manager : managers) {
			manager.fireValueChanged(c, value);
		}
	}

	public static <T> T getBroadcastValue(Class<? extends Pipe<T>> c) {
		Value<T> model = getModel(c);
		return model.get();
	}

	public static void resetAll() {
		managers.clear();
		models.clear();
	}

	private boolean isUsable() {
		return isConnected() && isEnabled();
	}

	@SuppressWarnings("unchecked")
	private static <T> Value<T> getModel(Class<?> c) {
		if (!models.containsKey(c)) {
			models.put(c, new Value<T>());
		}
		return (Value<T>) models.get(c);
	}
}
