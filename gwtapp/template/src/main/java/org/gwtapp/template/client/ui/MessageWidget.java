package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class MessageWidget extends Widget {

	public MessageWidget(String id) {
		this(DOM.getElementById(id));
	}

	public MessageWidget(Element e) {
		setElement(e);
	}
}
