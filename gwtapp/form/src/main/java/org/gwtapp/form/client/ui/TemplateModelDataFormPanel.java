package org.gwtapp.form.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.ModelData;
import org.gwtapp.template.client.MessageHandler;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplateModelDataFormPanel<T extends ModelData> extends
		TemplateFormPanel<T> {

	private Map<String, MessageHandler<?>> fields = new HashMap<String, MessageHandler<?>>();

	public TemplateModelDataFormPanel() {
		super();
	}

	public TemplateModelDataFormPanel(Template template) {
		super(template);
	}

	public TemplateModelDataFormPanel(T value) {
		this();
		setValue(value);
	}

	public TemplateModelDataFormPanel(T value, Template template) {
		this(template);
		setValue(value);
	}

	public <E extends Widget & HasValue<?>> MessageHandler<E> add(
			MetaField<?, ?> autofield, MessageHandler<E> handler) {
		addFieldHandler(autofield, handler);
		return handler;
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(
			MetaField<?, ?> autofield, MessageHandler<E> handler) {
		addWidgetHandler(autofield.name(), handler);
		fields.put(autofield.name(), handler);
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(
			MetaField<?, ?> autofield, E field) {
		addWidgetHandler(autofield.name(), field);
		addField(autofield.name(), field);
	}

	@Override
	public final void onAddWidgets() {
		super.onAddWidgets();
		for (Map.Entry<String, MessageHandler<?>> entry : fields.entrySet()) {
			addField(entry.getKey(), (HasValue<?>) entry.getValue().getWidget());
		}
		onAddFormWidgets();
	}

	public void onAddFormWidgets() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		T value = super.getValue();
		if (isTemplated() && value != null) {
			for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
				value.set(entry.getKey(), entry.getValue().getValue());
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value, boolean fireEvents) {
		if (isTemplated()) {
			for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
				if (value != null) {
					entry.getValue().setValue(value.get(entry.getKey()));
				} else {
					entry.getValue().setValue(null);
				}
			}
		}
		super.setValue(value, fireEvents);
	};
}
