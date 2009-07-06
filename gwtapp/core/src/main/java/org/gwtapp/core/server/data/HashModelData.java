package org.gwtapp.core.server.data;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;

public class HashModelData implements ModelData {

	private final Map<String, Object> map = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property) {
		return (X) map.get(property);
	}

	@Override
	public <X> X set(String property, X value) {
		X x = get(property);
		map.put(property, value);
		return x;
	}
}
