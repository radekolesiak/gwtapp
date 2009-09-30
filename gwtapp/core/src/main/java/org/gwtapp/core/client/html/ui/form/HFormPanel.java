package org.gwtapp.core.client.html.ui.form;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;
import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.client.html.ui.core.IValue;

public class HFormPanel<T extends ModelData> extends HPanel implements
		IValue<T> {

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

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
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
	public <S> HFieldPanel<S> getField(String property) {
		return fields.get(property);
	}
}
