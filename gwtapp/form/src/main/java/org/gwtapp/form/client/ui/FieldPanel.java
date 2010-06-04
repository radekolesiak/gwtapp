package org.gwtapp.form.client.ui;

import org.gwtapp.core.shared.data.ModelData;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ContainerPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class FieldPanel<M extends ModelData, T> extends ContainerPanel
		implements HasName, HasValue<T> {

	public static class Style {
		public final static String FIELD_PANEL = "fieldPanel";
		public final static String LABEL = "label";
		public final static String CONTROLLER = "controller";
	}

	private String name;
	private String property;
	private HasValue<T> controller;

	public <E extends Widget & HasValue<T>> FieldPanel(final HasValue<M> model,
			String property, String label, E controller) {
		this(DOM.createDiv(), model, property, label, controller);
	}

	public <E extends Widget & HasValue<T>> FieldPanel(Element element,
			final HasValue<M> model, String property, String label, E controller) {
		this(element, model, property, controller);
		Label labelPanel = new Label(label);
		labelPanel.addStyleName(Style.LABEL);
		controller.addStyleName(Style.CONTROLLER);
		add(labelPanel);
		add(controller);
	}

	private <E extends HasValue<T>> FieldPanel(Element element,
			final HasValue<M> model, String property, E controller) {
		setElement(element);
		addStyleName(Style.FIELD_PANEL);
		addStyleName(Style.FIELD_PANEL + "-" + property);
		this.controller = controller;
		setProperty(property);
		controller.addValueChangeHandler(new ValueChangeHandler<T>() {
			@Override
			public void onValueChange(ValueChangeEvent<T> event) {
				if (model instanceof HasFireOnUpdate) {
					if (((HasFireOnUpdate) model).isFireOnUpdate()) {
						ValueChangeEvent.fire(model, model.getValue());
					}
				}
			}
		});
	}

	protected void wrap() {
		onAttach();
		RootPanel.detachOnWindowClose(this);
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
