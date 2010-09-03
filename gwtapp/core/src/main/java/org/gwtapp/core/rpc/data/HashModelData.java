package org.gwtapp.core.rpc.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class HashModelData extends AbstractModelData implements
		HasMetaField {

	@SuppressWarnings("rawtypes")
	@Transient
	private Map<String, MetaField> autofields = new HashMap<String, MetaField>();

	@SuppressWarnings("rawtypes")
	@Override
	public void addMetaField(MetaField field) {
		assert field.name() != null && !field.name().isEmpty();
		autofields.put(field.name(), field);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String property) {
		if (autofields.containsKey(property)) {
			@SuppressWarnings("rawtypes")
			MetaField field = autofields.get(property);
			return field.get(this);
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object set(String property, Object value) {
		if (autofields.containsKey(property)) {
			@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
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
