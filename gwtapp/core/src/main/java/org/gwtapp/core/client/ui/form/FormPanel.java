package org.gwtapp.core.client.ui.form;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.data.ModelData;
import org.gwtapp.core.client.html.ui.form.HFormPanel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ContainerPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;

public class FormPanel<T extends ModelData> extends ContainerPanel implements
		HasName, HasValue<T>, IsFieldPanel<T>, HasFireOnUpdate {

	public final static class Style {
		public final static String FORM_PANEL = "formPanel";
	}

	@SuppressWarnings("unchecked")
	private final Map<String, FieldPanel> fields = new HashMap<String, FieldPanel>();
	private boolean fireOnUpdate = false;
	private String name;
	private T value;

	public FormPanel(T value) {
		this(DOM.createDiv(), value);
	}

	public FormPanel(HFormPanel<T> panel) {
		this(DOM.getElementById(panel.getId()), panel.getValue());
		setName(panel.getName());
	}

	public FormPanel(Element element, T value) {
		setElement(element);
		addStyleName(Style.FORM_PANEL);
		this.value = value;
	}

	protected void wrap() {
		onAttach();
		RootPanel.detachOnWindowClose(this);
	}

	@Override
	public <X> void addField(FieldPanel<T, X> field) {
		fields.put(field.getProperty(), field);
		if (!field.isAttached()) {
			add(field);
		}
	}

	@Override
	public <X> void removeField(FieldPanel<T, X> field) {
		fields.remove(field.getProperty());
		remove(field);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> FieldPanel<T, X> getField(String fieldName) {
		return fields.get(fieldName);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		for (Map.Entry<String, FieldPanel> entry : fields.entrySet()) {
			value.set(entry.getKey(), entry.getValue().getValue());
		}
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value, boolean fireEvents) {
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		this.value = value;
		for (Map.Entry<String, FieldPanel> entry : fields.entrySet()) {
			entry.getValue().setValue(value.get(entry.getKey()));
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setFireOnUpdate(boolean fireOnUpdate) {
		this.fireOnUpdate = fireOnUpdate;
	}

	@Override
	public boolean isFireOnUpdate() {
		return fireOnUpdate;
	}
}
