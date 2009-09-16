package org.gwtapp.core.client.html.ui.form;

import org.gwtapp.core.client.html.ui.core.HLabel;
import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.client.html.ui.core.HWidget;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HFieldPanel extends HPanel implements IsSerializable {

	private String name;
	private String property;
	private HWidget controller;
	//private HLabel label = new HLabel();

	public HFieldPanel(String property, String label, HWidget controller) {
		this.property = property;
		//this.label.setHTML(label);
		// addWidget(this.label);
		// addWidget(controller);
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

	public void setController(HWidget controller) {
		this.controller = controller;
	}

	public HWidget getController() {
		return controller;
	}

	public void setLabel(HLabel label) {
		//this.label = label;
	}

	public HLabel getLabel() {
		//return label;
		return null;
	}
}
