package org.gwtapp.core.rpc.data;

import java.util.List;

public interface HasValues<T> {
	
	void setValues(List<T> items);

	List<T> getValues();
}
