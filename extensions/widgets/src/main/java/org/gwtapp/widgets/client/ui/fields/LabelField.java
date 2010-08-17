package org.gwtapp.widgets.client.ui.fields;

import org.gwtapp.widgets.client.ui.ValuePanel;

public class LabelField<T> extends ValuePanel<T> {

	public static class Style {
		public final static String LABEL_FIELD = "labelField";
	}

	public LabelField() {
		addStyleName(Style.LABEL_FIELD);
	}

	public void setText(String value) {
		getElement().setInnerText(value);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		if (value == null) {
			setText("");
		} else {
			setText(value + "");
		}
		super.setValue(value, fireEvents);
	}
}
