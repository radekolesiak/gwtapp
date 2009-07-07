package org.gwtapp.core.server.data;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;

public class HashModelData implements ModelData {

	private final Map<String, Object> map = new HashMap<String, Object>();

	@Override
	public Object get(String property) {
		return map.get(property);
	}

	@Override
	public Object set(String property, Object value) {
		Object x = get(property);
		map.put(property, value);
		return x;
	}
}
