package org.gwtapp.ccalc.client.handler.fields;

import org.gwtapp.ccalc.client.ui.fields.DoubleNumberField;
import org.gwtapp.template.client.handler.UiHandler;


public class DoubleNumberHandler extends UiHandler<DoubleNumberField> {
	public DoubleNumberHandler() {
		super(new DoubleNumberField());
	}

	public DoubleNumberHandler(boolean readOnly) {
		super(new DoubleNumberField(readOnly));
	}
}
