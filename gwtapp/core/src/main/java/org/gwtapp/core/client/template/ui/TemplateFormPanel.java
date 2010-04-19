package org.gwtapp.core.client.template.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.rpc.data.AutoField;

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

	public TemplateFormPanel() {
		addStyleName(Style.TEMPLATE_FORM_PANEL);
	}

	public TemplateFormPanel(Template template) {
		super(template);
		addStyleName(Style.TEMPLATE_FORM_PANEL);
	}

	public TemplateFormPanel(T value) {
		this();
		setValue(value);
	}

	public TemplateFormPanel(T value, Template template) {
		this(template);
		setValue(value);
	}

	public <E extends HasValue<?> & HasName> void addField(E field) {
		addField(field.getName(), field);
	}

	@SuppressWarnings("unchecked")
	public void addField(String name, HasValue<?> field) {
		ValueChangeHandler handler = new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				ValueChangeEvent.fire(TemplateFormPanel.this, getValue());
			}
		};
		field.addValueChangeHandler(handler);
		fields.put(name, field);
		handlers.put(name, handler);
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(
			AutoField<?, ?> autofield, E field) {
		addWidgetHandler(autofield.name(), field);
		addField(autofield.name(), field);
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(String name,
			E field) {
		addWidgetHandler(name, field);
		addField(name, field);
	}

	public <E extends HasValue<?> & HasName> void removeField(E field) {
		removeField(field.getName());
	}

	public void removeField(String name) {
		handlers.remove(name);
		fields.remove(name);
	}

	@SuppressWarnings("unchecked")
	public HasValue getField(String fieldName) {
		return fields.get(fieldName);
	}

	@SuppressWarnings("unchecked")
	protected Map<String, HasValue> getFields() {
		return fields;
	}
}
