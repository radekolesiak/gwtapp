package org.gwtapp.core.rpc.data;

public interface IndexedItem<T> {
	
	String getLabel();

	void setLabel(String label);

	T getIndex();

	void setIndex(T index);
}
