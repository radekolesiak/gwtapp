package org.gwtapp.widgets.client.ui;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public abstract class NumericTextBox<T extends Number & Comparable<T>>
		extends TextBox {

	public static class Style {
		public final static String NUMERIC_TEXT_BOX = "numericTextBox";
		public final static String INVALID_VALUE = "invalidValue";
	}

	private T minValue = getInitMin();
	private T maxValue = getInitMax();

	private T value = null;

	public NumericTextBox(T minValue, T maxValue) {
		this();
		setMinValue(minValue);
		setMaxValue(maxValue);
	}

	public NumericTextBox() {
		addStyleName(Style.NUMERIC_TEXT_BOX);
		addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				validate();
			}
		});
		addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				validate();
			}
		});
	}

	protected abstract T getInitMax();

	protected abstract T getInitMin();

	protected abstract T parse(String t) throws NumberFormatException;

	private void validate() {
		try {
			String text = getValue();
			removeStyleName(Style.INVALID_VALUE);
			if (text == null || text.isEmpty()) {
				NumericTextBox.this.value = null;
			} else {
				T value = parse(text.replace(',', '.'));
				if (inRange(value)) {
					NumericTextBox.this.value = value;
				} else {
					addStyleName(Style.INVALID_VALUE);
				}
			}
		} catch (Exception e) {
			addStyleName(Style.INVALID_VALUE);
		}
	}

	private boolean inRange(T value) {
		return !(value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0);
	}

	public T getNumberValue() {
		return value;
	}

	public void setNumberValue(T value) {
		setNumberValue(value, false);
	}

	public void setNumberValue(T value, boolean fireEevents) {
		if (value != null && !inRange(value)) {
			throw new IllegalArgumentException();
		}
		this.value = value;
		if (value != null) {
			setValue("" + value, fireEevents);
		} else {
			setValue("", fireEevents);
		}
	}

	public void setMinValue(T minValue) {
		this.minValue = minValue;
	}

	public T getMinValue() {
		return minValue;
	}

	public void setMaxValue(T maxValue) {
		this.maxValue = maxValue;
	}

	public T getMaxValue() {
		return maxValue;
	}
}
