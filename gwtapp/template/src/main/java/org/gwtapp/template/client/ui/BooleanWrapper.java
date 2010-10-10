package org.gwtapp.template.client.ui;

import org.gwtapp.core.client.ui.HasEnable;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;

public class BooleanWrapper extends WidgetWrapper implements HasValue<Boolean>,
		HasEnable {

	private final InputElement e;
	private boolean value;

	public BooleanWrapper(boolean checkbox) {
		this(checkbox ? DOM.createInputCheck() : DOM.createInputRadio(""));
	}

	public BooleanWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public BooleanWrapper(Element e) {
		super(e);
		this.e = InputElement.as(e);
		this.value = isDefaultChecked();
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				value = isChecked();
				ValueChangeEvent.fire(BooleanWrapper.this, getValue());
			}
		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		e.setDisabled(!enabled);
		if (enabled) {
			removeStyleDependentName("disabled");
		} else {
			addStyleDependentName("disabled");
		}
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, false);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		if (value == null) {
			throw new IllegalArgumentException("value must not be null");
		}
		if (this.value != value) {
			setChecked(value);
			this.value = value;
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public boolean isDefaultChecked() {
		return e.isDefaultChecked();
	}

	protected boolean isChecked() {
		return e.isChecked();
	}

	protected void setChecked(boolean checked) {
		if (checked != value) {
			e.setChecked(checked);
		}
	}
}