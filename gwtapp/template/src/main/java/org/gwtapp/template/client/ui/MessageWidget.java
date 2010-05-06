package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;

public class MessageWidget extends HTML {

	public MessageWidget(String id) {
		this(DOM.getElementById(id));
	}

	public MessageWidget(Element e) {
		super(e);
	}
}
