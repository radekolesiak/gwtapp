package com.cuprum.web.common.client.data;

import java.util.Collection;
import java.util.Map;

public interface IModelData {
	
	public <X> X get(String property);

	public Map<String, Object> getProperties();

	public Collection<String> getPropertyNames();

	public <X> X remove(String property);

	public <X> X set(String property, X value);
}
