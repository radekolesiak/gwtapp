package org.gwtapp.startapp.client.ui.delegated;

import org.gwtapp.core.client.ui.DelegatedPanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

public class DoubleBox extends DelegatedPanel<Double, String> {

	private final static double eps = 1e-3;

	private final TextBox tb = new TextBox();
	private final HTML state = new HTML();
	private final HTML thisHandlerState = new HTML();
	private final HTML delegatedHandlerState = new HTML();

	private int thisCount = 0;
	private int delegatedCount = 0;

	public DoubleBox(String id) {
		super(DOM.getElementById(id));
		add(tb);
		add(state);
		add(delegatedHandlerState);
		add(thisHandlerState);
		addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(ValueChangeEvent<Double> event) {
				thisHandlerState.setText("Double(" + (++thisCount) + "): "
						+ event.getValue());
			}
		});
		tb.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				delegatedHandlerState.setText("String(" + (++delegatedCount)
						+ "): " + event.getValue());
			}
		});
	}

	@Override
	public HasValue<String> getDelegated() {
		return tb;
	}

	@Override
	public Double convertToX(String value) {
		Double doubleValue = null;
		try {
			doubleValue = Double.parseDouble(value);
			state.setText("OK: " + doubleValue);
		} catch (Exception e) {
			state.setText("Invalid value: " + value);
		}
		return doubleValue;
	}

	@Override
	public String convertToY(Double value) {
		return "" + value;
	}

	@Override
	public boolean isDelegatedToUpdate(Double oldValue, Double newValue) {
		if (oldValue == null ^ newValue == null) {
			return true;
		} else if (oldValue == null && newValue == null) {
			return false;
		} else {
			return Math.abs(oldValue - newValue) > eps;
		}
	}
}
