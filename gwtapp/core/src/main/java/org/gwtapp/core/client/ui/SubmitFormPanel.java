package org.gwtapp.core.client.ui;

import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;

public class SubmitFormPanel<T extends ModelData> extends FlowPanel implements
		HasName, HasValue<T>, HasFieldPanel<T> {

	private String name;
	private T value;

	private final FormPanel<T> form;

	public SubmitFormPanel(T value, FormPanel<T> form) {
		this.value = value;
		this.form = form;
		add(form);
	}

	public void submit() {
		SubmitFormPanel.this.form.getValue().copyTo(value);
		ValueChangeEvent.fire(this, value);
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

	@Override
	public void setValue(T value, boolean fireEvents) {
		if (value == null) {
			throw new IllegalArgumentException("value must not be null");
		}
		this.value = value;
		value.copyTo(form.getValue());
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
