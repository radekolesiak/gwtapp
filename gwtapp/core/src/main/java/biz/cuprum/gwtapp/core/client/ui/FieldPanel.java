package biz.cuprum.gwtapp.core.client.ui;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FieldPanel<T> extends FlowPanel implements HasName, HasValue<T> {

	private String name;
	private HasValue<T> controller;

	public FieldPanel(String label, HasValue<T> controller) {
		this.controller = controller;
		add(new Label(label));
		if (controller instanceof Widget) {
			add((Widget) controller);
		}
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

}
