package org.gwtapp.core.rpc.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class HashModelData extends AbstractModelData implements
		HasMetaField {

	private Map<String, MetaField> autofields = new HashMap<String, MetaField>();

	@Override
	public void addMetaField(MetaField field) {
		assert field.name() != null && !field.name().isEmpty();
		autofields.put(field.name(), field);
	}

	@Override
	public Object get(String property) {
		if (autofields.containsKey(property)) {
			MetaField field = autofields.get(property);
			return field.get(this);
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@Override
	public Object set(String property, Object value) {
		if (autofields.containsKey(property)) {
			MetaField field = autofields.get(property);
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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean empty = true;
		for (Map.Entry<String, MetaField> entry : autofields.entrySet()) {
			if (!empty) {
				sb.append(",");
			}
			sb.append("(");
			sb.append(entry.getKey());
			sb.append("=>");
			sb.append(get(entry.getKey()));
			sb.append(")");
			empty = false;
		}
		sb.append("]");
		return sb.toString();
	}

}
