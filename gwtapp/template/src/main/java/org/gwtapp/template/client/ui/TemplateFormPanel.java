package org.gwtapp.template.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.Template;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplateFormPanel<T> extends TemplatePanel<T> {

	public final static class Style {
		public final static String TEMPLATE_FORM_PANEL = "templateFormPanel";
	}

	@SuppressWarnings("unchecked")
	private final Map<String, HasValue> fields = new HashMap<String, HasValue>();
	@SuppressWarnings("unchecked")
	private final Map<String, ValueChangeHandler> handlers = new HashMap<String, ValueChangeHandler>();

	public TemplateFormPanel(Template template) {
		super(template);
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

	public TemplateFormPanel(Template template, T value) {
		super(template, value);
		init();
	}

	private void init() {
		addStyleName(Style.TEMPLATE_FORM_PANEL);
	}

	public <E extends HasValue<?> & HasName> E addField(E field) {
		addField(field.getName(), field);
		return field;
	}

	@SuppressWarnings("unchecked")
	public HasValue<?> addField(String name, HasValue<?> field) {
		assert name != null;
		ValueChangeHandler handler = new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				ValueChangeEvent.fire(TemplateFormPanel.this, getValue());
			}
		};
		field.addValueChangeHandler(handler);
		fields.put(name, field);
		handlers.put(name, handler);
		return field;
	}

	public <E extends Widget & HasValue<?>> E addFieldHandler(String name,
			E field) {
		addWidgetHandler(name, field);
		addField(name, field);
		return field;
	}

	public <E extends HasValue<?> & HasName> void removeField(E field) {
		removeField(field.getName());
	}

	public void removeField(String name) {
		assert name != null;
		handlers.remove(name);
		fields.remove(name);
	}

	@SuppressWarnings("unchecked")
	public HasValue getField(String name) {
		assert name != null;
		return fields.get(name);
	}

	@SuppressWarnings("unchecked")
	protected Map<String, HasValue> getFields() {
		return fields;
	}
}
