package org.gwtapp.core.rpc.html.ui.core;

public interface IContainer extends Iterable<HWidget> {
	
	void addWidget(HWidget widget);
	
	void removeWidget(HWidget widget);

	HWidget getWidget(int index);
	
	void clear();

	int size();
}
