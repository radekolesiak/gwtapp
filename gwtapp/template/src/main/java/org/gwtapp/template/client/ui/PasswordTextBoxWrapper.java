package org.gwtapp.template.client.ui;

import org.gwtapp.core.client.ui.HasEnable;
import org.gwtapp.template.client.TemplateUtils;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordTextBoxWrapper extends PasswordTextBox implements
		HasEnable {

	private String value;

	public PasswordTextBoxWrapper() {
	}

	public PasswordTextBoxWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public PasswordTextBoxWrapper(Element e) {
		super(e);
		addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				setValue(event.getValue());
			}
		});
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
