package org.gwtapp.core.client.data;

import java.util.HashMap;
import java.util.Map;

public abstract class HashModelData extends AbstractModelData {

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

}
