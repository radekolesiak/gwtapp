package org.gwtapp.core.rpc.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

public class HashModelData extends AbstractModelData implements HasMetaField {

	@SuppressWarnings("rawtypes")
	@Transient
	private Map<String, MetaField> metafields = new HashMap<String, MetaField>();

	@SuppressWarnings("rawtypes")
	@Override
	public void addMetaField(MetaField field) {
		assert field.name() != null && !field.name().isEmpty();
		metafields.put(field.name(), field);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <X> X get(String property) {
		if (metafields.containsKey(property)) {
			MetaField field = metafields.get(property);
			return (X) field.get(this);
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <X> X set(String property, X value) {
		if (metafields.containsKey(property)) {
			MetaField field = metafields.get(property);
			X oldValue = (X) field.get(this);
			field.set(this, value);
			return oldValue;
		} else {
			throw new IllegalArgumentException("Unknown property: " + property);
		}
	}

	@Override
	public Collection<String> getPropertyNames() {
		return metafields.keySet();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean empty = true;
		for (Map.Entry<String, MetaField> entry : metafields.entrySet()) {
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
