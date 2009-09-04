package org.gwtapp.core.client.html.ui.core;

public interface IsContainer extends Iterable<HWidget> {
	
	void addWidget(HWidget... widget);
	
	void removeWidget(HWidget... widget);

	HWidget getWidget(int index);
	
	void clear();

	int size();
}
