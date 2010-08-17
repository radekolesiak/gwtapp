package org.gwtapp.extension.widget.client.ui.fields;

import org.gwtapp.extension.widget.client.ui.NumericTextBox;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;

public class NumberField<T extends Number & Comparable<T>> extends FlowPanel
		implements HasValue<T>, Focusable, HasAllFocusHandlers {

	private final NumericTextBox<T> textBox;

	public NumberField(NumericTextBox<T> textBox) {
		this(textBox, false);
	}

	public NumberField(NumericTextBox<T> textBox, boolean readOnly) {
		this.textBox = textBox;
		setReadOnly(readOnly);
		add(textBox);
	}

	public void setReadOnly(boolean readOnly) {
		textBox.setReadOnly(readOnly);
	}

	public boolean isReadOnly() {
		return textBox.isReadOnly();
	}

	@Override
	public T getValue() {
		return textBox.getNumberValue();
	}

	@Override
	public void setValue(T value) {
		textBox.setNumberValue(value);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		textBox.setNumberValue(value, fireEvents);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			final ValueChangeHandler<T> handler) {
		return textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				final HandlerRegistration hr = addHandler(handler,
						ValueChangeEvent.getType());
				ValueChangeEvent.fire(NumberField.this, NumberField.this
						.getValue());
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						hr.removeHandler();
					}
				});
			}
		});
	}

	@Override
	public int getTabIndex() {
		return textBox.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		textBox.setAccessKey(key);
	}

	@Override
	public void setFocus(boolean focused) {
		textBox.setFocus(focused);
	}

	@Override
	public void setTabIndex(int index) {
		textBox.setTabIndex(index);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return textBox.addFocusHandler(handler);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textBox.addBlurHandler(handler);
	}

}
