package org.gwtapp.template.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FormPanel;

public class FormWrapper extends FormPanel {

	public FormWrapper() {
	}

	public FormWrapper(String id) {
		this(id, false);
	}

	public FormWrapper(String id, boolean createIFrame) {
		this(DOM.getElementById(id), createIFrame);
	}

	public FormWrapper(Element e) {
		this(e, false);
	}

	public FormWrapper(Element e, boolean createIFrame) {
		super(e, createIFrame);
	}
}
