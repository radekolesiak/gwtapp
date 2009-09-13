package com.google.gwt.user.client.ui;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

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
