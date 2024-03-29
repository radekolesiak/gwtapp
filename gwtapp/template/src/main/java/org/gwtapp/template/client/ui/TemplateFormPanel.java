package org.gwtapp.template.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplateFormPanel<T> extends TemplatePanel<T> {

	public final static class Style {
		public final static String TEMPLATE_FORM_PANEL = "template-form-panel";
	}


	@SuppressWarnings("rawtypes")
	private final Map<String, HasValue> fields = new HashMap<String, HasValue>();
	@SuppressWarnings("rawtypes")
	private final Map<String, ValueChangeHandler> handlers = new HashMap<String, ValueChangeHandler>();

	private boolean autoValueChangeFire = true;

	public TemplateFormPanel(TemplateCallback callback) {
		super(callback);
		init();
	}

	public TemplateFormPanel(TemplateCallback callback, T value) {
		super(callback, value);
		init();
	}

	public TemplateFormPanel(ElementCallback callback) {
		super(callback);
		init();
	}

	public TemplateFormPanel(ElementCallback callback, T value) {
		super(callback, value);
		init();
	}

	private void init() {
		addStyleName(Style.TEMPLATE_FORM_PANEL);
	}

	public <E extends HasValue<?> & HasName> E addField(E field) {
		addField(field.getName(), field);
		return field;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HasValue<?> addField(String name, HasValue<?> field) {
		assert name != null && !name.isEmpty();
		ValueChangeHandler handler = new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				if (isAutoValueChangeFire()) {
					fireValueChangeEvent();
				}
			}
		};
		field.addValueChangeHandler(handler);
		fields.put(name, field);
		handlers.put(name, handler);
		return field;
	}

	public <E extends Widget & HasValue<?>> E addFieldHandler(String name,
			E field) {
		add(name, field);
		addField(name, field);
		return field;
	}

	public void setAutoValueChangeFire(boolean autoValueChangeFire) {
		this.autoValueChangeFire = autoValueChangeFire;
	}

	public boolean isAutoValueChangeFire() {
		return autoValueChangeFire;
	}

	@SuppressWarnings("rawtypes")
	public HasValue getField(String name) {
		assert name != null && !name.isEmpty();
		return fields.get(name);
	}

	@SuppressWarnings("rawtypes")
	protected Map<String, HasValue> getFields() {
		return fields;
	}
}
