package org.gwtapp.core.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;
import org.gwtapp.core.client.html.ui.HFormPanel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;

public class FormPanel<T extends ModelData> extends ComplexPanel implements
		HasName, HasValue<T>, IsFieldPanel<T> {

	@SuppressWarnings("unchecked")
	private final Map<String, FieldPanel> fields = new HashMap<String, FieldPanel>();
	private String name;
	private T value;

	public FormPanel(T value) {
		this(DOM.createDiv(), value);
	}

	public FormPanel(Element element, T value) {
		setElement(element);
		this.value = value;
	}

	public FormPanel(HFormPanel<T> panel) {
		this(DOM.getElementById(panel.getId()), panel.getValue());
		setName(panel.getName());
	}

	@SuppressWarnings("unchecked")
	private void updateValues(T value) {
		for (Map.Entry<String, FieldPanel> entry : fields.entrySet()) {
			entry.getValue().setValue(value.get(entry.getKey()));
		}
	}

	@Override
	public <X> void addField(FieldPanel<T, X> field) {
		fields.put(field.getProperty(), field);
		add(field);
	}

	@Override
	public <X> void removeField(FieldPanel<T, X> field) {
		fields.remove(field.getProperty());
		remove(field);
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
		updateValues(value);
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
