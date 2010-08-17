package org.gwtapp.extension.widget.client.handler.fields;

import org.gwtapp.extension.widget.client.ui.fields.DoubleNumberField;
import org.gwtapp.template.client.UiHandler;


public class DoubleNumberHandler extends UiHandler<DoubleNumberField> {
	public DoubleNumberHandler() {
		super(new DoubleNumberField());
	}

	public DoubleNumberHandler(boolean readOnly) {
		super(new DoubleNumberField(readOnly));
	}
}
