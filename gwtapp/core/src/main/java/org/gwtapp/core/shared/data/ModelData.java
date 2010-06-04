package org.gwtapp.core.shared.data;

import java.util.Collection;

public interface ModelData {

	Object get(String property);

	Object set(String property, Object value);

	Object remove(String property);

	Collection<String> getPropertyNames();
}
