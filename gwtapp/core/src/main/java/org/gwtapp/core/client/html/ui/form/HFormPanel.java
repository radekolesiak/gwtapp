package org.gwtapp.core.client.html.ui.form;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;
import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.client.html.ui.core.HValue;

public class HFormPanel<T extends ModelData> extends HPanel implements
		HValue<T> {

	@SuppressWarnings("unchecked")
	private Map<String, HFieldPanel> fields = new HashMap<String, HFieldPanel>();
	private T value;
	private String name;

	public HFormPanel() {
	}

	public HFormPanel(T value) {
		setValue(value);
	}

	@Override
	public T getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value) {
		this.value = value;
		for (Map.Entry<String, HFieldPanel> entry : fields.entrySet()) {
			Object v = value.get(entry.getKey());
			entry.getValue().setValue(v);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addField(HFieldPanel<?> field) {
		fields.put(field.getProperty(), field);
		addWidget(field);
	}

	public void removeField(HFieldPanel<?> field) {
		fields.remove(field.getProperty());
		removeWidget(field);
	}

	@SuppressWarnings("unchecked")
	public HFieldPanel getField(String property) {
		return fields.get(property);
	}
}
