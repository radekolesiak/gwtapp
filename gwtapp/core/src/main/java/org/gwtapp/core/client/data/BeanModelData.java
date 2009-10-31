package org.gwtapp.core.client.data;

public abstract class BeanModelData implements ModelData {

	@Override
	public Object remove(String property) {
		return set(property, null);
	}
}
