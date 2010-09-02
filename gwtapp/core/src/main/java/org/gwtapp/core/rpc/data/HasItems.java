package org.gwtapp.core.rpc.data;

import java.util.List;

public interface HasItems<T> {
	
	void setItems(List<T> items);

	List<T> getItems();
}
