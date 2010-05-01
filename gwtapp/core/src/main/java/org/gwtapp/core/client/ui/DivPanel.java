package org.gwtapp.core.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ContainerPanel;
import com.google.gwt.user.client.ui.HasValue;

public class DivPanel<T> extends ContainerPanel implements HasValue<T> {

	public static class Style {
		public final static String DIV_PANEL = "divPanel";
	}

	private T value;

	public DivPanel() {
		this(DOM.createDiv());
	}

	public DivPanel(Element element) {
		this(element, null);
	}

	public DivPanel(T value) {
		this(DOM.createDiv(), value);
	}

	public DivPanel(Element element, T value) {
		setElement(element);
		addStyleName(Style.DIV_PANEL);
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

}
