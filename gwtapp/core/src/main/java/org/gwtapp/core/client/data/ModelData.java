package org.gwtapp.core.client.data;

import java.util.Collection;

public interface ModelData extends IsClonable<ModelData> {

	Object get(String property);

	Object set(String property, Object value);

	Object remove(String property);

	Collection<String> getPropertyNames();
}
