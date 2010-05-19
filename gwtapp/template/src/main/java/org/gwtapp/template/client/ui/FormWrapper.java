package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FormPanel;

public class FormWrapper extends FormPanel {
	
	public FormWrapper() {
	}

	public FormWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public FormWrapper(Element e) {
		super(e);
	}
}
