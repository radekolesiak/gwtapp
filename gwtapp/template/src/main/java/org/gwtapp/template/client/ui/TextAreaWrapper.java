package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextArea;

public class TextAreaWrapper extends TextArea {

	public TextAreaWrapper() {
	}

	public TextAreaWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public TextAreaWrapper(Element e) {
		super(e);
	}
}
