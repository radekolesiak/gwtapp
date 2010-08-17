package org.gwtapp.widgets.client.handler.fields;

import org.gwtapp.template.client.UiHandler;
import org.gwtapp.widgets.client.ui.fields.DoubleNumberField;


public class DoubleNumberHandler extends UiHandler<DoubleNumberField> {
	public DoubleNumberHandler() {
		super(new DoubleNumberField());
	}

	public DoubleNumberHandler(boolean readOnly) {
		super(new DoubleNumberField(readOnly));
	}
}
