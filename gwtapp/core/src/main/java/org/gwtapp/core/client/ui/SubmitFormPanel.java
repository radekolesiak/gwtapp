package org.gwtapp.core.client.ui;

import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;

public class SubmitFormPanel<T extends ModelData> extends ComplexPanel
		implements HasName, HasValue<T>, IsFieldPanel<T> {

	private String name;
	private T value;

	private final FormPanel<T> form;

	public SubmitFormPanel(T value, FormPanel<T> form) {
		this(DOM.createDiv(), value, form);
	}

	public SubmitFormPanel(Element element, T value, FormPanel<T> form) {
		setElement(element);
		this.value = value;
		this.form = form;
	}

	public FormPanel<T> getForm() {
		return form;
	}

	@SuppressWarnings("unchecked")
	public void submit() {
		setValue((T) getForm().getValue().cloneTo(), true);
	}

	@Override
	public <X> void addField(FieldPanel<T, X> field) {
		form.addField(field);
	}

	@Override
	public <X> void removeField(FieldPanel<T, X> field) {
		form.removeField(field);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value, boolean fireEvents) {
		if (value == null) {
			throw new IllegalArgumentException("value must not be null");
		}
		this.value = value;
		form.setValue((T) value.cloneTo());
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
