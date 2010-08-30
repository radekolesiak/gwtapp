package org.gwtapp.ccalc.client.ui.fields;

import org.gwtapp.ccalc.rpc.proc.calculator.Calculator;


public class ExchangeLabelField extends LabelField<Double> {
	@Override
	public void setValue(Double value, boolean fireEvents) {
		if (value != null && value < 0) {
			setText("@" + Calculator.q(-value));
		} else {
			if (value == null) {
				setText("");
			} else {
				setText(value + "");
			}
		}
		if (fireEvents) {
			fireValueChangeEvent(value);
		}
	}
}
