package org.gwtapp.core.client.html.ui.form;

import org.gwtapp.core.client.html.ui.core.HLabel;
import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.client.html.ui.core.HWidget;
import org.gwtapp.core.client.html.ui.core.IValue;

public class HFieldPanel<T> extends HPanel implements IValue<T> {

	private String name;
	private String property;
	private IValue<T> controller;
	private HLabel label = new HLabel();

	public HFieldPanel() {
	}

	public <E extends HWidget & IValue<T>> HFieldPanel(String property,
			String label, E controller) {
		this.property = property;
		this.controller = controller;
		this.label.setLeaf(label);
		addWidget(this.label);
		addWidget(controller);
	}

	public String getControllerId() {
		return ((HWidget) controller).getId();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setLabel(HLabel label) {
		this.label = label;
	}

	public HLabel getLabel() {
		return label;
	}

	@Override
	public T getValue() {
		return controller.getValue();
	}

	@Override
	public void setValue(T value) {
		controller.setValue(value);
	}
}
