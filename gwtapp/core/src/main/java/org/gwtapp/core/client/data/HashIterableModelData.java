package org.gwtapp.core.client.data;

import java.util.HashMap;
import java.util.Map;

public abstract class HashIterableModelData implements IterableModelData {

	private Map<String, Object> delegated = new HashMap<String, Object>();

	private boolean inited = false;

	private void init() {
		if (!inited) {
			for (String property : getPropertyNames()) {
				delegated.put(property, delegated.get(property));
			}
		}
		inited = true;
	}

	@Override
	public Object get(String property) {
		init();
		if (delegated.containsKey(property)) {
			return delegated.get(property);
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@Override
	public Object set(String property, Object value) {
		init();
		if (delegated.containsKey(property)) {
			Object oldValue = get(property);
			delegated.put(property, value);
			return oldValue;
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	private boolean equalsAB(Object a, Object b) {
		if (a == b) {
			return true;
		} else if (a == null) {
			return false;
		} else {
			return a.equals(b);
		}
	}

	private int getHashCodeA(final Object a) {
		if (a != null) {
			return a.hashCode();
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 53;
		int hashCode = super.hashCode();
		for (String property : getPropertyNames()) {
			hashCode = (hashCode + getHashCodeA(get(property))) * prime;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ModelData) {
			ModelData m = (ModelData) o;
			for (String property : getPropertyNames()) {
				if (!equalsAB(get(property), m.get(property))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
