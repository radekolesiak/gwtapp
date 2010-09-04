package org.gwtapp.core.rpc.data;

import java.util.Collection;

public interface ModelData {

	<X> X get(String property);

	<X> X set(String property, X value);

	<X> X  remove(String property);

	Collection<String> getPropertyNames();
}
