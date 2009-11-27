package org.gwtapp.core.client.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class HashModelData extends AbstractModelData {

	private Map<String, AutoField> autofields = new HashMap<String, AutoField>();

	public void addAutoField(final AutoField field) {
		autofields.put(field.name(), field);
	}

	@Override
	public Object get(String property) {
		if (autofields.containsKey(property)) {
			AutoField field = autofields.get(property);
			return field.get(this);
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@Override
	public Object set(String property, Object value) {
		if (autofields.containsKey(property)) {
			AutoField field = autofields.get(property);
			Object oldValue = field.get(this);
			field.set(this, value);
			return oldValue;
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@Override
	public Collection<String> getPropertyNames() {
		return autofields.keySet();
	}

}
