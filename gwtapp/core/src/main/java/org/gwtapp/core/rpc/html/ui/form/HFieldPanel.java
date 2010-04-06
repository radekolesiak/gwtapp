package org.gwtapp.core.rpc.html.ui.form;

import org.gwtapp.core.rpc.html.ui.core.HLabel;
import org.gwtapp.core.rpc.html.ui.core.HPanel;
import org.gwtapp.core.rpc.html.ui.core.HValue;
import org.gwtapp.core.rpc.html.ui.core.HWidget;

public class HFieldPanel<T> extends HPanel implements HValue<T> {

	private String name;
	private String property;
	private HValue<T> controller;
	private HLabel label = new HLabel();

	public HFieldPanel() {
	}

	public <E extends HWidget & HValue<T>> HFieldPanel(String property,
			String label, E controller) {
		this.property = property;
		this.controller = controller;
		this.label.setLeaf(label);
		addWidget(this.label);
		addWidget(controller);
	}

	@SuppressWarnings("unchecked")
	public  <E extends HWidget & HValue<T>> E getController(){
		return (E)controller;
	}
	
	public String getControllerId() {
		return ((HWidget) controller).getId();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
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
