package org.gwtapp.core.rpc.data;

import java.util.Collection;

public interface HasValues<T> {
	
	void setValues(Collection<T> items);

	Collection<T> getValues();
}
