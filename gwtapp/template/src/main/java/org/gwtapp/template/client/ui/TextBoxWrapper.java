package org.gwtapp.template.client.ui;

import org.gwtapp.template.client.TemplateUtils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextBox;

public class TextBoxWrapper extends TextBox {

	private String value;

	public TextBoxWrapper() {
	}

	public TextBoxWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public TextBoxWrapper(Element e) {
		super(e);
	}

	@Override
	public String getValue() {
		return value;
	};

	@Override
	public void setValue(String value) {
		setValue(value, false);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		if (!TemplateUtils.equals(this.value, value)) {
			this.value = value;
			super.setValue(value, fireEvents);
		}
	}
}
