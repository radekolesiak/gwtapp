package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextBox;

public class TextBoxWrapper extends TextBox {

	public TextBoxWrapper() {
	}

	public TextBoxWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public TextBoxWrapper(Element e) {
		super(e);
	}
}
