package com.cuprum.web.common.client.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.cuprum.web.common.client.exceptions.RpcException;
import com.cuprum.web.widgets.common.client.exception.MultipleValuesException;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * @author radek
 * 
 */
@SuppressWarnings("unchecked")
public class TValueModelData<T extends TValue> implements IModelData,
		IValueCollection, IsSerializable {

	private Map<String, T> map = null;

	protected Map<String, T> getMap() {
		if (map == null) {
			map = new HashMap<String, T>();
			updateMap(map);
		}
		return map;
	}

	protected void updateMap(Map<String, T> map) {
	}

	 //  TODO: to extend at the gwtapp-app project and implement with the extjs's ModelData
	/*-
	public void copyFrom(final ModelData model) {
		for (String key : map.keySet()) {
			map.get(key).set((Serializable) model.get(key));
		}
	}
	 */

	public void clearErrors() {
		for (T value : getMap().values()) {
			value.clearError();
		}
	}

	public boolean hasErrors() {
		for (T value : getMap().values()) {
			if (value.hasError()) {
				return true;
			}
		}
		return false;
	}

	public void evalErrors() throws MultipleValuesException {
		if (hasErrors()) {
			throw new MultipleValuesException();
		}
	}

	public <X> X get(String property) {
		Map<String, T> properties = getMap();
		TValue value = properties.get(property);
		if (value == null) {
			return null;
		} else {
			return (X) value.value;
		}
	}

	public Map<String, Object> getProperties() {
		Map<String, T> properties = getMap();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, T> entry : properties.entrySet()) {
			String key = entry.getKey();
			TValue value = entry.getValue();
			if (value == null) {
				map.put(key, null);
			} else {
				map.put(key, value.value);
			}
		}
		return map;
	}

	public Collection<String> getPropertyNames() {
		Map<String, T> properties = getMap();
		return properties.keySet();
	}

	public <X> X remove(String property) {
		Map<String, T> properties = getMap();
		X x = (X) get(property);
		properties.remove(property);
		return x;

	}

	public <X> X set(String property, X value) {
		X x = null;
		Map<String, T> properties = getMap();
		TValue v = properties.get(property);

		if (v != null) {
			x = (X) v.get();
			v.set((Serializable) value);
		}

		return x;
	}

	public RpcException setError(String property, RpcException error) {
		RpcException e = getMap().get(property).error;
		getMap().get(property).error = error;
		return e;
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (Entry<String, T> entry : map.entrySet()) {
			s.append(entry.getKey());
			s.append("=");
			s.append(entry.getValue());
			s.append(";");
		}
		return s.toString();
	}
}