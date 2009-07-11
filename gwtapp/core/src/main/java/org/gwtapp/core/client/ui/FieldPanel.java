package org.gwtapp.core.client.ui;

import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FieldPanel<M extends ModelData, T> extends FlowPanel implements
		HasName, HasValue<T> {

	private String name;
	private String property;
	private HasValue<T> controller;

	public <E extends Widget & HasValue<T>> FieldPanel(final HasValue<M> model,
			String property, String label, E controllerWidget) {
		this.controller = controllerWidget;
		setProperty(property);
		add(new Label(label));
		add(controllerWidget);
		controllerWidget.addValueChangeHandler(new ValueChangeHandler<T>() {
			@Override
			public void onValueChange(ValueChangeEvent<T> event) {
				model.getValue().set(getProperty(), event.getValue());
				model.setValue(model.getValue(), true);
			}
		});
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
		return controller.getValue();
	}

	@Override
	public void setValue(T value) {
		controller.setValue(value);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		controller.setValue(value, fireEvents);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return controller.addValueChangeHandler(handler);
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

}
