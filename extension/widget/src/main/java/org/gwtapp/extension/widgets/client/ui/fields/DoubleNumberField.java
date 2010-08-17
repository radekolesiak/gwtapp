package org.gwtapp.extension.widgets.client.ui.fields;

import org.gwtapp.extension.widgets.client.ui.DoubleNumericTextBox;

public class DoubleNumberField extends NumberField<Double>{

	public DoubleNumberField() {
		this(false);
	}

	public DoubleNumberField(boolean readOnly) {
		super(new DoubleNumericTextBox(), readOnly);
	}
}
