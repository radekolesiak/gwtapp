package com.google.gwt.user.client.ui;


public class ContainerPanel extends ComplexPanel {
	
	@Override
	public void add(Widget w) {
		add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		if (beforeIndex < getWidgetCount()) {
			insert(w, getElement(), beforeIndex, true);
		} else {
			add(w);
		}
	}
}
