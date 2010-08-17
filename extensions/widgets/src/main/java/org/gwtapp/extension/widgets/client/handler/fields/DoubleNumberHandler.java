package org.gwtapp.extension.widgets.client.handler.fields;

import org.gwtapp.extension.widgets.client.ui.fields.DoubleNumberField;
import org.gwtapp.template.client.UiHandler;


public class DoubleNumberHandler extends UiHandler<DoubleNumberField> {
	public DoubleNumberHandler() {
		super(new DoubleNumberField());
	}

	public DoubleNumberHandler(boolean readOnly) {
		super(new DoubleNumberField(readOnly));
	}
}
