package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;

public class ButtonWrapper extends Button {

	public ButtonWrapper() {
	}

	public ButtonWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public ButtonWrapper(Element e) {
		super(e);
	}
}
