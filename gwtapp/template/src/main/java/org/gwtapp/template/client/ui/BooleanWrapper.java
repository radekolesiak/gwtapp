package org.gwtapp.template.client.ui;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;

public class BooleanWrapper extends WrapperWidget implements
		HasValue<Boolean>, HasClickHandlers {

	private final InputElement e;

	public BooleanWrapper(String id) {
		this(DOM.getElementById(id));
	}

	public BooleanWrapper(Element e) {
		super(e);
		this.e = InputElement.as(e);
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ValueChangeEvent.fire(BooleanWrapper.this, getValue());
			}
		});
	}

	@Override
	public Boolean getValue() {
		if (isAttached()) {
			return e.isChecked();
		} else {
			return e.isDefaultChecked();
		}
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
		e.setChecked(value);
		e.setDefaultChecked(value);
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
}