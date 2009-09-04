package org.gwtapp.core.client.html.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;
import org.gwtapp.core.client.html.ui.core.HPanel;

public class HFormPanel<T extends ModelData> extends HPanel {

	private T value;
	private String name;
	private Map<String, HFieldPanel> fields = new HashMap<String, HFieldPanel>();

	public HFormPanel(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addField(HFieldPanel field) {
		fields.put(field.getProperty(), field);
	}

	public void removeField(HFieldPanel field) {
		fields.remove(field.getProperty());
	}
	
	public HFieldPanel getField(String property){
		return fields.get(property);
	}
}
